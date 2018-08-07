<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>



<form class = "form-inline" action ="Editquotes" method="post">
<input class = "form-control" type ="text" name ="text" value ="${quotes.text }" >
<input class = "form-control" type ="text" name = "author" value = "${quotes.author }">
<input class = "btn btn-primary" type ="submit" value ="Edit Quotation">
<input type = "hidden" name = "id" value = "${quotes.id}">
</form>


</body>
</html>