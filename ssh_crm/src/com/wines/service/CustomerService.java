package com.wines.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wines.dao.CustomerDao;
import com.wines.entity.Customer;
import com.wines.entity.PageBean;

@Transactional
public class CustomerService {
	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	public void add(Customer customer) {
		customerDao.add(customer);
	}

	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	public Customer findOne(int cid) {
		return customerDao.findOne(cid);
	}

	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

	public void update(Customer customer) {
		customerDao.update(customer);
	}

	public PageBean listPage(Integer currentPage) {
		//封装分页数据到pageBean对象中
		PageBean pageBean = new PageBean();
		//当前页
		pageBean.setCurrentPage(currentPage);
		//总记录数
		int totalCount = customerDao.findCount();
		pageBean.setTotalCount(totalCount);
		//每页显示记录数
		int pageSize = 3;
		//总页数
		int totalPage = 0;
		if(totalCount % pageSize == 0) {
			totalPage = totalCount / pageSize;
		} else {
			totalPage = totalCount / pageSize + 1;
		}
		pageBean.setTotalPage(totalPage);
		//开始位置
		int begin = (currentPage - 1) * pageSize;
		//每页记录的list集合
		List<Customer> list = customerDao.findList(begin, pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	public List<Customer> findCondition(Customer customer) {
		return customerDao.findCondition(customer);
	}

	public List<Customer> findMoreCondition(Customer customer) {
		return customerDao.findMoreCondition(customer);
	}

	public List findCountLevel() {
		return customerDao.findCountLevel();
	}

	public List<Customer> findPageJson(int begin, int rows) {
		return customerDao.findList(begin, rows);
	}

	public int findCount() {
		return customerDao.findCount();
	}
}
