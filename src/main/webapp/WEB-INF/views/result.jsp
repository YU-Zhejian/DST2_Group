<!DOCTYPE html>
<html>
	<head>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<style>
			td {text-align: center;}
			form {margin: 50% 0}
		</style>
		<title>Recommendation</title>
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
			<c:forEach items="${result}" var="sample">
				<tr>
					<td>${sample.getCreatedAt()}</td>
					<td>${sample.getMatchedDrug().getDrugId()}</td>
					<td>${sample.getMatchedDrug().getSource()}</td>
					<td>${sample.getMatchedDrug().hasDosingInformation()}</td>
					<td>${sample.getMatchedDrug().getSummaryMarkdown()}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>
