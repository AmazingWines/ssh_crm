package com.wines.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.wines.entity.User;

public class UserDaoImpl implements UserDao {
	//得到hibernateTemplate对象
    private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	//登录的方法
	@Override
	public User login(User user) {
		//登录的查询操作
		//根据用户名和密码查询
		List<User> list = (List<User>) hibernateTemplate.find("from User where username=? and password=?", user.getUsername(), user.getPassword());
		//返回user对象
		//判断
		if(list != null && list.size() != 0) {
			User userCheck = list.get(0);
			return userCheck;
		}
		return null;
	}

	//到添加客户拜访页面-1、查询所有用户的方法
	@Override
	public List<User> findAll() {
		return (List<User>) hibernateTemplate.find("from User");
	}
}
