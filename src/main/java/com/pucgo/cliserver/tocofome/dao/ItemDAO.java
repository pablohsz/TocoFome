package com.pucgo.cliserver.tocofome.dao;

import com.pucgo.cliserver.tocofome.config.ConnectionFactory;
import com.pucgo.cliserver.tocofome.model.Item;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ItemDAO implements IItemDAO{
    @Override
    public List<Item> findAll() {
        String query = "SELECT * FROM items";
        List<Item> list = new ArrayList<>();
        try (Connection  connection = ConnectionFactory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeQuery();
            //recuperando matricula do aluno que foi inserido no banco de dados

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Item item = new Item();
                item.setId(resultSet.getLong("id"));
                item.setProduct(resultSet.getString("product"));
                item.setDescription(resultSet.getString("description"));
                item.setImageUrl(resultSet.getString("image_url"));
                item.setPrice(resultSet.getBigDecimal("price"));
                list.add(item);
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
