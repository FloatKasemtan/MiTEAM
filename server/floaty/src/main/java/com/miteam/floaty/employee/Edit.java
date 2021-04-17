package com.miteam.floaty.employee;

import com.miteam.floaty.utils.SQLconnector;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class Edit {
    @PutMapping(path = "/update")
    public Map<String, Object> edit(@RequestParam String fname, @RequestParam String lname, @RequestParam String image, @RequestParam double salary, @RequestParam String status, @RequestParam String email, @RequestParam boolean is_manager, @RequestParam int employee_id) {
        Map<String, Object> res = new HashMap<>();
        try {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employee SET firstname = ?, lastname = ?, image = ?, salary = ?, status = ?, email = ?, is_manager = ? WHERE employee_id = ?");

            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            preparedStatement.setString(3, image);
            preparedStatement.setDouble(4, salary);
            preparedStatement.setString(5, status);
            preparedStatement.setString(6, email);
            preparedStatement.setBoolean(7, is_manager);
            preparedStatement.setInt(8, employee_id);

            preparedStatement.executeUpdate();
            res.put("SUCCESS", true);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("SUCCESS", false);
        }
        return res;
    }
}
