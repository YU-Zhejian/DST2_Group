<!DOCTYPE HTML>
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
						<span class="image fit"><p><img src="search.png" alt=""/></p>
							Undergraduate<br>
							<i>Zhejiang University, University of Edinburgh Institute</i></span>
					</div>

					<div class="col-8 col-7-large col-12-medium">
						<header>
							<h2><strong>Welcome</strong></h2>
						</header>
						<p>Welcome to use our software
						<p>This software was built by students from Zhejiang University, University
							of Edinburgh Institute, majoring in bioinformatics. I've taken a series
							of lessons about molecular biology, functional biology, genomics and
							proteomics, data science, and computer science. The main programming
							languages I use include python, java, R, and SQL. I'm interested in
							exploring the human genome and solving biological questions, and I'm
							also committed to broadening participation within biomedical informatics
							and improving the communication of science outside the academic
							realm.</p>
						<a href="login.jsp" class="button large scrolly">Log in</a>
					</div>
				</div>

				<div class="title">GET START</div>

				<div class="col-8 col-7-large col-12-medium">
					<header><h2><strong>Match</strong></h2></header>

					<form action="${pageContext.request.contextPath}/match" enctype="multipart/form-data" method="POST">
						Upload<input type="file" name="annovar" required="required">
						<input type="submit" name="submit">
					</form>
				</div>


			</div>
		</article>
	</body>
</html>
