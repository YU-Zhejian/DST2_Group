<!DOCTYPE html>
<!--
Miniport by HTML5 UP
html5up.net | @ajlkn
Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>Precision Medicine Search Webpage</title>
		<meta charset="utf-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
		<link rel="stylesheet" type="text/css"
			  href="${pageContext.request.contextPath}/static/main.min.css"/>
	</head>
	<body>
		<!-- Nav -->
		<nav id="nav">
			<ul class="container">
				<% String path = request.getContextPath();%>
				<li><a href="<%=path%>/index">Welcome</a></li>
				<li><a href="<%=path%>/DrugServlet">Drug</a></li>
				<li><a href="<%=path%>/DrugLabelServlet">Drug Label</a></li>
				<li><a href="<%=path%>/DosingGuidelineServlet">Dosing Guideline</a></li>
				<li><a href="<%=path%>/contact">Contact Us</a></li>
				<% if (request.getSession().getAttribute("username") == null){
					out.println("<li><a href=\""+request.getContextPath()+"/login\">Log in</a></li>");
				}
				else{
					out.println("<li><a href=\""+request.getContextPath()+"/ResultServlet\">"+ request.getSession().getAttribute("username")+"</a></li>");
				}
				%>
			</ul>
		</nav>
	</body>
</html>
