package com.niit.booklibrary.dao;

import java.util.List;

import com.niit.booklibrary.model.User;

public interface Userdao {
	public void save(User user);
	public User checkUser(String email,String password);
	
	public List<User> getAllUsers();
	public User getUserById(int id);
	public void deleteuser(int id);
	

}
