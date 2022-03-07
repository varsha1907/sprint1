package com.mycompany;

import com.mycompany.config.JpaConfig;
import com.mycompany.config.RootConfig;
import com.mycompany.dao.UserRepository;
import com.mycompany.model.User;
import com.mycompany.util.TestDataGenerator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.array;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.IsNull.notNullValue;

@SpringJUnitConfig(RootConfig.class)
@Transactional
class SpringDataJpaConfigTest {
    @Configuration
    static class TestConfig {
        @Bean
        TestDataGenerator dataGenerator() {
            return new TestDataGenerator();
        }
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestDataGenerator dataGenerator;

    @Test
    void testTxManagerBeanName() {
        PlatformTransactionManager transactionManager = applicationContext.getBean(PlatformTransactionManager.class, "transactionManager");

        assertThat(transactionManager, notNullValue());
    }

    @Test
    void testUserRepositoryBeanName() {
        UserRepository userRepository = applicationContext.getBean(UserRepository.class, "userRepository");

        assertThat(userRepository, notNullValue());
    }

    @Test
    void testEntityManagerFactoryBeanName() {
        EntityManagerFactory entityManagerFactory = applicationContext.getBean(EntityManagerFactory.class, "entityManagerFactory");

        assertThat(entityManagerFactory, notNullValue());
    }

    @Test
    void testUserRepositoryIsNotMarkedAsRepository() {
        Repository repository = UserRepository.class.getAnnotation(Repository.class);

        assertThat(repository, nullValue());
    }

    @Test
    void testRootConfigComponentScan() {
        ComponentScan componentScan = RootConfig.class.getAnnotation(ComponentScan.class);

        String[] basePackages = componentScan.basePackages();
        if (basePackages.length == 0) {
            basePackages = componentScan.value();
        }

        assertThat(basePackages, array(equalTo("com.mycompany")));
    }

    @Test
    void testJpaConfigRepositoriesPackage() {
        EnableJpaRepositories enableJpaRepositories = JpaConfig.class.getAnnotation(EnableJpaRepositories.class);

        assertThat(enableJpaRepositories.basePackages(), array(equalTo("com.mycompany.dao")));
    }

    @Test
    void testSaveUser() {
        User user = dataGenerator.generateUser();

        userRepository.save(user);

        assertThat(user.getId(), notNullValue());
    }

    @Test
    void testFindAll() {
        Stream.generate(dataGenerator::generateUser).limit(10).forEach(userRepository::save);

        List<User> users = userRepository.findAll();

        assertThat(users.size(), is(10));
    }
}
