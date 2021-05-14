package com.miteam.floaty.employee;

import com.miteam.floaty.account.JwtUtil;
import com.miteam.floaty.utils.SQLconnector;
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
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class ListForOwner {
    @GetMapping(path = "/listForOwner")
    public Map<String, Object> list(HttpServletRequest req){
        Map<String, Object> res = new HashMap<>();
        String token = req.getHeader("Authorization");
        try  {
            String owner = JwtUtil.parseToken(token.split(" ")[1]);
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee,team WHERE team.owner = ? AND employee.team_id = team.team_id");
            preparedStatement.setString(1,owner);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Map<String, Object>> employees = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> employee = new HashMap<>();
                employee.put("employee_id", resultSet.getInt("employee_id"));
                employee.put("firstname", resultSet.getString("firstname"));
                employees.add(employee);
            }
            res.put("employees", employees);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
