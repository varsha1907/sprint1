package com.mycompany.dao;

import java.util.List;

import com.mycompany.model.jpa.User;

/**
 * User Data Access Object (DAO) API
 */
public interface UserDao {
    List<User> findAll();

    User findById(long id);

    void save(User user);
}
