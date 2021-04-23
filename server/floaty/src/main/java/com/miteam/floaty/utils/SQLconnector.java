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
        if (!connection.isValid(200))
            reconnect();
        return connection;
    }

    private static void reconnect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://csproject.sit.kmutt.ac.th:3306/db63130500237", "63130500237", "abcd1234");
        logger.info("Reconnected to MySQL Server");
    }

}
