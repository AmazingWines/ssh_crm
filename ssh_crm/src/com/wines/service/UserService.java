package com.wines.service;

import java.util.List;

import com.wines.dao.UserDao;
import com.wines.entity.User;

public class UserService {
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	//登录的方法
	public User login(User user) {
		//调用dao的方法
		return userDao.login(user);
	}

	public List<User> findAll() {
		return userDao.findAll();
	}
}
