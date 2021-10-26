package com.niit.booklibrary.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.booklibrary.model.Student;

@Repository
public class BookIssueDAOImpl implements BookIssueDAO {
	@Autowired
	SessionFactory sessionFactory;

	public BookIssueDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	
	@Autowired
	List<Student> std;
	
	@Override
	@Transactional
	public void issueBook(int sid, int bid) {

		Session session = this.sessionFactory.getCurrentSession();
		session.createQuery("insert");
	}

}
