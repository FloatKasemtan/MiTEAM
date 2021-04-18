package com.miteam.floaty.account;

import com.miteam.floaty.utils.SQLconnector;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class Regis {
    @PostMapping(path = "/register")
    public Map<String, Object> regis(@RequestParam String username, @RequestParam String password, @RequestParam String image, @RequestParam String fname, @RequestParam
                                     String lname){
        Map<String, Object> res = new HashMap<>();
        try
        {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO admin (username,  password, image, first_name, last_name)" + " VALUES (?, ?, ?, ?, ?)");

            preparedStatement.setString (1, username);
            preparedStatement.setString (2, password);
            preparedStatement.setString(3, image);
            preparedStatement.setString(4, fname);
            preparedStatement.setString(5, lname);


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
