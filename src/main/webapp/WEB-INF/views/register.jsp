<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Register</title>
		<link rel="stylesheet" type="text/css"
			  href="${pageContext.request.contextPath}/static/welcomepage.min.css"/>
		<link rel="stylesheet" type="text/css"
			  href="${pageContext.request.contextPath}/static/main.min.css"/>
	</head>
	<body>
		<div id="login-box">
			<h1>Register</h1>
			<form name="this" action="${pageContext.request.contextPath}/RegisterServlet" method="POST">
				<div class="item">
					<i class="fa fa-github-alt" style="font-size:24px"></i>
					<label>
						<input type="text" placeholder="Username" name="username">
					</label>
				</div>
				<div class="item">
					<i class="fa fa-search" style="font-size:24px"></i>
					<label>
						<input type="password" placeholder="Passwords" name="password">
					</label>
				</div>
				<input type="submit" value="register">
				<br>
				<a href="${pageContext.request.contextPath}/login">Already have an account? Goto login</a>
				<br>
				<p style="color: red">
				<%
					if (request.getAttribute("errMsg") != null){ out.println(request.getAttribute("errMsg")); }
				%>
				</p>
			</form>
		</div>
	</body>
</html>
