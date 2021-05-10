package com.miteam.floaty.account;

import com.miteam.floaty.DTO.LoginDTO;
import com.miteam.floaty.DTO.ResetPassDTO;
import com.miteam.floaty.utils.SQLconnector;
import io.jsonwebtoken.JwtException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class ForgetPass {
    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping(path = "/forgot")
    public Map<String, Object> _forgot(@RequestParam String email) {
        Map<String, Object> res = new HashMap<>();
        try {

            Connection conn = SQLconnector.getConnection();
            PreparedStatement pstm = conn.prepareStatement("INSERT INTO reset_pass (verify_cd, expire_tm, username) " +
                    "SELECT ? as verify_cd, ? as expire_tm, username from admin WHERE email = ?", Statement.RETURN_GENERATED_KEYS);

            // Random
            String generatedString = RandomStringUtils.randomAlphanumeric(6);
            pstm.setString(1, generatedString);

            Timestamp expire_tm = new Timestamp(new java.util.Date().getTime() + (15 * 60 * 1000)); // 15 minutes
            pstm.setTimestamp(2, expire_tm);
            pstm.setString(3, email);
            int rs = pstm.executeUpdate();

            if (rs >= 1) {
                try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        res.put("id_reset", generatedKeys.getInt(1));
                        SimpleMailMessage msg = new SimpleMailMessage();
                        msg.setTo(email);
                        msg.setFrom("chuxreataurant@patiphon.cf");
                        msg.setSubject("Chuxrestaurant password reset token");
                        msg.setText("Your verification code is " + generatedString);
                        javaMailSender.send(msg);
                        res.put("success", true);
                        res.put("text", "Please check your email for verification code");
                        return res;
                    } else {
                        res.put("success", false);
                        res.put("text", "Creating user failed, no reset ID obtained");
                        return res;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    res.put("success", false);
                    res.put("text", "Something Wrong (generatedKeys)");
                }
            }

            res.put("success", false);
            res.put("text", "Creating user failed, no email obtained");

        } catch (JwtException e) {
            e.printStackTrace();
            res.put("success", false);
            res.put("text", "Token is incorrect");
        } catch (Exception e) {
            e.printStackTrace();
            res.put("success", false);
            res.put("text", "Something Wrong");
        }
        return res;
    }

    @PatchMapping(path = "/reset")
    public Map<String, Object> _reset(@RequestBody ResetPassDTO info) {
        Map<String, Object> res = new HashMap<>();
        try {
            Connection conn = SQLconnector.getConnection();
            PreparedStatement pstm = conn.prepareStatement("SELECT username FROM reset_pass WHERE ? = verify_cd AND ? = id_reset AND expire_tm > ?");
            pstm.setString(1, info.getVerify());
            pstm.setInt(2, info.getId_reset());
            pstm.setTimestamp(3, new Timestamp(new Date().getTime())); // > Now
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                try {
                    PreparedStatement user = conn.prepareStatement("UPDATE admin SET password = ? WHERE username = ?");
                    user.setString(1, info.getNew_pass());
                    user.setString(2, rs.getString("username"));
                    user.executeUpdate();

                    res.put("success", true);
                    res.put("text", "Your password is changed :)");
                } catch (Exception e) {
                    res.put("success", false);
                    res.put("text", "Something Wrong :(");
                    e.printStackTrace();
                }
            } else {
                res.put("success", false);
                res.put("text", "Your verification code must be valid! :(");
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.put("success", false);
            res.put("text", "Something Wrong :(");
        }
        return res;
    }
}
