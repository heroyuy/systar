package com.soyomaker.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soyomaker.model.User;

/**
 * @author chenwentao
 *
 */
@Service("userService")
@Transactional
public class UserService extends AbstractService<User> {

	public User findByUsername(String username) {
		return findUnique("from User u where u.username=?", username);
	}
}
