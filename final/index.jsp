<!DOCTYPE html>
<html>
<head>
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="static/bootstap.min.css">
	<style>
		#box { display: table; height: 400px; }#content { color: #fff; text-align: center; display: table-cell; vertical-align: middle; }

	</style>
</head>
<body>
	<jsp:include page="navi.jsp"></jsp:include>
	<div id="box">
		<div id="content">
			<form action=hello method="post">
				<input type="file" name="anovaOut" required="required">
				<input type="submit" value="submit">
			</form>
		</div>
	</div>
	
	<div class="center">
		
	</div>
</body>
</html>