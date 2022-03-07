package com.mycompany.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mycompany.TestDataGenerator;
import com.mycompany.dao.AccountDao;
import com.mycompany.dao.impl.FakeAccountDao;
import com.mycompany.service.AccountService;

/**
 * todo: Refactor {@link RootConfig} in order to user inter-bean dependencies properly.
 */
@Component
public final class RootConfig {
	
    @Bean
    public AccountService accountService() {
        return new AccountService(fakeAccountDao());
    }

    @Bean("accountDao")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public final AccountDao fakeAccountDao() {
        return new FakeAccountDao(dataGenerator());
    }

    @Bean("dataGenerator")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    private TestDataGenerator dataGenerator() {
        return new TestDataGenerator();
    }
}
