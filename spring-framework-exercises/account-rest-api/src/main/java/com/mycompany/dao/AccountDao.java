package com.mycompany.dao;

import java.util.List;

import com.mycompany.model.Account;

public interface AccountDao {
    List<Account> findAll();

    Account findById(long id);

    Account save(Account account);

    void remove(Account account);
}
