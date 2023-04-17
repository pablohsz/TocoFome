package com.pucgo.cliserver.tocofome.dao;

import com.pucgo.cliserver.tocofome.model.User;

import java.util.Optional;

public interface IUserDAO {

    boolean createUser(User user);
    Optional<User> findByUsername(String username);
}
