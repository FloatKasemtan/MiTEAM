package com.miteam.floaty.employee;

import com.miteam.floaty.utils.SQLconnector;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class Add {
    @PostMapping(path = "/insert")
    public Map<String, Object> Add(@RequestParam String fname, @RequestParam String lname, @RequestParam String image, @RequestParam double salary, @RequestParam String status, @RequestParam String email, @RequestParam boolean is_manager, @RequestParam int team_id) {
        Map<String, Object> res = new HashMap<>();
        try {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employee (firstname, lastname, image, salary, status, email, is_manager, team_id)" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            preparedStatement.setString(3, image);
            preparedStatement.setDouble(4, salary);
            preparedStatement.setString(5, status);
            preparedStatement.setString(6, email);
            preparedStatement.setBoolean(7, is_manager);
            preparedStatement.setInt(8, team_id);

            preparedStatement.executeUpdate();
            res.put("SUCCESS", true);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("SUCCESS", false);
        }
        return res;
    }
}
