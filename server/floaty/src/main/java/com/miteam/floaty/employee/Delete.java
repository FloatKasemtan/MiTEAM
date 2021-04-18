package com.miteam.floaty.employee;

import com.miteam.floaty.utils.SQLconnector;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class Delete {
    @DeleteMapping(path = "/delete")
    public Map<String, Object> delete(@RequestParam int id){
        Map<String, Object> res = new HashMap<>();
        try  {
            Connection connection = SQLconnector.getConnection();
            PreparedStatement ps_in = connection.prepareStatement("DELETE FROM check_in WHERE employee_id = ?");
            ps_in.setInt(1, id);
            ps_in.executeUpdate();
            PreparedStatement ps_out = connection.prepareStatement("DELETE FROM check_out WHERE employee_id = ?");
            ps_out.setInt(1, id);
            ps_out.executeUpdate();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM employee WHERE employee_id = ?");
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
