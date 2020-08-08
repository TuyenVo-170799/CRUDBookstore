package com.javaee.bookstore.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.javaee.bookstore.dao.BookDao;
import com.javaee.bookstore.model.Book;

public class BookDaoImpl implements BookDao {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/book";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "12345";
	
	public Connection getConnection() {
		try {
			Class.forName(DRIVER);
			String url = URL;
			String user = USERNAME;
			String password = PASSWORD;
			return DriverManager.getConnection(url, user, password);
		}
		catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}
	
	@Override
	public boolean insertBook(Book book) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO book (title, author, price) VALUES (?, ?, ?)";
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setFloat(3, book.getPrice());
			
			boolean rowInserted = statement.executeUpdate() > 0;
			connection.commit();
			return rowInserted;
		}
		catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
			}
			catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public void updateBook(Book book) throws SQLException{
		// TODO Auto-generated method stub
		String sql = "UPDATE book SET title = ?, author = ?, price= ? WHERE id=?";
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setFloat(3, book.getPrice());
			statement.setLong(4, book.getId());
			statement.executeUpdate();
			connection.commit();
		}
		catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
			}
			catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	@Override
	public boolean deleteBook(long id) throws SQLException{
		// TODO Auto-generated method stub
		String sql = "DELETE FROM book WHERE id = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			boolean rowDeleted = statement.executeUpdate() > 0;
			connection.commit();
			return rowDeleted;
		}
		catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
			}
			catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public Book findBookById(long id) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM book WHERE id = ?";
		Book book = new Book();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				book.setId(id);
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(resultSet.getString("author"));
				book.setPrice(resultSet.getFloat("price"));
			}
			return book;
		}
		catch (SQLException e) {
			return null;
		}
		finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			}
			catch (SQLException e) {
				return null;
			}
		}
	}

	@Override
	public List<Book> findAllBook() throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM book";
		List<Book> books = new ArrayList<Book>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getLong("id"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(resultSet.getString("author"));
				book.setPrice(resultSet.getFloat("price"));
				books.add(book);
			}
			return books;
		}
		catch (SQLException e) {
			return null;
		}
		finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			}
			catch (SQLException e) {
				return null;
			}
		}
	}
	
	

}
