<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>HELLO!! 
<% if(session.getAttribute("name") != null){ %>
<%= session.getAttribute("name") %>
<%}%>

</h1>


<% if(session.getAttribute("name") == null){ %>
<a href="register.jsp"><button type="">Register</button></a>
<a href="login.jsp"><button type="">Login</button></a>
<% }else{ %>
<form action="logout.jsp" method="POST">
<input type="hidden">		
<button type="submit">LogOut</button>				
</form>
<% } %>
	




</body>
</html>