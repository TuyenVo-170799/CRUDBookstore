<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Page</title>
</head>
<body>
	<div align="center">
		<h1>Books Management</h1>
		<h2>
			<a href="/new">Add New Book</a> &nbsp;&nbsp;&nbsp; 
			<a href="/list">List All Books</a>
		</h2>
	</div>

	<div align="center">
		<c:if test="${book != null }">
			<form action="update" method="post">
				<table border="1">
					<c:if test="${book != null}">
						<input type="hidden" name="id"
							value="<c:out value='${book.id}' />" />
					</c:if>
					<tr>
						<th>Title:</th>
						<td><input type="text" name="title" size="45"
							value="<c:out value='${book.title}' />" /></td>
					</tr>
					<tr>
						<th>Author:</th>
						<td><input type="text" name="author" size="45"
							value="<c:out value='${book.author}' />" /></td>
					</tr>
					<tr>
						<th>Price:</th>
						<td><input type="text" name="price" size="5"
							value="<c:out value='${book.price}' />" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Save Change" /></td>
					</tr>
				</table>
			</form>
		</c:if>
	</div>
</body>
</html>