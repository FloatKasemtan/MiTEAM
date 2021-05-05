package com.miteam.floaty.account;

import com.miteam.floaty.DTO.RegisDTO;
import com.miteam.floaty.utils.SQLconnector;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class Regis {
    @PostMapping(path = "/register")
    public Map<String, Object> regis(@RequestBody RegisDTO user){
        Map<String, Object> res = new HashMap<>();
        try
        {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO admin(username,  password, image, first_name, last_name, email) VALUES (?, ?, ?, ?, ?, ?)");

            preparedStatement.setString (1, user.getUsername());
            preparedStatement.setString (2, user.getPassword());
            preparedStatement.setString(3, user.getImage());
            preparedStatement.setString(4, user.getFname());
            preparedStatement.setString(5, user.getLname());
            preparedStatement.setString(6, user.getEmail());

            preparedStatement.executeUpdate();
            res.put("SUCCESS", true);
        }catch (SQLIntegrityConstraintViolationException e){
            e.printStackTrace();
            res.put("SUCCESS", "Email already used");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            res.put("SUCCESS", false);
        }
        return res;
    }
}
