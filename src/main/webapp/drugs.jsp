<!DOCTYPE html>
<html>
<head>
	<%@page import="java.util.ArrayList" %>
	<%@page import="java.util.HashMap" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style type="text/css">
td {text-align:center;}
</style>

<title>Insert title here</title>
</head>
<body>
    <%if(request.getAttribute("drugs")==null){
    	request.getRequestDispatcher("/drugs").forward(request,response);} 
    %>
	
	<table border="1">
		<tr>
			<td>ID</td>
			<td>Name</td>
			<td>Drug URL</td>
			<td>Biomarker</td>
		</tr>
		
		<c:forEach items="${drugs}" var="drug">
		<tr>
			<td>${drug.id}</td>
			<td>${drug.name}</td>
			<td><a href="https://api.pharmgkb.org/v1/data${row.drug_url}">${drug.drug_url}</a></td>
			<td>${drug.biomarker}</td>
		</tr>
		</c:forEach>
	</table>

</body>
</html>