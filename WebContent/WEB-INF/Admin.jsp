<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin</title>
</head>
<body>

<a href ="lab5main">Link to MainPage</a>

<table border = "1">

<tr><th>text</th><th>author</th><th>Like</th><th>Dislike</th><th>Action</th></tr>

<c:forEach items="${quotes}" var ="quotes">
	<tr>
		<td>${quotes.text}</td>
		<td>${quotes.author}</td>
		<td>${quotes.liked}</td>
		<td>${quotes.disliked}</td>
		<td> <a href ="Editquotes?id=${quotes.id}">Edit</a>
		 	 <a href ="Deletequotes?id=${quotes.id}">Delete</a>
		</td>
	</tr>
</c:forEach>
</table>

<form class = "form-inline" action ="Admin" method="post">
<input class = "form-control" type ="text" name ="text" >
<input class = "form-control" type ="text" name = "author">
<input class = "btn btn-primary" type ="submit" value ="Add Quotation">
</form>

</body>
</html>