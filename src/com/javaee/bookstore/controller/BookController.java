package com.javaee.bookstore.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaee.bookstore.dao.BookDao;
import com.javaee.bookstore.dao.impl.BookDaoImpl;
import com.javaee.bookstore.model.Book;

public class BookController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private BookDao bookDao = new BookDaoImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/list":
				showListBook(request, response);
				break;
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insert(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				update(request, response);
				break;
			case "/delete":
				delete(request, response);
				break;
			default:
				showListBook(request, response);
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// function show list book page
	private void showListBook(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		List<Book> books = bookDao.findAllBook();
		request.setAttribute("books", books);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/list.jsp");
		dispatcher.forward(request, response);
	}

	// function show insert book page
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/insert_form.jsp");
		dispatcher.forward(request, response);
	}

	// function insert book into database
	private void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		float price = Float.parseFloat(request.getParameter("price"));
		Book book = new Book(title, author, price);
		bookDao.insertBook(book);
		response.sendRedirect("/list");
	}
	
	// function show edit page
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		Book book = bookDao.findBookById(id);
		request.setAttribute("book", book);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/edit_form.jsp");
		dispatcher.forward(request, response);
	}
	
	// function update book into database
	private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		float price = Float.parseFloat(request.getParameter("price"));
		bookDao.updateBook(new Book(id, title, author, price));
		response.sendRedirect("/list");
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		bookDao.deleteBook(id);
		response.sendRedirect("/list");
	}
}
