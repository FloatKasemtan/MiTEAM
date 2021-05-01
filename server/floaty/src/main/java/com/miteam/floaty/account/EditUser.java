package com.miteam.floaty.account;

import com.miteam.floaty.DTO.EmployeeDTO;
import com.miteam.floaty.DTO.RegisDTO;
import com.miteam.floaty.utils.SQLconnector;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class EditUser {
    @PutMapping(path = "/updateFname")
    public Map<String, Object> editUserFname(@RequestParam String newDetail, HttpServletRequest req) {
        Map<String, Object> res = new HashMap<>();
        String token = req.getHeader("Authorization");
        String owner = JwtUtil.parseToken(token.split(" ")[1]);
        try {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE admin SET first_name = ? WHERE username = ?");

            preparedStatement.setString(1, newDetail);
            preparedStatement.setString(2, owner);
            preparedStatement.executeUpdate();
            res.put("SUCCESS", true);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("SUCCESS", false);
        }
        return res;
    }
    @PutMapping(path = "/updateLname")
    public Map<String, Object> editUserLname(@RequestParam String newDetail, HttpServletRequest req) {
        Map<String, Object> res = new HashMap<>();
        String token = req.getHeader("Authorization");
        String owner = JwtUtil.parseToken(token.split(" ")[1]);
        try {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE admin SET last_name = ? WHERE username = ?");

            preparedStatement.setString(1, newDetail);
            preparedStatement.setString(2, owner);
            preparedStatement.executeUpdate();
            res.put("SUCCESS", true);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("SUCCESS", false);
        }
        return res;
    }
    @PutMapping(path = "/updateImage")
    public Map<String, Object> editUserImage(@RequestParam String newDetail, HttpServletRequest req) {
        Map<String, Object> res = new HashMap<>();
        String token = req.getHeader("Authorization");
        String owner = JwtUtil.parseToken(token.split(" ")[1]);
        try {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE admin SET image = ? WHERE username = ?");

            preparedStatement.setString(1, newDetail);
            preparedStatement.setString(2, owner);
            preparedStatement.executeUpdate();
            res.put("SUCCESS", true);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("SUCCESS", false);
        }
        return res;
    }
    @PutMapping(path = "/updatePass")
    public Map<String, Object> editUserPass(@RequestParam String newDetail, HttpServletRequest req) {
        Map<String, Object> res = new HashMap<>();
        String token = req.getHeader("Authorization");
        String owner = JwtUtil.parseToken(token.split(" ")[1]);
        try {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE admin SET password = ? WHERE username = ?");

            preparedStatement.setString(1, newDetail);
            preparedStatement.setString(2, owner);
            preparedStatement.executeUpdate();
            res.put("SUCCESS", true);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("SUCCESS", false);
        }
        return res;
    }
}
