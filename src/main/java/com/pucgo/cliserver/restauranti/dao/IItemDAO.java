package com.pucgo.cliserver.restauranti.dao;

import com.pucgo.cliserver.restauranti.model.Item;

public interface IItemDAO {

    Item crateItem(Item item);
    Item findById(Long id);
}
