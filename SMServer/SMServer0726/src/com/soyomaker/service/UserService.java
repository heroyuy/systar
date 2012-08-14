package com.soyomaker.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soyomaker.model.Player;
import com.soyomaker.model.User;

@Service("userService")
@Transactional
public class UserService extends AbstractService {

	public User findUserByUsername(String username) {
		return findUnique("from User u where u.username=?", username);
	}

	public boolean saveUser(User user) {
		try {
			hibernateRepository.save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean savePlayer(Player player) {
		try {
			hibernateRepository.save(player);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
