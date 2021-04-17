package com.miteam.floaty.check;

import com.miteam.floaty.utils.SQLconnector;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/check")
public class Check_in {
    @PostMapping(path = "/in")
    public Map<String, Object> check_in(@RequestParam int employee_id, @RequestParam long check_in_time) {
        Map<String, Object> res = new HashMap<>();
        try {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO check_in(employee_id, check_in_time)" + " VALUES (?, ?)");

            preparedStatement.setInt(1, employee_id);
            preparedStatement.setTimestamp(2, new Timestamp(check_in_time*1000));

            preparedStatement.executeUpdate();
            res.put("SUCCESS", true);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("SUCCESS", false);
        }
        return res;
    }
}
