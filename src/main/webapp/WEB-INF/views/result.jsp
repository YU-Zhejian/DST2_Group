<!DOCTYPE html>
<html>
	<head>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<style>
			td {text-align: center;}
			form {margin: 50% 0}
		</style>
		<title>suggested medicine</title>
	</head>
	<body>
		<%@include file="navi.jsp" %>
		<table style="border: solid"  class="table table-striped table-sm">
			<tr style="align-content: center">
				<td>Create time</td>
				<td>ID</td>
				<td>Source</td>
				<td>Dosing information</td>
				<td style="width: 50%">Summary Markdown</td>
			</tr>
			<c:forEach items="${result}" var="row">
				<tr>
					<td>${row.created_at}</td>
					<td>${row.drug_id}</td>
					<td>${row.source}</td>
					<td>${row.have_dosing_information}</td>
					<td>${row.summary_markdown}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>
