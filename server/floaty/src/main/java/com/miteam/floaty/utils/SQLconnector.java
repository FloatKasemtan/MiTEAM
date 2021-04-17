package com.miteam.floaty.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLconnector {
    private static final Logger logger = LoggerFactory.getLogger(SQLconnector.class);
    private static Connection connection;

    public SQLconnector() throws SQLException {
        reconnect();
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null)
            reconnect();
        return connection;
    }

    private static void reconnect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://mysql.cslab.bsthun.com:1205/miteam", "float", "2688");
        logger.info("Reconnected to MySQL Server");
    }

}
