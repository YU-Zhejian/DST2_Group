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
			<form name="this" action="register" method="POST">
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
				<a href="${pageContext.request.contextPath}/login.jsp">Already have an account? Goto login</a>
			</form>
		</div>
	</body>
</html>
