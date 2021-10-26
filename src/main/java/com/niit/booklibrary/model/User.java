package com.niit.booklibrary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Email;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Size(min=3,message="Minimum 3 charectors are required")
	private String firstName;
	@Size(min=3,message="Minimum 3 charectors are required")
	private String lastName;
	@Email(message="Invailed Email!! Enter a valid Email")
	private String email;
	@Size(min=8,message="Minimum 8 charectors are required")
	private String password;
	
	@Digits(integer = 10, fraction = 0,message = "Maximum 10 digit only")
	private String phone;
	
	@Size(min=10,message="Minimum 10 charectors are required")
	private String address;
	
	 @Column(columnDefinition = "varchar(255) default 'normalUser'")
	private String userType;
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	

}
