package com.miteam.floaty.event;

import com.miteam.floaty.utils.SQLconnector;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
เ
@RestController
@RequestMapping("/event")
public class EventAdd {
    @PostMapping(path = "/insert")
    public Map<String, Object> eventAdd(@RequestParam String name, @RequestParam Date start, @RequestParam Date deadline, @RequestParam String team_name) {
        Map<String, Object> res = new HashMap<>();
        try {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement psForTeamId = connection.prepareStatement("SELECT * FROM team WHERE name = ?");
            psForTeamId.setString(1,team_name);
            ResultSet rs = psForTeamId.executeQuery();
            rs.next();
            int team_id = rs.getInt("team_id");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO event (name, team_id, start_time, deadline)" + " VALUES (?, ?, ?, ?)");

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, team_id);
            preparedStatement.setDate(3,start);
            preparedStatement.setDate(4,deadline);

            preparedStatement.executeUpdate();
            res.put("SUCCESS", true);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("SUCCESS", false);
        }
        return res;
    }
}

