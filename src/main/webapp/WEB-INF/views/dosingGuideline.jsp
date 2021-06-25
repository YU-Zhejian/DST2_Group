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
					<td style="align-content: center"><a href="https://www.pharmgkb.org/guidelineAnnotation/${guideline.getId()}">${guideline.getId()}</a></td>
					<td style="align-content: center">${guideline.getName()}</td>
					<td style="align-content: center">${guideline.isRecommendation()}</td>
					<td style="align-content: center">${guideline.getDrugId()}</td>
					<td style="align-content: center">${guideline.getSource()}</td>
					<td style="width: 50%">${guideline.getSummaryMarkdown()}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>
