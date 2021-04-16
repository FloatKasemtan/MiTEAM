package com.miteam.floaty.team;

import com.miteam.floaty.utils.SQLconnector;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/team")
public class Edit {
    @PostMapping(path = "/update")
    public Map<String, Object> update(@RequestParam int id, @RequestParam String name, @RequestParam String image){
        Map<String, Object> res = new HashMap<>();
        try  {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE team SET name = ?, image = ? WHERE team_id = ?");

            preparedStatement.setString (1, name);
            preparedStatement.setString (2, image);

            preparedStatement.setInt (3, id);

            preparedStatement.executeUpdate();
            res.put("SUCCESS", true);
        } catch (SQLException e) {
            e.printStackTrace();
            res.put("SUCCESS", false);
        }
        return res;
    }
}
