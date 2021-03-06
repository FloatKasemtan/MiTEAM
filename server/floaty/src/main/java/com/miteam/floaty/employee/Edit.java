package com.miteam.floaty.employee;

import com.miteam.floaty.DTO.EmployeeDTO;
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
    public Map<String, Object> edit(@RequestBody EmployeeDTO member) {
        Map<String, Object> res = new HashMap<>();
        try {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employee SET firstname = ?, lastname = ?, image = ?, salary = ?, status = ?, email = ?, is_manager = ? WHERE employee_id = ?");

            preparedStatement.setString(1, member.getFname());
            preparedStatement.setString(2, member.getLname());
            preparedStatement.setString(3, member.getImage());
            preparedStatement.setDouble(4, member.getSalary());
            preparedStatement.setString(5, member.getStatus());
            preparedStatement.setString(6, member.getEmail());
            preparedStatement.setBoolean(7, member.isIs_manager());
            preparedStatement.setInt(8, member.getEmployee_id());

            preparedStatement.executeUpdate();
            res.put("SUCCESS", true);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("SUCCESS", false);
        }
        return res;
    }
}
