<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit File Name</title>
</head>
<body>

<p> this JSP page do editting file name</p>


<form action='EditFile' method='post'>
<p>Edit file name: 
<input type="text" name="newfileName" value="${Name}"> ${extension}<br>
<input type="hidden" name="tempid" value="${currentID }">
<input type="submit" value="Save">



</form>
</body>
</html>