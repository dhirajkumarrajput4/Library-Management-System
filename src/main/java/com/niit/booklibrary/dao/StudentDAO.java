package com.niit.booklibrary.dao;

import java.util.List;

import com.niit.booklibrary.model.Student;



public interface StudentDAO {

	public void save(Student student);
	public List<Student> getStudents();
	public Student getStudentById(int id);
	public void deleteStudent(int id);
}
