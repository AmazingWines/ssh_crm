package com.wines.dao;

import java.util.List;

import com.wines.entity.LinkMan;

public interface LinkManDao {
	void add(LinkMan linkMan);

	List<LinkMan> findAll();

	LinkMan findOne(int linkid);

	void update(LinkMan linkMan);

	List<LinkMan> findMoreCondition(LinkMan linkMan);
}
