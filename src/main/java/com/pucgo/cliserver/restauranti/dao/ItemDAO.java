package com.pucgo.cliserver.restauranti.dao;

import com.pucgo.cliserver.restauranti.config.ConnectionFactory;
import com.pucgo.cliserver.restauranti.model.Item;

import java.net.MalformedURLException;
import java.sql.*;


public class ItemDAO implements IItemDAO{
    @Override
    public Item crateItem(Item item) {
        try (Connection connection = ConnectionFactory.getConnection()){
            String query = "INSERT INTO users" +
                    "(product, description, image_url)" +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getProduct());
            statement.setString(2, item.getDescription());
            statement.setString(3, item.getImageUrl().toString());
            statement.executeUpdate();

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                item = new Item (resultSet.getLong("id"),
                        resultSet.getString("product"),
                        resultSet.getString("description"),
                        resultSet.getString("image_url"));
        } catch (SQLException | MalformedURLException e) {
            System.err.print(e.getMessage());
            return null;
        }
        return item;
    }

    @Override
    public Item findById(Long id) {
        String query = "SELECT * FROM items WHERE id = ?";
        Item item = new Item();
        try (Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                item = new Item(resultSet.getLong("id"),
                        resultSet.getString("product"),
                        resultSet.getString("description"),
                        resultSet.getString("image_url")
                        );
        } catch (SQLException| MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return item;
    }
}
