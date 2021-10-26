package com.niit.booklibrary.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.booklibrary.model.User;

@Repository
public class UserdaoImpl implements Userdao {

	@Autowired
	SessionFactory sessionFactory;

	public UserdaoImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void save(User user) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(user);

	}

	@Transactional
	public User checkUser(String email, String password) {

		User user = null;

		try {

			Session session = this.sessionFactory.getCurrentSession();

			Query query = session.createQuery("from User where email=:email And password=:password");
			query.setParameter("email", email);
			query.setParameter("password", password);
			user = (User) query.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return user;

	}

	@Override
	@Transactional
	public List<User> getAllUsers() {
		return this.sessionFactory.getCurrentSession().createCriteria(User.class).list();

	}

	@Override
	@Transactional
	public User getUserById(int id) {
		User user = this.sessionFactory.getCurrentSession().get(User.class, id);
		return user;
	}

	@Override
	@Transactional
	public void deleteuser(int id) {
		User user = this.sessionFactory.getCurrentSession().get(User.class,id);
		this.sessionFactory.getCurrentSession().delete(user);

	}

}
