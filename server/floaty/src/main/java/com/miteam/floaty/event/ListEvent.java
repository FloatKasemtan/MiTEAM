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
    public Map<String, Object> list_event(){
        Map<String, Object> res = new HashMap<>();
        try  {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM event");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Map<String, Object>> events = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> team = new HashMap<>();
                team.put("event_id", resultSet.getInt("event_id"));
                team.put("team_id", resultSet.getInt("team_id"));
                team.put("deadline", resultSet.getString("deadline"));
                team.put("progress", resultSet.getInt("progress"));
                events.add(team);
            }
            res.put("events", events);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
