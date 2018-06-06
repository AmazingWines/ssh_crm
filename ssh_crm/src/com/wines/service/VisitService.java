package com.wines.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wines.dao.VisitDao;
import com.wines.entity.Visit;

@Transactional
public class VisitService {
	private VisitDao visitDao;
	public void setVisitDao(VisitDao visitDao) {
		this.visitDao = visitDao;
	}
	
	public void add(Visit visit) {
		visitDao.add(visit);
	}

	public List<Visit> findAll() {
		return visitDao.findAll();
	}
}
