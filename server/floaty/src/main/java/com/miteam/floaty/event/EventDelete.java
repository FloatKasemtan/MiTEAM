package com.miteam.floaty.event;

import com.miteam.floaty.utils.SQLconnector;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/event")
public class EventDelete {
    @DeleteMapping(path = "/delete")
    public Map<String, Object> eventDelete(@RequestParam int id){
        Map<String, Object> res = new HashMap<>();
        try  {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM event WHERE event_id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

            res.put("SUCCESS", true);
        } catch (SQLException e) {
            e.printStackTrace();
            res.put("SUCCESS", false);
        }
        return res;
    }
}
