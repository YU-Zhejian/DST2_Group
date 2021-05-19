<%@ page contentType="text/html;charset=utf-8" pageEncoding="GBK" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Log in</title>
	</head>
	<body>
		<%
			if (request.getSession().getAttribute("username") != null) {
				response.sendRedirect(request.getContextPath() + "/MatchingServlet");
			}
		%>

		<form action=login method="post">
			username:
			<label>
				<input type="text" name="username" required="required">
			</label>
			password:
			<label>
				<input type="password" name="password" required="required">
			</label>
			<input type="submit" value="submit">
		</form>
	</body>
</html>
