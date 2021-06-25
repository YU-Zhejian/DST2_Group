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
					<td><a href="https://www.pharmgkb.org/labelAnnotation/${label.getId()}">${label.getId()}</a></td>
					<td>${label.getSource()}</td>
					<td>${label.hasDosingInformation()}</td>
					<td>${label.getSummaryMarkdown()}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>
