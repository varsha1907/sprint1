package com.mycompany.service;

import com.mycompany.dao.UserRepository;
import com.mycompany.exception.EntityNotFoundException;
import com.mycompany.model.RoleType;
import com.mycompany.model.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class provides service logic for {@link User}.
 * <p>
 * todo: 0. PLEASE NOTE, THAT SOME REQUIRED STEPS ARE OMITTED IN THE TODO LIST AND YOU HAVE TO DO IT ON YOUR OWN
 * <p>
 * todo: 1. Implement {@link UserService#findByCity(String)} using {@link UserRepository}, make method read only
 * todo: 2. Implement {@link UserService#getByEmail(String)} using {@link UserRepository}, make method read only
 * todo: 3. In case user is not found by email, throw {@link EntityNotFoundException} with message "Cannot find user by email ${email}"
 * todo: 4. Implement {@link UserService#addRoleToAllUsers(RoleType)} using {@link UserRepository}
 */

@Transactional
@Service
public class UserService {
	
	private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
    @Transactional(readOnly = true)
    public List<User> findByCity(String city) {
       // throw new UnsupportedOperationException("Do your best and implement this method!");
    	return userRepository.findAllByAddressCity(city);
    }

    
    @Transactional(readOnly = true)
    public User getByEmail(String email) {
      //  throw new UnsupportedOperationException("Do your best and implement this method!");
    	 return userRepository.findByEmailFetchRoles(email)
                 .orElseThrow(() -> new EntityNotFoundException(String.format("Cannot find user by email %s", email)));
    
    }

    public void addRoleToAllUsers(RoleType roleType) {
      //  throw new UnsupportedOperationException("Do your best and implement this method!");
    	userRepository.addRoleToAllUsers(roleType);
    }
}
