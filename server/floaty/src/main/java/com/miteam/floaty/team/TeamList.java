package com.miteam.floaty.team;

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
@RequestMapping("/team")
public class TeamList {
    @GetMapping(path = "/list")
    public Map<String, Object> list(){
        Map<String, Object> res = new HashMap<>();

        try  {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM team");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Map<String, Object>> teams = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> team = new HashMap<>();
                team.put("team_id", resultSet.getBigDecimal("team_id"));
                team.put("name", resultSet.getString("name"));
                team.put("image", resultSet.getString("image"));
                team.put("progress", resultSet.getInt("progress"));
                teams.add(team);
            }
            res.put("teams", teams);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

}