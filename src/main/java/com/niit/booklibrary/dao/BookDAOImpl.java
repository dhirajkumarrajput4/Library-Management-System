package com.niit.booklibrary.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.booklibrary.model.Book;

@Repository
public class BookDAOImpl implements BookDAO {
	@Autowired
	SessionFactory sessionFactory;

	public BookDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void save(Book book) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(book);

	}

	@Transactional
	public List<Book> getBooks() {

		return this.sessionFactory.getCurrentSession().createCriteria(Book.class).list();

	}

	@Transactional
	public Book getBookById(int id) {
		return this.sessionFactory.getCurrentSession().get(Book.class, id);

	}

	@Transactional
	public void deleteBook(int id) {
		Book book = this.sessionFactory.getCurrentSession().get(Book.class, id);
		this.sessionFactory.getCurrentSession().delete(book);
	}

	@Transactional
	public List<Book> getBooksByAuthorName(String SearchValue) {

		List<Book> b=null;
		try {

			Session session = this.sessionFactory.getCurrentSession();
			Query qry = session.createQuery("from Book where bookauthor  like:startwith");
			qry.setParameter("startwith", "%" + SearchValue + "%");
			 b = qry.getResultList();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}

		return b;
	
	}

	@Transactional
	public List<Book> getBooksByBookName(String SearchValue) {
		List<Book> b=null;
		try {

			Session session = this.sessionFactory.getCurrentSession();
			Query qry = session.createQuery("from Book where booktitle  like:startwith");
			qry.setParameter("startwith", "%" + SearchValue + "%");
			 b = qry.getResultList();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}

		return b;
	}

}
