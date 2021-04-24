package com.miteam.floaty.event;

import com.miteam.floaty.account.JwtUtil;
import com.miteam.floaty.utils.SQLconnector;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/event")
public class EventAdd {
    @PostMapping(path = "/insert")
    public Map<String, Object> eventAdd(@RequestParam String name, @RequestParam long start, @RequestParam long deadline, @RequestParam String team_name, HttpServletRequest req) {
        Map<String, Object> res = new HashMap<>();
        String token = req.getHeader("Authorization");
        String owner = JwtUtil.parseToken(token.split(" ")[1]);
        try {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement psForTeamId = connection.prepareStatement("SELECT * FROM team WHERE name = ?");
            psForTeamId.setString(1,team_name);
            ResultSet rs = psForTeamId.executeQuery();
            rs.next();
            int team_id = rs.getInt("team_id");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO event (name, team_id, start_time, deadline, owner)" + " VALUES (?, ?, ?, ?, ?)");

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, team_id);
            preparedStatement.setTimestamp(3,new Timestamp(start));
            preparedStatement.setTimestamp(4,new Timestamp(deadline));
            preparedStatement.setString(5, owner);

            preparedStatement.executeUpdate();
            res.put("SUCCESS", true);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("SUCCESS", false);
        }
        return res;
    }
}

