package com.pucgo.cliserver.restauranti.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private ConnectionFactory(){}

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/restauranTI",
                    "postgres",
                    "520090"
            );
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
}
