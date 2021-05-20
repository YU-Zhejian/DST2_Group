<!DOCTYPE html>
<!--
Miniport by HTML5 UP
html5up.net | @ajlkn
Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>Precision Medicine Seacrch Webpage</title>
		<meta charset="utf-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
		<link rel="stylesheet" type="text/css"
			  href="${pageContext.request.contextPath}/static/main.min.css"/>
	</head>
	<style>
		a {
			text-decoration: none;
		}
		.cv footer {
			align-items: center;
			align-self: center;
		}
		#portfolio header {
			font-family: "arial", sans-serif;
		}
		div.col-8 col-7-large col-12-medium {
			color: black;
			font-family: "arial", sans-serif;
		}
	</style>
	<body class="is-preload">
		<%@include file="navi.jsp" %>
		<!-- Home -->
		<article id="Wel" class="wrapper style1">
			<div class="container">
				<div class="row">
					<div class="col-4 col-5-large col-12-medium">
						<span class="image fit"><p><img src="${pageContext.request.contextPath}/static/search.png" alt=""/></p>
							Undergraduate<br>
							<i>Zhejiang University, University of Edinburgh Institute</i></span>
					</div>
					<div class="col-8 col-7-large col-12-medium">
						<header>
							<h2><strong>Welcome</strong></h2>
						</header>
						<p>Welcome to use the &quot;Precision Drug Search&quot; software, which was built by students from Zhejiang University, University of Edinburgh Institute, majoring in bioinformatics. The database of our software was crawled from PharmGKB and will be automatically uploaded every week.</p>
						<p>The major two functions of our software are listed below.</p>
						<p>1.All the drugs, durg labels and dosing guidelines stored in the database are showed in our web, you can click the corresponding tag in the header for detailed information.</p>
						<p>2.This web can help you to match variants in your own file with corresponding target drug information. you can click the &quot;Match&quot; button and then click the &quot;Upload&quot; button to upload your file. The result will be showed in the &quot;Result&quot; part soon.</p>
						<p>Before matching your file, it is necessary to register by simply clicking the &quot;Register&quot; button and then type in your username and password. If you already have your account, you can just click the &quot;Login&quot; button and then you can exploring your data.</p>
					</div>
				</div>
				<div class="title">GET STARTED</div>

				<div class="col-8 col-7-large col-12-medium">
					<header><h2><strong>Match</strong></h2></header>
					<form action="${pageContext.request.contextPath}/MatchingServlet" enctype="multipart/form-data" method="POST">
						Upload<input type="file" name="annovar" required="required">
						<input type="submit" name="submit">
					</form>
				</div>
			</div>
		</article>
	</body>
</html>
