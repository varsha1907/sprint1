package com.mycompany.service;

import com.mycompany.dao.UserDao;
import com.mycompany.model.jpa.Role;
import com.mycompany.model.jpa.RoleType;
import com.mycompany.model.jpa.User;

import java.util.List;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.stream.Collectors.toList;

/**
 * This class proovides {@link User} related service logic.
 * <p>
 * todo: 1. Configure {@link UserService} bean as spring service
 * todo: 2. Inject {@link UserDao} using constructor-based injection
 * todo: 3. Enable transaction management on class level
 * todo: 4. Configure {@link UserService#getAll()} as read-only method
 * todo: 4. Configure {@link UserService#getAllAdmins()} as read-only method
 */

@Service
@Transactional
public class UserService {
    private UserDao userDao;

    
    public UserService(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	public void save(User user) {
        userDao.save(user);
    }
	
	@Transactional(readOnly=true)
    public List<User> getAll() {
        return userDao.findAll();
    }
	
	@Transactional(readOnly = true)
    public List<User> getAllAdmins() {
     //   throw new UnsupportedOperationException("Don't be lazy and implement the method");
        return userDao.findAll().stream()
                .filter(user -> user.getRoles().stream()
                        .map(Role::getRoleType)
                        .anyMatch(roleType -> roleType.equals(RoleType.ADMIN)))
                .collect(toList());
	}

    public void addRole(Long userId, RoleType roleType) {
        User user = userDao.findById(userId);
        Role role = Role.valueOf(roleType);
        user.addRole(role);
    }
}
