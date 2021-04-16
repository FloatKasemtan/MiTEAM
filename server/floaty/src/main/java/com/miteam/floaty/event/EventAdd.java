package com.miteam.floaty.event;

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
@RequestMapping("/event")
public class Add {
    @PostMapping(path = "/insert")
    public Map<String, Object> eventAdd(@RequestParam String name, @RequestParam DateTimeFormatter start, @RequestParam DateTimeFormatter deadline, @RequestParam int team_id) {
        Map<String, Object> res = new HashMap<>();
        try {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO event (name, team_id, start_time, deadline)" + " VALUES (?, ?, ?, ?)");

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, team_id);

            preparedStatement.executeQuery();
            res.put("SUCCESS", true);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("SUCCESS", false);
        }
        return res;
    }
}

