<!DOCTYPE html>
<html>
	<head>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<link rel="stylesheet" type="text/css"
			  href="${pageContext.request.contextPath}/static/main.min.css"/>
		<style>
			td {text-align: center;}
		</style>
		<title>DRUGS</title>
	</head>
	<body>
		<%
			if (request.getAttribute("drugs") == null) { request.getRequestDispatcher("/DrugServlet").forward(request, response); }
		%>
		<%@include file="navi.jsp" %>
		<table style="border: solid">
			<tr>
				<td>ID</td>
				<td>Name</td>
				<td>Biomarker</td>
			</tr>
			<c:forEach items="${drugs}" var="drug">
				<tr>
					<td><a href="https://www.pharmgkb.org${drug.getDrugUrl()}">${drug.getId()}</a></td>
					<td>${drug.getName()}</td>
					<td>${drug.isBiomarker()}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>
