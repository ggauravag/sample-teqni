<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>TeqniHome</title>

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

	<div class="container">

		<div class="page-header">
			<h1>
				TeqniHome <small>Assignment</small>
			</h1>
		</div>

		<c:if test="${param.error != null}">
			<div class="alert alert-danger" role="alert">
				Your login attempt was not successful, try again.<br />
			</div>
		</c:if>


		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active"><a href="#login"
				aria-controls="home" role="tab" data-toggle="tab">Login</a></li>
			<li role="presentation"><a href="#register"
				aria-controls="profile" role="tab" data-toggle="tab">Register</a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="login">
				<br />
				<form class="form-horizontal" action="j_spring_security_check"
					method="post">
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-3 control-label">Username</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="inputEmail3"
								name="username" placeholder="Username">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-3 control-label">Password</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="inputPassword3"
								placeholder="Password" name="password">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-4">
							<button type="submit" class="btn btn-primary">Sign in</button>
						</div>
						<div class="col-sm-4">
							<button type="reset" class="btn btn-default">Reset</button>
						</div>
					</div>
				</form>
			</div>
			<div role="tabpanel" class="tab-pane" id="register">
				<br />
				<form>
					<div class="form-group">
						<label for="inputFirstName">First Name</label> <input type="text"
							class="form-control" id="inputFirstName" placeholder="First Name">
					</div>
					<div class="form-group">
						<label for="inputLastName">Last Name</label> <input type="text"
							class="form-control" id="inputLastName" placeholder="Last Name">
					</div>
					<div class="form-group">
						<label for="inputDOB">Date Of Birth</label> <input type="date"
							class="form-control" id="inputDOB" placeholder="Date of Birth">
					</div>
					<div class="form-group">
						<label for="inputUserType">User Type</label> <select
							class="form-control" id="inputUserType" placeholder="User Type">
							<option value="ROLE_USER">User</option>
							<option value="ROLE_ADMIN">Admin</option>
						</select>
					</div>
					<div class="form-group">
						<label for="inputDOB">Email Address</label> <input type="email"
							class="form-control" id="inputEmail" placeholder="Email">
					</div>
					<div class="form-group">
						<label for="inputUsername">Username</label> <input type="text"
							class="form-control" id="inputUsername"
							placeholder="Enter Username">
					</div>
					<div class="form-group">
						<label for="inputPassword">Password</label> <input type="password"
							class="form-control" id="inputPassword" placeholder="Password">
					</div>


					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-4">
							<button type="submit" class="btn btn-success">Register</button>
						</div>
						<div class="col-sm-4">
							<button type="reset" class="btn btn-default">Reset</button>
						</div>
					</div>

				</form>

			</div>
		</div>
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