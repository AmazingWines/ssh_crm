package com.wines.dao;

import java.util.List;

import com.wines.entity.Customer;

public interface CustomerDao {
	void add(Customer customer);

	List<Customer> findAll();

	Customer findOne(int cid);

	void delete(Customer customer);

	void update(Customer customer);

	int findCount();

	List<Customer> findList(int begin, int pageSize);

	List<Customer> findCondition(Customer customer);

	List<Customer> findMoreCondition(Customer customer);

	List findCountLevel();
}
