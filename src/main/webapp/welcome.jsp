<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
		<title>Login</title>  
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/welcomepage.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/main.css"/>
        <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.css">
	</head>
    <body>
		<div id="login-box">
		<h1>Login</h1>
			<form name="this">

		
			<div class="item">
			   <i class="fa fa-github-alt" style="font-size:24px"></i>
			   <input type="text" placeholder="username">		   
			</div>
			<div class="item">
			   <i class="fa fa-search" style="font-size:24px"></i>
			   <input type="text" placeholder="passwords">		   
			</div>
			
			<input type="submit" value="submit" onclick="/login">



		</form>
		<button type="submit" form="this" value="Submit" onclick="/login">Login</button>
        <button type="submit" form="this" value="Submit" onclick="/users">Register</button>
	    </div>
	</body>
</html>
