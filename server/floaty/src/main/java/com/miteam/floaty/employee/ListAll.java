package com.miteam.floaty.employee;

import com.miteam.floaty.account.JwtUtil;
import com.miteam.floaty.utils.SQLconnector;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class ListAll {
    @GetMapping(path = "/listAll")
    public Map<String, Object> list(HttpServletRequest req, @RequestParam long thisDate){
        Map<String, Object> res = new HashMap<>();
        String token = req.getHeader("Authorization");
        try  {
            String owner = JwtUtil.parseToken(token.split(" ")[1]);
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee,team,check_in WHERE team.owner = ? AND employee.team_id = team.team_id AND check_in.employee_id = employee.employee_id AND check_in_time >= ?");
            preparedStatement.setString(1,owner);
            preparedStatement.setTimestamp(2,new Timestamp(thisDate-25200000));
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Map<String, Object>> employees = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> employee = new HashMap<>();
                employee.put("id",resultSet.getInt("employee_id"));
                employee.put("team_id", resultSet.getInt("team_id"));
                employee.put("team_name", resultSet.getString("name"));
                employee.put("firstname", resultSet.getString("firstname"));
                employee.put("lastname", resultSet.getString("lastname"));
                employee.put("email", resultSet.getString("email"));
                employee.put("check_in", resultSet.getTimestamp("check_in_time"));
                employee.put("check_out",null);
                PreparedStatement p1 = connection.prepareStatement("SELECT * FROM check_out WHERE check_out_time >= ? AND employee_id = ?");
                p1.setTimestamp(1,new Timestamp(thisDate-25200000));
                p1.setInt(2,resultSet.getInt("employee_id"));
                ResultSet rs1 = p1.executeQuery();
                if (rs1.next()) {
                    employee.put("check_out", rs1.getTimestamp("check_out_time"));
                }
                employees.add(employee);
            }
            res.put("employees", employees);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
