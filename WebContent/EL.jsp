<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EL Table</title>
</head>
<body>
<style>
table ,th, tr{
border: 1px solid gray;}
</style>
<br></br>
<table style = "width:50%" >
<tr><th>Arithnetic Operators</th></tr>
<tr><th>Expression</th><th>Result</th></tr>
<tr><th>\${3+2-1}</th><th>${3+2-1 }</th></tr>
<tr><th>\${"1"+2}</th><th>${"1"+2 }</th></tr>
<tr><th>\${1+2*3 +3/4}</th><th>${1+2*3 +3/4}</th></tr>
<tr><th>\${3%2}</th><th>${3%2}</th></tr>
<tr><th>\${((8 div 2) mod 3)}</th><th>${((8 div 2) mod 3)}</th></tr>
<tr></tr>
<tr><th>Relational Operators</th></tr>
<tr><th>Expression</th><th>Result</th></tr>
<tr><th>\${1<2}</th><th>${1<2 }</th></tr>
<tr><th>\${'a'<'b'}</th><th>${'a'<'b' }</th></tr>
<tr><th>\${2/3 >= 3/2}</th><th>${2/3 >= 3/2 }</th></tr>
<tr><th>\${3/4 ==0.75}</th><th>${3/4 ==0.75 }</th></tr>
<tr><th>\${null == "test"}</th><th>${null == "test" }</th></tr>
<tr></tr>
<tr><th>Logical Operators</th></tr>
<tr><th>Expression</th><th>Result</th></tr>
<tr><th>\${((1<2)&&(3<4))}</th><th>${((1<2)&&(3<4)) }</th></tr>
<tr><th>\${((1<2) || (3<4)) }</th><th>${((1<2) || (3<4)) }</th></tr>
<tr><th>\${(!(1<2))}</th><th>${(!(1<2)) }</th></tr>
<tr></tr>
<tr><th>empty Operators</th></tr>
<tr><th>Expression</th><th>Result</th></tr>
<tr><th>\${empty ""}</th><th>${empty "" }</th></tr>
<tr><th>\${empty null}</th><th>${empty null }</th></tr>
<tr><th>\${empty param.blah}</th><th>${empty param.blah }</th></tr>

</table>
</body>
</html>