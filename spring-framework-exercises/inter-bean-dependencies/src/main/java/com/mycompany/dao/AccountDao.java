package com.mycompany.dao;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.mycompany.model.Account;


public interface AccountDao {
    Account findById(Long id);
}
