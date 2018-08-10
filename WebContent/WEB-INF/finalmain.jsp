<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Folder manager</title>
</head>
<body>

<p> this is final project </p>
<p> file manager </p>


<h4> Current Folder : ${currentName} </h4>

<table cellpadding = "2" >
	<tr>
		<td><a href="NewFolder${addString }">New Folder</a></td><td></td>
		<td><a href="Upload${addString }">Upload File</a></td>
		<td><a href='SearchFile'>Search File</a></td>
	</tr>
	<tr></tr>

	<tr>
	<td><c:if test="${backString != null }"><a href="finalmain${backString }">Back</a></c:if></td>
	</tr>
	
</table>

<table border='1' cellpadding = '1' cellspacing ='1' width=60%>
	<tr>
		<td>Name</td>
		<td>Upload Time</td>
		<td columnspan="2">Operations</td>
	</tr>
	
	<c:forEach items="${Tempfolders}" var="folder" varStatus="folderStatus">
		<tr>
			<td> <a href="finalmain?tempid=${folder.id }&tn=${folder.name}">${folder.name }</a> <br> </td>
			<td>${folder.dateString }</td>
			
			<td> <a href="EditFolder?tempid=${folder.id }&tn=${folder.name}">Rename</a> 
			<a href="DeleteFolder?tempid=${folder.id }&tn=${folder.name}">Delete</a>
			 </td>
		</tr>
	</c:forEach>

	<c:forEach items="${Tempfiles}" var="file" varStatus="fileStatus">
		<tr>
			<td> <a href="Download?tempid=${file.id }&tn=${file.name}">${file.name }</a> <br> </td>
			<td>${file.dateString }</td>
			
			<td> <a href="EditFile?tempid=${file.id }&tn=${file.name}">Rename</a> 
			<a href="DeleteFile?tempid=${file.id }&tn=${file.name}">Delete</a>
			 </td>
		</tr>
	</c:forEach>

</table>
</body>
</html>