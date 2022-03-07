package com.mycompany.service;

import com.mycompany.dao.AccountDao;
import com.mycompany.model.Account;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * Provides service API for {@link Account}.
 * <p>
 * todo: configure {@link AccountService} bean implicitly using special annotation for service classes
 * todo: use implicit constructor-based dependency injection (don't use {@link org.springframework.beans.factory.annotation.Autowired})
 */
@Service
public class AccountService {
	
    private final AccountDao accountDao;

    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public Account findRichestAccount() {
        List<Account> accounts = accountDao.findAll();
        return accounts.stream()
                .max(Comparator.comparing(Account::getBalance))
                .get();
    }

}
