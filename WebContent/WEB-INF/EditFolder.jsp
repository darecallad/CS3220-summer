<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rename Folder</title>
</head>
<body>

<h4>Rename the folder and send it back to post</h4>


<form action='EditFolder' method='post'>
<p>Edit Folder: 
<input type="text" name="newName" value="${currentName }"> 
<input type="hidden" name="tempid" value="${currentID }">
<input type="submit" value="Save">

</form>

</body>
</html>