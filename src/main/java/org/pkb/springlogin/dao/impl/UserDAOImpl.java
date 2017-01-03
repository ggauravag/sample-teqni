package org.pkb.springlogin.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.pkb.springlogin.dao.UserDAO;
import org.pkb.springlogin.model.Role;
import org.pkb.springlogin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findUserInfo(String userName) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, userName);
		return user;
	}

	@Override
	public void createUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}

	@Override
	public Role getRole(String name) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Role.class);
		criteria.add(Restrictions.eq("name", name));
		Role role = (Role) criteria.uniqueResult();
		return role;
	}

}