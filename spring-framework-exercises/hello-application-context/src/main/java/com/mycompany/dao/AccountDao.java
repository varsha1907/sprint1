package com.mycompany.dao;

import java.util.List;

import com.mycompany.model.Account;

/**
 * Defines an API for {@link Account} data access object (DAO).
 */
public interface AccountDao {
    List<Account> findAll();
}
