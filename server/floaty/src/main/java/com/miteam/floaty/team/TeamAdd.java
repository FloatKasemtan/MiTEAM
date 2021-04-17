package com.miteam.floaty.team;

import com.miteam.floaty.utils.SQLconnector;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/team")
public class TeamAdd {
    @PostMapping(path = "/insert")
    public Map<String, Object> Add(@RequestParam String name,@RequestParam String image){
        Map<String, Object> res = new HashMap<>();
        try
        {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO team (name, image)" + " VALUES (?, ?)");

            preparedStatement.setString (1, name);
            preparedStatement.setString (2, image);

            preparedStatement.executeUpdate();
            res.put("SUCCESS", true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            res.put("SUCCESS", false);
        }
        return res;
    }
}
