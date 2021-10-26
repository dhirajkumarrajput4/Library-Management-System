package com.niit.booklibrary.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int studentid;
	@Size(min = 3,message = "Name cantain 3 charecter")
	private String studentname;
	@Size(min = 3,message = "Name cantain 3 charecter")
	private String fathername;
	@Size(min = 6,message = "Name cantain 6 charecter")
	private String collage;
	@ManyToMany
	private List<Book> b;
	
	public List<Book> getB() {
		return b;
	}
	public void setB(List<Book> b) {
		this.b = b;
	}
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getFathername() {
		return fathername;
	}
	public void setFathername(String fathername) {
		this.fathername = fathername;
	}
	public String getCollage() {
		return collage;
	}
	public void setCollage(String collage) {
		this.collage = collage;
	}

}
