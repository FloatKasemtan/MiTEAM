package com.miteam.floaty.check;

import com.miteam.floaty.account.JwtUtil;
import com.miteam.floaty.utils.SQLconnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/check")
public class OvertimeCalculator {
    @GetMapping(path = "/ot")
    public Map<String, Object> getOt(HttpServletRequest req, @RequestParam String currentMonth, @RequestParam String nextMonth){
        Map<String, Object> res = new HashMap<>();
        String token = req.getHeader("Authorization");
        try  {
            String owner = JwtUtil.parseToken(token.split(" ")[1]);
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee,team,check_out WHERE team.owner = ? AND employee.team_id = team.team_id AND check_out.employee_id = employee.employee_id AND check_out_time >= ? AND check_out_time <= ?");
            preparedStatement.setString(1,owner);
            preparedStatement.setString(2,currentMonth);
            preparedStatement.setString(3,nextMonth);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Map<String, Object>> employees = new ArrayList<>();
            Map<String, Integer> OT = new HashMap<>();
            while (resultSet.next()) {
                int hours = Integer.parseInt(resultSet.getString("check_out_time").substring(11,13));
                String employee_id = resultSet.getString("employee_id");
                int diff = hours - 17;
                if(OT.get(employee_id) == null){
                    OT.put(employee_id,0);
                }
                if (diff >= 0){
                    OT.put(employee_id,OT.get(employee_id)+(diff));
                }
            }
            Iterator it = OT.entrySet().iterator();
            while (it.hasNext()) {
                Map<String, Object> employee = new HashMap<>();
                Map.Entry pair = (Map.Entry)it.next();

                String id = pair.getKey().toString();
                String ot =  pair.getValue().toString();
                PreparedStatement pstm = connection.prepareStatement("SELECT * FROM team,employee WHERE employee_id = ? and employee.team_id = team.team_id");
                pstm.setString(1,id);
                ResultSet rs = pstm.executeQuery();
                if (rs.next()){
                    employee.put("id",rs.getInt("employee_id"));
                    employee.put("team_id", rs.getInt("team_id"));
                    employee.put("team_name", rs.getString("name"));
                    employee.put("firstname", rs.getString("firstname"));
                    employee.put("lastname", rs.getString("lastname"));
                    employee.put("email", rs.getString("email"));
                }

                it.remove(); // avoids a ConcurrentModificationException
//SQL ไปดึงข้อมูล emp คนนั้น
                employee.put("ot",ot);
                employees.add(employee);
            }
            res.put("employees", employees);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
