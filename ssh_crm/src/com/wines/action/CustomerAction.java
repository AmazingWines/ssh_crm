package com.wines.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wines.entity.Customer;
import com.wines.entity.PageBean;
import com.wines.service.CustomerService;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	//模型驱动获取
	private Customer customer = new Customer(); 
	@Override
	public Customer getModel() {
		return customer;
	}
	
	//到添加客户页面的方法
	public String toAddPage() {
		return "toAddPage";
	}
	
	//添加客户的方法
	public String add() {
		customerService.add(customer);
		return "add";
	}
	
	private List<Customer> list;
	public List<Customer> getList() {
		return list;
	}

	//客户列表的方法
	public String list() {
		List<Customer> list = customerService.findAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
	
	//修改和删除的方法
	//删除的方法
	public String delete() {
		//使用模型驱动获取表单提交cid
		int cid = customer.getCid();
		//删除规范写法：先根据id查询，再调用方法删除
		Customer customer = customerService.findOne(cid);
		//判断
		if(customer != null) {
			customerService.delete(customer);
		}
		return "delete";
	}
	//修改的方法-1、根据id查询
	public String showCustomer() {
		int cid = customer.getCid();
		Customer customer = customerService.findOne(cid);
		//放到域对象中
		ServletActionContext.getRequest().setAttribute("customer", customer);
		return "showCustomer";
	}
	//修改的方法-2、调用方法修改
	public String update() {
		customerService.update(customer);
		return "update";
	}
	
	//使用属性封装获取当前页
	private Integer currentPage;
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	//客户分页列表的方法
	public String listPage() {
		PageBean pageBean = customerService.listPage(currentPage);
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return "listPage";
	}
	
	//客户列表中条件查询的方法
	public String listCondition() {
		if(customer.getCustName() != null && !"".equals(customer.getCustName())) {
			List<Customer> list = customerService.findCondition(customer);
			ServletActionContext.getRequest().setAttribute("list", list);
		} else {
			list = customerService.findAll();
		}
		return "listCondition";
	}
	
	//到多条件查询页面的方法
	public String toSelectPage() {
		return "toSelectPage";
	}
	
	//多条件查询的方法
	public String moreCondition() {
		List<Customer> list = customerService.findMoreCondition(customer);
		ServletActionContext.getRequest().setAttribute("list", list);
		return "moreCondition";
	}
	
	//客户级别统计分析的方法
	public String countLevel() {
		List list = customerService.findCountLevel();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "countLevel";
	}
	
	//返回所有客户json数据的方法
	public String customerJson() throws Exception {
		List<Customer> list = customerService.findAll();
		//list转换为json数据格式
		//因为json数据格式为名称和值，使用map集合进行数据封装，再转换为json
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", list.size());
		map.put("rows", list);
		//map转换为json数据格式
		String json = JSON.toJSONString(map);
		//使用response对象进行返回
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
		return NONE;
	}
	
	//使用属性封装获取返回给action的page和rows
	private int page; //当前页
	private int rows; //每页记录数
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	//返回分页客户json数据的方法
	public String customerPageJson() throws Exception {
		//开始位置
		int begin = (page - 1) * rows;
		List<Customer> list = customerService.findPageJson(begin, rows);
		//得到总记录数
		int count = customerService.findCount();
		//转换为json数据格式
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", count);
		map.put("rows", list);
		//map转换为json数据格式
		String json = JSON.toJSONString(map);
		//使用response对象进行返回
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
		return NONE;
	}
}
