package com.miteam.floaty.account;

import com.miteam.floaty.utils.SQLconnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class UpdateUserData {
    @GetMapping(path = "/list")
    public Map<String, Object> listUserInfo(HttpServletRequest req){
        Map<String, Object> res = new HashMap<>();
        String token = req.getHeader("Authorization");
        String owner = JwtUtil.parseToken(token.split(" ")[1]);
        try  {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM admin WHERE username = ?");
            preparedStatement.setString(1,owner);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Map<String, Object> userDetail = new HashMap<>();
                userDetail.put("firstname" , resultSet.getString("first_name"));
                userDetail.put("lastname" , resultSet.getString("last_name"));
                userDetail.put("image", resultSet.getString("image"));
                res.put("userDetail", userDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
