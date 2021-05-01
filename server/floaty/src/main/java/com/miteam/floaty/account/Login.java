package com.miteam.floaty.account;

import com.miteam.floaty.DTO.LoginDTO;
import com.miteam.floaty.utils.SQLconnector;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    public Map<String, Object> _login(@RequestBody LoginDTO user, HttpServletResponse response) {
        Map<String, Object> res = new HashMap<>();
        try {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM admin WHERE username = ? AND password = ?");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPass());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String token = JwtUtil.generateToken(rs.getString("username") + "");
                final String HEADER_STRING = "Authorization";
                final String TOKEN_PREFIX = "Bearer";
                response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + token);
                res.put("token", token);

                Map<String, Object> userDetail = new HashMap<>();

                userDetail.put("firstname" , rs.getString("first_name"));
                userDetail.put("lastname" , rs.getString("last_name"));
                userDetail.put("image", rs.getString("image"));

                res.put("userDetail", userDetail);
                res.put("loginStatus", true);
                String owner = JwtUtil.parseToken(token);
                res.put("owner", owner);
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
