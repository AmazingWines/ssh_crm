package com.wines.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.wines.entity.Customer;

public class CustomerDaoImpl implements CustomerDao {
	//得到hibernateTemplate对象
    private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	//添加客户功能的方法
	@Override
	public void add(Customer customer) {
		hibernateTemplate.save(customer);
	}

	//客户列表的方法
	@Override
	public List<Customer> findAll() {
		return (List<Customer>) hibernateTemplate.find("from Customer");
	}

	//根据id查询的方法
	@Override
	public Customer findOne(int cid) {
		return hibernateTemplate.get(Customer.class, cid);
	}

	//列表中删除的方法
	@Override
	public void delete(Customer customer) {
		hibernateTemplate.delete(customer);
	}

	//列表中修改的方法
	@Override
	public void update(Customer customer) {
		hibernateTemplate.update(customer);
	}

	//客户分页的方法-1、查询记录数
	@Override
	public int findCount() {
		List<Object> list = (List<Object>) hibernateTemplate.find("select count(*) from Customer");
		if(list.get(0) != null && list.size() != 0) {
			Object obj = list.get(0);
			Long longObj = (Long) obj;
			int count = longObj.intValue();
			return count;
		}
		return 0;
	}
	//客户分页的方法-2、查询分页列表
	@Override
	public List<Customer> findList(int begin, int pageSize) {
		//使用离线对象
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		List<Customer> list = (List<Customer>) hibernateTemplate.findByCriteria(criteria, begin, pageSize);
		return list;
	}

	//客户列表条件查询的方法
	@Override
	public List<Customer> findCondition(Customer customer) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		criteria.add(Restrictions.like("custName", "%"+customer.getCustName()+"%"));
		List<Customer> list = (List<Customer>) hibernateTemplate.findByCriteria(criteria);
		return list;
	}

	//多条件查询的方法
	@Override
	public List<Customer> findMoreCondition(Customer customer) {
		/*//原始方法-拼接hql语句
		String hql = "from Customer where 1=1";
		List<Object> list = new ArrayList<Object>();
		if(customer.getCustName() != null && !"".equals(customer.getCustName())) {
			hql += " and custName=?";
			list.add(customer.getCustName());
		}
		if(customer.getCustLevel() != null && !"".equals(customer.getCustLevel())) {
			hql += " and custLevel=?";
			list.add(customer.getCustLevel());
		}
		if(customer.getCustSource() != null && !"".equals(customer.getCustSource())) {
			hql += " and custSource=?";
			list.add(customer.getCustSource());
		}
		return (List<Customer>) hibernateTemplate.find(hql, list.toArray());*/
		
		//使用离线对象实现多条件组合查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		if(customer.getCustName() != null && !"".equals(customer.getCustName())) {
			criteria.add(Restrictions.eq("custName", customer.getCustName()));
		}
		if(customer.getCustLevel() != null && !"".equals(customer.getCustLevel())) {
			criteria.add(Restrictions.eq("custLevel", customer.getCustLevel()));
		}
		if(customer.getCustSource() != null && !"".equals(customer.getCustSource())) {
			criteria.add(Restrictions.eq("custSource", customer.getCustSource()));
		}
		return (List<Customer>) hibernateTemplate.findByCriteria(criteria);
	}

	//客户级别统计分析的方法
	@Override
	public List findCountLevel() {
		//因为写复杂语句，所以直接调用底层sql语句实现
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery("select count(*) as num,custLevel from t_customer group by custLevel");
		//返回数据转换为map结构
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		List list = sqlQuery.list();
		return list;
	}
}
