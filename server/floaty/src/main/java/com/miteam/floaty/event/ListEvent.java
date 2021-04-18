package com.miteam.floaty.event;

import com.miteam.floaty.utils.SQLconnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/event")
public class ListEvent {
    @GetMapping(path = "/list")
    public Map<String, Object> listEvent(){
        Map<String, Object> res = new HashMap<>();
        try  {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM event");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Map<String, Object>> events = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> event = new HashMap<>();
                event.put("event_id", resultSet.getInt("event_id"));
                event.put("team_id", resultSet.getInt("team_id"));
                event.put("deadline", resultSet.getDate("deadline"));
                event.put("is_finish", resultSet.getBoolean("is_finish"));
                event.put("start_time", resultSet.getDate("start_time"));
                event.put("name", resultSet.getString("name"));
                events.add(event);
            }
            res.put("events", events);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
