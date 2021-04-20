package com.miteam.floaty.team;

import com.miteam.floaty.account.JwtUtil;
import com.miteam.floaty.utils.SQLconnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    public Map<String, Object> list(HttpServletRequest req){
        Map<String, Object> res = new HashMap<>();
        String token = req.getHeader("Authorization");
        String owner = JwtUtil.parseToken(token.split(" ")[1]);
        try  {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM team WHERE owner = ?");
            preparedStatement.setString(1,owner);
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