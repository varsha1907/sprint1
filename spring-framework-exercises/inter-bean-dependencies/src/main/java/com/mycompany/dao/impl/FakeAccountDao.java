package com.mycompany.dao.impl;

import java.util.function.IntPredicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.mycompany.TestDataGenerator;
import com.mycompany.dao.AccountDao;
import com.mycompany.model.Account;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FakeAccountDao implements AccountDao {
	
	@Autowired
    private  TestDataGenerator dataGenerator ;

    public FakeAccountDao(TestDataGenerator dataGenerator2) {
	}

	
	  @Override public Account findById(Long id) 
	  { 
		  Account account =
		dataGenerator.generateAccount();
		  account.setId(id); 
		  return account; }


	public TestDataGenerator getDataGenerator() {
		return dataGenerator;
	}


	public void setDataGenerator(TestDataGenerator dataGenerator) {
		this.dataGenerator = dataGenerator;
	}
	 
	
	
	

	
	
}
