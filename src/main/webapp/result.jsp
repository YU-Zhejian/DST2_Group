<!DOCTYPE html>
<html>
<head>
	<%@page import="java.util.ArrayList" %>
	<%@page import="java.util.HashMap" %>
	<%--@page import="main.java.JDBC" --%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style type="text/css">
td {text-align:center;}
form {margin:50% 0}
</style>

<title>Insert title here</title>
</head>
<body>
	<%@include file="navi.jsp" %>
	<%String user=(String)request.getSession().getAttribute("username");
	String sql="SELECT * FROM result WHERE username='"+user+"";
	%>
	<h2>user:<%=user %></h2>
	<table border="1" class="table table-striped table-sm">

		<c:forEach items="${result}" var="row">
		<tr>
			<td>${row.id}</td>
			<td>${row.source}</td>
			<td >${row.dosing_information}</td>
			<td>${row.summary_markdown}</td>
	
			
		</tr>
		</c:forEach>
	</table>

</body>
</html>