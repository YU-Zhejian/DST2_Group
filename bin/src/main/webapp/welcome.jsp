<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
		<title>Login</title>
		<link rel="stylesheet" type="text/css"
			  href="${pageContext.request.contextPath}/static/welcomepage.min.css"/>
		<link rel="stylesheet" type="text/css"
			  href="${pageContext.request.contextPath}/static/main.min.css"/>
	</head>
	<body>
		<div id="login-box">
			<h1>Login</h1>
			<form name="this" action="login" method="POST">
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
				<input type="submit" name="action" value="login">
				<input type="button" name="action" value="register">
			</form>
		</div>
	</body>
</html>
