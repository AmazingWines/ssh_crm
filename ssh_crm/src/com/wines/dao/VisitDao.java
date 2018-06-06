package com.wines.dao;

import java.util.List;

import com.wines.entity.Visit;

public interface VisitDao {
	void add(Visit visit);

	List<Visit> findAll();
}
