package com.wines.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wines.dao.LinkManDao;
import com.wines.entity.LinkMan;

@Transactional
public class LinkManService {
	private LinkManDao linkManDao;
	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}
	
	public void add(LinkMan linkMan) {
		linkManDao.add(linkMan);
	}

	public List<LinkMan> findAll() {
		return linkManDao.findAll();
	}

	public LinkMan findOne(int linkid) {
		return linkManDao.findOne(linkid);
	}

	public void update(LinkMan linkMan) {
		linkManDao.update(linkMan);
	}

	public List<LinkMan> findMoreCondition(LinkMan linkMan) {
		return linkManDao.findMoreCondition(linkMan);
	}
}
