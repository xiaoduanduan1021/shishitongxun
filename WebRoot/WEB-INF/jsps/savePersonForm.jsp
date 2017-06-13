<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<title>My JSP 'updatePersonForm.jsp' starting page</title>
</head>
<body>
	<form action="<%=path%>/person/save" method="post">

		name:<input type="text" name="name"> <br> address:<input
			type="text" name="address"><br> <input type="submit"
			value="submit">
	</form>
</body>
</html>
