package com.mycompany;

import com.mycompany.config.JpaConfig;
import com.mycompany.config.RootConfig;
import com.mycompany.dao.UserRepository;
import com.mycompany.exception.EntityNotFoundException;
import com.mycompany.model.RoleType;
import com.mycompany.model.User;
import com.mycompany.service.UserService;
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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.array;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@SpringJUnitConfig(RootConfig.class)
@Transactional
class UserServiceAppTest {
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

    @Autowired
    private UserService userService;

    @PersistenceContext
    private EntityManager entityManager;

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

        String[] basePackages = enableJpaRepositories.basePackages();
        if (basePackages.length == 0) {
            basePackages = enableJpaRepositories.value();
        }

        assertThat(basePackages, array(equalTo("com.mycompany.dao")));
    }


    @Test
    void testFindUsersByCity() {
        List<User> userList = Stream.generate(dataGenerator::generateUser).limit(10).collect(toList());
        userRepository.saveAll(userList);
        entityManager.flush();
        userList.forEach(entityManager::detach);

        String city = userList.get(0).getAddress().getCity();
        List<User> cityUsers = userService.findByCity(city);

        assertThat(cityUsers, hasSize(greaterThan(0)));
        assertThat(cityUsers, everyItem(hasProperty("address", hasProperty("city", equalTo(city)))));
    }

    @Test
    void testFindUsersByCityIsReadOnly() throws NoSuchMethodException {
        Transactional transactional = UserService.class.getMethod("findByCity", String.class)
                .getAnnotation(Transactional.class);

        assertThat(transactional.readOnly(), is(true));
    }

    @Test
    void testGetUserByEmail() {
        User generatedUser = dataGenerator.generateUser();
        userRepository.save(generatedUser);
        entityManager.flush();
        entityManager.detach(generatedUser);

        User foundUser = userService.getByEmail(generatedUser.getEmail());

        assertThat(foundUser, equalTo(generatedUser));
    }

    @Test
    void testGetUserByEmailFetchesRoles() {
        User generatedUser = dataGenerator.generateUser();
        userRepository.save(generatedUser);
        entityManager.flush();
        entityManager.detach(generatedUser);

        User foundUser = userService.getByEmail(generatedUser.getEmail());

        assertTrue(Persistence.getPersistenceUtil().isLoaded(foundUser, "roles"));
    }

    @Test
    void testGetUserByEmailForUserWithoutRoles() {
        User generatedUserWithoutRoles = dataGenerator.generateUserWithoutRoles();
        userRepository.save(generatedUserWithoutRoles);
        entityManager.flush();
        entityManager.detach(generatedUserWithoutRoles);

        User foundUser = userService.getByEmail(generatedUserWithoutRoles.getEmail());

        assertThat(foundUser, notNullValue());
    }

    @Test
    void testGetUserByEmailFetchesAddress() {
        User generatedUser = dataGenerator.generateUser();
        userRepository.save(generatedUser);
        entityManager.flush();
        entityManager.detach(generatedUser);

        User foundUser = userService.getByEmail(generatedUser.getEmail());

        assertTrue(Persistence.getPersistenceUtil().isLoaded(foundUser, "address"));
    }

    @Test
    void testGetUserByEmailForUserWithoutAddress() {
        User generatedUserWithoutAddress = dataGenerator.generateUser();
        generatedUserWithoutAddress.setAddress(null);
        userRepository.save(generatedUserWithoutAddress);
        entityManager.flush();
        entityManager.detach(generatedUserWithoutAddress);

        User foundUser = userService.getByEmail(generatedUserWithoutAddress.getEmail());

        assertThat(foundUser, notNullValue());
    }

    @Test
    void testGetByEmailIsReadOnly() throws NoSuchMethodException {
        Transactional transactional = UserService.class.getMethod("getByEmail", String.class)
                .getAnnotation(Transactional.class);

        assertThat(transactional.readOnly(), is(true));
    }

    @Test
    void testGetUserByNotExistingEmail() {
        try {
            User foundUser = userService.getByEmail("xxx@gmail.com");
            fail("Exception should be thrown");
        } catch (Exception e) {
            assertTrue(e instanceof EntityNotFoundException);
            assertThat(e.getMessage(), equalTo(String.format("Cannot find user by email %s", "xxx@gmail.com")));
        }
    }

    @Test
    void testAddRoleToAllUsers() {
        List<User> userList = Stream.generate(dataGenerator::generateUser).limit(10).collect(toList());
        userRepository.saveAll(userList);

        userService.addRoleToAllUsers(RoleType.OPERATOR);
        entityManager.flush();
        userList.forEach(entityManager::detach);

        List<User> users = userRepository.findAll();

        assertThat(users, everyItem(hasProperty("roles",
                hasItem(hasProperty("roleType", is(RoleType.OPERATOR))))));
    }

    @Test
    void testAddRoleToAllUsersIncludingUsersWithoutRoles() {
        List<User> userList = Stream.generate(dataGenerator::generateUser).limit(10).collect(toList());
        User userWithoutRoles = dataGenerator.generateUserWithoutRoles();
        userList.add(userWithoutRoles);

        userRepository.saveAll(userList);

        userService.addRoleToAllUsers(RoleType.OPERATOR);
        entityManager.flush();
        userList.forEach(entityManager::detach);

        List<User> users = userRepository.findAll();

        assertThat(users, everyItem(hasProperty("roles",
                hasItem(hasProperty("roleType", is(RoleType.OPERATOR))))));
    }

    @Test
    void testAddRoleToAllUsersDoesntAddDuplicates() {
        User user = dataGenerator.generateUser(RoleType.USER, RoleType.OPERATOR);
        userRepository.save(user);

        userService.addRoleToAllUsers(RoleType.OPERATOR);

        entityManager.flush();
        entityManager.detach(user);

        User foundUser = entityManager.find(User.class, user.getId());

        assertThat(foundUser.getRoles(), hasSize(2));
    }


}
