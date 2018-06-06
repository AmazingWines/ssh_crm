package com.wines.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wines.entity.Customer;
import com.wines.entity.User;
import com.wines.entity.Visit;
import com.wines.service.CustomerService;
import com.wines.service.UserService;
import com.wines.service.VisitService;

public class VisitAction extends ActionSupport implements ModelDriven<Visit> {
	private VisitService visitService;
	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}
	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	private Visit visit = new Visit();
	@Override
	public Visit getModel() {
		return visit;
	}

	//到添加页面的方法
	public String toAddPage() {
		//查询所有用户
		List<User> listUser = userService.findAll();
		//查询所有客户
		List<Customer> listCustomer = customerService.findAll();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listUser", listUser);
		request.setAttribute("listCustomer", listCustomer);
		return "toAddPage";
	}
	
	//添加客户拜访的方法
	public String addVisit() {
		visitService.add(visit);
		return "addVisit";
	}
	
	//客户拜访列表的方法
	public String list() {
		List<Visit> list = visitService.findAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
}
