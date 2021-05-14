package com.miteam.floaty.check;

import com.miteam.floaty.utils.SQLconnector;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/check")
public class Check_out {
    @PostMapping(path = "/out")
    public Map<String, Object> check_out(@RequestParam int employee_id, @RequestParam long check_out_time) {
        Map<String, Object> res = new HashMap<>();
        try {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement psforcheck = connection.prepareStatement("SELECT employee_id FROM check_out WHERE employee_id = ?");
            psforcheck.setInt(1,employee_id);
            ResultSet rs = psforcheck.executeQuery();
            if(rs.next()){
                res.put("SUCCESS", false);
            }else{
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO check_out(employee_id, check_out_time)" + " VALUES (?, ?)");

                preparedStatement.setInt(1, employee_id);
                preparedStatement.setTimestamp(2, new Timestamp(check_out_time));

                preparedStatement.executeUpdate();
                res.put("SUCCESS", true);
            }

        } catch (Exception e) {
            e.printStackTrace();
            res.put("error", false);
        }
        return res;
    }
}
