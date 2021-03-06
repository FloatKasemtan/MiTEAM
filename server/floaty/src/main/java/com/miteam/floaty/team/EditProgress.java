package com.miteam.floaty.team;

import com.miteam.floaty.utils.SQLconnector;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/team")
public class EditProgress {
    @PutMapping(path = "/updateProgress")
    public Map<String, Object> updateProgress(@RequestParam int progress, @RequestParam int id){
        Map<String, Object> res = new HashMap<>();
        try  {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE team SET progress = ? WHERE team_id = ?");
            ps.setInt(1,progress);
            ps.setInt(2,id);
            ps.executeUpdate();
            res.put("SUCCESS", true);

        } catch (SQLException e) {
            e.printStackTrace();
            res.put("SUCCESS", false);
        }
        return res;
    }

}
