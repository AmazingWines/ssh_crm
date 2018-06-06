package com.wines.dao;

import java.util.List;

import com.wines.entity.User;

public interface UserDao {
	User login(User user);

	List<User> findAll();
}
