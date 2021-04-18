package com.miteam.floaty.event;

import com.miteam.floaty.utils.SQLconnector;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/event")
public class ChangeStatus {
    @PutMapping(path = "/update")
    public Map<String, Object> update(@RequestParam int id){
        Map<String, Object> res = new HashMap<>();
        try  {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM event WHERE event_id = ?");
            preparedStatement.setInt(1, id);
            ResultSet rs =  preparedStatement.executeQuery();
            rs.next();

            if (rs.getBoolean("is_finish")){
                PreparedStatement ps = connection.prepareStatement("UPDATE event SET is_finish = false WHERE event_id = ?");
                ps.setInt(1, id);
                ps.executeUpdate();
            }else{
                PreparedStatement ps = connection.prepareStatement("UPDATE event SET is_finish = true WHERE event_id = ?");
                ps.setInt(1, id);
                ps.executeUpdate();
            }

            res.put("SUCCESS", true);

        } catch (SQLException e) {
            e.printStackTrace();
            res.put("SUCCESS", false);
        }
        return res;
    }
}
