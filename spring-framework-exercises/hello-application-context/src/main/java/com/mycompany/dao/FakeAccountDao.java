package com.mycompany.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.mycompany.TestDataGenerator;
import com.mycompany.model.Account;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Provides a fake {@link AccountDao} implementation that uses generated fake data.
 * <p>
 * todo: configure this class as Spring component with bean name "accountDao"
 * todo: use explicit (with {@link Autowired} annotation) constructor-based dependency injection
 */
@Component("accountDao")
public class FakeAccountDao implements AccountDao {
    private List<Account> accounts;

    @Autowired
    public FakeAccountDao(TestDataGenerator testDataGenerator) {
        this.accounts = Stream.generate(testDataGenerator::generateAccount)
                .limit(20)
                .collect(toList());
    }

    @Override
    public List<Account> findAll() {
        return accounts;
    }
}
