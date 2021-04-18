package com.miteam.floaty.account;

import com.miteam.floaty.utils.SQLconnector;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class Login {
    @PostMapping(path = "/login")
    public Map<String, Object> _login(@RequestParam String em, @RequestParam String passwd) {
        Map<String, Object> res = new HashMap<>();
        try {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM admin WHERE username = ? AND password = ?");
            preparedStatement.setString(1, em);
            preparedStatement.setString(2, passwd);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String token = JwtUtil.generateToken(rs.getString("username") + "");
                res.put("token", token);

                Map<String, Object> userDetail = new HashMap<>();

                userDetail.put("firstname" , rs.getString("first_name"));
                userDetail.put("lastname" , rs.getString("last_name"));
                userDetail.put("image", rs.getString("image"));

                res.put("loginStatus", true);
                res.put("userDetail", userDetail);
            } else {
                res.put("loginStatus", false);
            }
        } catch(SQLException e) {
            res.put("success", false);
            e.printStackTrace();
        }
        return res;
    }
}
