package com.wines.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.wines.entity.Visit;

public class VisitDaoImpl implements VisitDao {
	//得到hibernateTemplate对象
    private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	//添加客户拜访的方法
	@Override
	public void add(Visit visit) {
		hibernateTemplate.save(visit);
	}

	//客户拜访列表的方法
	@Override
	public List<Visit> findAll() {
		return (List<Visit>) hibernateTemplate.find("from Visit");
	}
}
