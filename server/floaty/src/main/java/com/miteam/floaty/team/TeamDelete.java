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
public class TeamDelete {
    @GetMapping(path = "/delete")
    public Map<String, Object> delete(int id){
        Map<String, Object> res = new HashMap<>();
        try  {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM team WHERE team_id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

            res.put("SUCCESS", true);
        } catch (SQLException e) {
            e.printStackTrace();
            res.put("SUCCESS", false);
        }
        return res;
    }
}
