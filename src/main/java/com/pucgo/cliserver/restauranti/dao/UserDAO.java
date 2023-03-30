package com.pucgo.cliserver.restauranti.dao;

import com.pucgo.cliserver.restauranti.config.ConnectionFactory;
import com.pucgo.cliserver.restauranti.model.User;

import java.sql.*;
import java.util.Optional;

public class UserDAO implements IUserDAO{
    @Override
    public boolean createUser(User user) {
        try (Connection connection = ConnectionFactory.getConnection()){
            String query = "INSERT INTO users" +
                    "(username, name, email, password)" +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Optional<User> findByUsername(String username) {

        String query = "SELECT * FROM users WHERE username = ?";
        User user = new User();
        try (Connection  connection = ConnectionFactory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.executeQuery();

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                user = new User(resultSet.getString("username"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(user);
    }
}
