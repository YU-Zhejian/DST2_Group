<!DOCTYPE html>
<html>
	<head>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<link rel="stylesheet" type="text/css"
			  href="${pageContext.request.contextPath}/static/main.min.css"/>
		<title>DOSING GUIDELINES</title>
		<style>
			table, tr, td {vertical-align: baseline}
		</style>
	</head>
	<body>
		<%@include file="navi.jsp" %>
		<table style="border: solid">
			<tr style="align-content: center">
				<td>ID</td>
				<td>Name</td>
				<td>Recommendation</td>
				<td>Drug ID</td>
				<td>Source</td>
				<td style="width: 50%">Summary markdown</td>
			</tr>
			<c:forEach items="${guidelines}" var="guideline">
				<tr>
					<td style="align-content: center"><a href="https://www.pharmgkb.org/guidelineAnnotation/${guideline.id}">${guideline.id}</a></td>
					<td style="align-content: center">${guideline.name}</td>
					<td style="align-content: center">${guideline.is_recommendation}</td>
					<td style="align-content: center">${guideline.drug_id}</td>
					<td style="align-content: center">${guideline.source}</td>
					<td style="width: 50%">${guideline.summary_markdown}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>
