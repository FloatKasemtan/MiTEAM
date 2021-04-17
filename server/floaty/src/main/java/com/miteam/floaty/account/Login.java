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
                String token = JwtUtil.generateToken(rs.getInt("id_user") + "");
                res.put("token", token);

                Map<String, Object> userDetail = new HashMap<>();
                Map<String, Object> bgColor = new HashMap<>();

                userDetail.put("id" , rs.getInt("id_user"));
                userDetail.put("firstname" , rs.getString("first_name"));
                userDetail.put("lastname" , rs.getString("last_name"));
                userDetail.put("phoneNumber" , rs.getString("phone_no"));
                userDetail.put("email" , rs.getString("email"));
                userDetail.put("isAdmin" , rs.getBoolean("isAdmin"));

                bgColor.put("color" , rs.getString("bg_color"));
                bgColor.put("value" , rs.getString("bg_color").toLowerCase());

                res.put("getRmd", rs.getBoolean("getReminders"));
                res.put("bgColor", bgColor);
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
