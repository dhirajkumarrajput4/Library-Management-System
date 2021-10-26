package com.niit.booklibrary.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.booklibrary.model.Student;

@Repository
public class StudentDAOImpl implements StudentDAO {

	@Autowired
	SessionFactory sessionfactory;

	public StudentDAOImpl(SessionFactory sessionfactory) {
		super();
		this.sessionfactory = sessionfactory;
	}

	@Override
	@Transactional
	public void save(Student student) {
		this.sessionfactory.getCurrentSession().saveOrUpdate(student);

	}

	@Override
	@Transactional
	public List<Student> getStudents() {
		return this.sessionfactory.getCurrentSession().createCriteria(Student.class).list();

	}

	@Override
	@Transactional
	public Student getStudentById(int id) {
		Student student = this.sessionfactory.getCurrentSession().get(Student.class, id);
		return student;

	}

	@Override
	@Transactional
	public void deleteStudent(int id) {
		Student student = this.sessionfactory.getCurrentSession().get(Student.class, id);
		this.sessionfactory.getCurrentSession().delete(student);

	}

}
