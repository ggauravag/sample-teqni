<%@page session="false"%>
<html>
<head>
<title>TeqniHome Dashboard</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">

<style type="text/css">
.text-hidden {
	text-overflow: ellipsis;
	overflow: hidden;
	white-space: nowrap;
}

.text-shown {
	word-wrap: break-word;
}
</style>
</head>
<body>
	<jsp:include page="_menu.jsp" />
	<div class="container">

		<div class="page-header">
			<h1>
				TeqniHome <small>Assignment</small>
			</h1>
		</div>

		<h3>${messageTitle}</h3>
		<p>${messageBody}</p>

	</div>
	<script src="https://code.jquery.com/jquery-2.2.2.js"
		integrity="sha256-4/zUCqiq0kqxhZIyp4G0Gk+AOtCJsY1TA00k5ClsZYE="
		crossorigin="anonymous"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
		integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
		crossorigin="anonymous"></script>
	<!-- <script src="js/client.js"></script> -->
</body>
</html>