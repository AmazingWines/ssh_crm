package com.wines.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.wines.entity.User;
import com.wines.service.UserService;

public class UserAction extends ActionSupport {
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	//属性封装获取
	//登录
	public String login() {
		//封装到实体类对象
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		//调用service的方法实现
		User userExist = userService.login(user);
		//判断
		if(userExist != null) {
			//成功
			//使用session保持登录状态
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("user", userExist);
			return "loginsuccess";
		} else {
			//失败
			return "loginfail";
		}
	}
}
