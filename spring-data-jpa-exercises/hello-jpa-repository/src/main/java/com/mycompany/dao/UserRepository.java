package com.mycompany.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.model.User;

/**
 * This interface represents a data access object (DAO) for {@link User}.
 * <p>
 * todo: 1. Configure {@link UserRepository} as {@link JpaRepository} for class User
 */
public interface UserRepository extends JpaRepository<User,Long> {

}
