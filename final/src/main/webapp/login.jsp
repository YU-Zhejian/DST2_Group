<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log in</title>
</head>
<body>
	<jsp:forward page="navi.jsp"></jsp:forward>
	<form action=login method="post">
		用户名：<input type="text" name="username" required="required">
		密码：<input type="password" name="password" required="required">
		<input type="submit" value="submit">
	</form>
</body>
</html>