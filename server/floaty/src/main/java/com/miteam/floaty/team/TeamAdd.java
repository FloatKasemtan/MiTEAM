package com.miteam.floaty.team;

import com.miteam.floaty.account.JwtUtil;
import com.miteam.floaty.utils.SQLconnector;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/team")
public class TeamAdd {
    @PostMapping(path = "/insert")
    public Map<String, Object> Add(@RequestParam String name,@RequestParam String image, HttpServletRequest req){
        Map<String, Object> res = new HashMap<>();
        String token = req.getHeader("Authorization");
        String owner = JwtUtil.parseToken(token.split(" ")[1]);
        try
        {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO team (name, image, owner)" + " VALUES (?, ?, ?)");

            preparedStatement.setString (1, name);
            preparedStatement.setString (2, image);
            preparedStatement.setString (3, owner);

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
