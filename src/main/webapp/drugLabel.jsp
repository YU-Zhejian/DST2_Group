<!DOCTYPE html>
<html>
	<head>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<link rel="stylesheet" type="text/css"
			  href="${pageContext.request.contextPath}/static/main.min.css"/>

		<title>LABELS</title>

		<style>
			td {text-align: center;}
		</style>
	</head>
	<body>
		<%@include file="navi.jsp" %>
		<table style="border: solid">
			<tr style="align-content: center">
				<td>ID</td>
				<td>Source</td>
				<td>Dosing information</td>
				<td style="width: 50%">Summary Markdown</td>
			</tr>
			<c:forEach items="${labels}" var="label">
				<tr>
					<td>${label.id}</td>
					<td>${label.source}</td>
					<td>${label.summary_markdown}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>
