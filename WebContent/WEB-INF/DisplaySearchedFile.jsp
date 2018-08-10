 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h4>List of files based on your search: <i> ${fileName} </i> </i></h4>

<table border='1' cellpadding = '1' cellspacing ='1' width=60%>
	<tr>
		<td>Name</td>
		<td>Upload Time</td>
		<td columnspan="2">Operations</td>
	</tr>
	
	<c:forEach items="${fileEntries}" var="file">
		<tr>
			<td> <a href="finalmain?tempid=${folder.id }&tn=${file.name}">${file.name }</a> <br> </td>
			<td>${file.dateString }</td>
			
			<td> <a href="EditFolder?tempid=${file.id }&tn=${file.name}">Rename</a> 
			<a href="DeleteFolder?tempid=${file.id }&tn=${file.name}">Delete</a>
			 </td>
		</tr>
	</c:forEach>

</table>

</body>
</html>