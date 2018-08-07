<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Folder</title>
</head>
<body>

<h4> Simple form for creating new Folder</h4>

<form action="NewFolder" method="post">
New Folder: <input type="text" name="newName" value="New Name" Required>
<input type="submit" value="Create">
<input type="hidden" name="pid" value="${parentID }">

</form>

</body>
</html>