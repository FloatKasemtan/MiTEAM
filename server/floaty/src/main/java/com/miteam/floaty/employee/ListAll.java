package com.miteam.floaty.employee;

import com.miteam.floaty.account.JwtUtil;
import com.miteam.floaty.utils.SQLconnector;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class ListAll {
    @GetMapping(path = "/listAll")
    public Map<String, Object> list(HttpServletRequest req){
        Map<String, Object> res = new HashMap<>();
        String token = req.getHeader("Authorization");
        try  {
            String owner = JwtUtil.parseToken(token.split(" ")[1]);
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee,team,check_in,check_out WHERE team.owner = ? AND employee.team_id = team.team_id AND check_in.employee_id = employee.employee_id AND check_out.employee_id = employee.employee_id");
            preparedStatement.setString(1,owner);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Map<String, Object>> employees = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> employee = new HashMap<>();
                employee.put("team_id", resultSet.getInt("team_id"));
                employee.put("team_name", resultSet.getString("name"));
                employee.put("firstname", resultSet.getString("firstname"));
                employee.put("lastname", resultSet.getString("lastname"));
                employee.put("image", resultSet.getString("image"));
                employee.put("salary", resultSet.getString("salary"));
                employee.put("status", resultSet.getString("status"));
                employee.put("email", resultSet.getString("email"));
                employee.put("is_manager", resultSet.getString("is_manager"));
                employee.put("check_in", resultSet.getTimestamp("check_in_time"));
                employee.put("check_out", resultSet.getTimestamp("check_out_time"));
                employees.add(employee);
            }
            res.put("employees", employees);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
