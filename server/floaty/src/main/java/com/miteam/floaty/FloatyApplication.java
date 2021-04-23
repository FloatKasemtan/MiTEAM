package com.miteam.floaty;

import com.miteam.floaty.utils.SQLconnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class FloatyApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(FloatyApplication.class, args);
		new SQLconnector();
	}

}
