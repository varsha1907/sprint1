package com.mycompany.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mycompany.dao.AccountDao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccountService {
	
    public AccountService(AccountDao fakeAccountDao) {
		
	}
    @Autowired
	private AccountDao accountDao ;

	
	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	
	
	 
	

	

}
