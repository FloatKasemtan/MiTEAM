package com.miteam.floaty.team;

import com.miteam.floaty.utils.SQLconnector;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/team")
public class TeamDelete {
    @DeleteMapping(path = "/delete")
    public Map<String, Object> delete(@RequestParam int id){
        Map<String, Object> res = new HashMap<>();
        try  {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM team WHERE team_id = ?");
            preparedStatement.setInt(1,id);
            PreparedStatement ps_Employee = connection.prepareStatement("DELETE FROM employee where team_id = ?");
            ps_Employee.setInt(1,id);
            ps_Employee.executeUpdate();
            preparedStatement.executeUpdate();

            res.put("SUCCESS", true);
        } catch (SQLException e) {
            e.printStackTrace();
            res.put("SUCCESS", false);
        }
        return res;
    }
}
