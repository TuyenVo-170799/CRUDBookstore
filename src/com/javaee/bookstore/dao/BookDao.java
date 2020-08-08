package com.javaee.bookstore.dao;

import java.sql.SQLException;
import java.util.List;

import com.javaee.bookstore.model.Book;

public interface BookDao {
	
	boolean insertBook(Book book) throws SQLException;
	void updateBook(Book book) throws SQLException;
	boolean deleteBook(long id) throws SQLException;
	Book findBookById(long id) throws SQLException;
	List<Book> findAllBook() throws SQLException;

}
