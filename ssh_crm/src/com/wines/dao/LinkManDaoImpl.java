package com.wines.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.wines.entity.LinkMan;

public class LinkManDaoImpl implements LinkManDao {
	//得到hibernateTemplate对象
    private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	//添加联系人的方法
	@Override
	public void add(LinkMan linkMan) {
		hibernateTemplate.save(linkMan);
	}

	//联系人列表的方法
	@Override
	public List<LinkMan> findAll() {
		return (List<LinkMan>) hibernateTemplate.find("from LinkMan");
	}

	//根据id查询的方法
	@Override
	public LinkMan findOne(int linkid) {
		return hibernateTemplate.get(LinkMan.class, linkid);
	}

	//列表中修改的方法
	@Override
	public void update(LinkMan linkMan) {
		hibernateTemplate.update(linkMan);
	}

	//多条件查询的方法
	@Override
	public List<LinkMan> findMoreCondition(LinkMan linkMan) {
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		if(linkMan.getLkmName() != null && !"".equals(linkMan.getLkmName())) {
			criteria.add(Restrictions.eq("lkmName", linkMan.getLkmName()));
		}
		if(linkMan.getCustomer().getCid() != null && linkMan.getCustomer().getCid() > 0) {
			criteria.add(Restrictions.eq("customer.cid", linkMan.getCustomer().getCid()));
		}
		return (List<LinkMan>) hibernateTemplate.findByCriteria(criteria);
	}
}
