<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload file</title>
</head>
<body>

<h4> this is a simple form page </h4>
<h4> upload your file here. </h4>
<br>
<br>

<form action="Upload" method="post" enctype='multipart/form-data'>
<input type="file" name="file" Required>
<br>
<br>
<input type="submit"  value="Upload" >
<br>
</form>
</body>
</html>