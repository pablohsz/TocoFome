package com.pucgo.cliserver.restauranti.dao;

import com.pucgo.cliserver.restauranti.model.User;

import java.util.Optional;

public interface IUserDAO {

    boolean createUser(User user);
    Optional<User> findByUsername(String username);
}
