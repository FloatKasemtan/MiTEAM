package com.miteam.floaty.employee;

import com.miteam.floaty.utils.SQLconnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class ListManager {
    @GetMapping(path = "/listManager")
    public Map<String, Object> list(@RequestParam int id){
        Map<String, Object> res = new HashMap<>();
        try  {

            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE team_id = ? AND is_manager = 1");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Map<String, Object>> employees = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> employee = new HashMap<>();
                employee.put("employee_id", resultSet.getInt("employee_id"));
                employee.put("firstname", resultSet.getString("firstname"));
                employee.put("lastname", resultSet.getString("lastname"));
                employee.put("image", resultSet.getString("image"));
                employee.put("salary", resultSet.getString("salary"));
                employee.put("status", resultSet.getString("status"));
                employee.put("email", resultSet.getString("email"));
                employee.put("is_manager", resultSet.getBoolean("is_manager"));
                employee.put("hire_since", resultSet.getTimestamp("hire_since"));
                employees.add(employee);
            }
            res.put("employees", employees);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
