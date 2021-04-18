<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<!-- FIXME: There are inconsistency in the name of this file and other files.-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign in</title>
</head>
<body>
	<form action=users method="post">
		用户名：<input type="text" name="username" required="required">
		密码：<input type="password" name="password" required="required">
		<input type="submit" value="submit">
	</form>
</body>
</html>