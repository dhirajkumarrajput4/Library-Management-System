package com.niit.booklibrary.dao;

import java.util.List;

import com.niit.booklibrary.model.Book;

public interface BookDAO {
	
	public void save(Book book);
	public List<Book> getBooks();
	public Book getBookById(int id);
	public void deleteBook(int id);
	public List<Book> getBooksByAuthorName(String SearchValue);
	public List<Book> getBooksByBookName(String SearchValue);
	

}
