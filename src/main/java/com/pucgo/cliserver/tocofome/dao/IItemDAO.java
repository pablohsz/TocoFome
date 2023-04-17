package com.pucgo.cliserver.tocofome.dao;

import com.pucgo.cliserver.tocofome.model.Item;

import java.util.List;

public interface IItemDAO {
    List<Item> findAll();
}
