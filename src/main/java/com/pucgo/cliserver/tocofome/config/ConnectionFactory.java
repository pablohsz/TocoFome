package com.pucgo.cliserver.tocofome.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private ConnectionFactory(){}

    static Properties prop;
    public static Connection getConnection() throws IOException {

        startProperties();
        String user = prop.getProperty("db.username");
        String password = prop.getProperty("db.password");

        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/tocofome",
                    user,
                    password
            );
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void startProperties() throws IOException {
        prop = new Properties();
        InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("config.properties");
        prop.load(input);
    }
}
