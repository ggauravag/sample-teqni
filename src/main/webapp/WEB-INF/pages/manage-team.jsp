<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Team</title>
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

<script src="http://rubaxa.github.io/Sortable/Sortable.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>

<script type="text/javascript"
	src="http://rubaxa.github.io/Sortable/ng-sortable.js"></script>

<style type="text/css">
.glyphicon-move {
	cursor: move;
	cursor: -webkit-grabbing;
}

.list-group {
	min-height: 100px;
}
</style>

</head>
<body>
	<jsp:include page="_menu.jsp" />
	<div class="container">

		<input type="hidden" id="authToken" value="${authToken}">

		<div class="page-header">
			<h1>
				TeqniHome <small>Assignment</small>
			</h1>
		</div>

		<h2>Manage Team Page</h2>
		<br />

		<div class="row">
			<div class="col-sm-4">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Available Students</h3>
					</div>
					<div class="panel-body">
						<ul id="availableStudentList" class="list-group">
							<c:forEach var="member" items="${members}">
								<c:if test="${member.teamMemberDTO == null}">
									<li data-id="${member.id}" class="list-group-item">${member.name}</li>
								</c:if>
							</c:forEach>

							<!-- 							<li data-id="1" class="list-group-item">Gaurav Agarwal</li> -->
							<!-- 							<li data-id="2" class="list-group-item">Deshant Sharma</li> -->
							<!-- 							<li data-id="3" class="list-group-item">Ajay Jain</li> -->
						</ul>
					</div>
				</div>
			</div>

			<div class="col-sm-8">
				<div class="row">

					<c:forEach var="i" begin="0" end="1">
						<div class="col-sm-4">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">${teams.get(i).getName()}</h3>
								</div>
								<div class="panel-body">
									<ul id="team${i}List" class="list-group">
										<c:forEach var="member"
											items="${teams.get(i).getTeamMembers()}">
											<li data-id="${member.memberDTO.id}" class="list-group-item">${member.memberDTO.name}</li>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
					</c:forEach>

				</div>
				<div class="row">

					<c:forEach var="j" begin="2" end="3">
						<div class="col-sm-4">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">${teams.get(j).getName()}</h3>
								</div>
								<div class="panel-body">
									<ul id="team${j}List" class="list-group">
										<c:forEach var="member"
											items="${teams.get(j).getTeamMembers()}">
											<li data-id="${member.memberDTO.id}" class="list-group-item">${member.memberDTO.name}</li>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
					</c:forEach>

				</div>

			</div>

		</div>
	</div>
	<script src="https://code.jquery.com/jquery-2.2.2.js"
		integrity="sha256-4/zUCqiq0kqxhZIyp4G0Gk+AOtCJsY1TA00k5ClsZYE="
		crossorigin="anonymous"></script>
	<script>
		var availableStudentList = document
				.getElementById('availableStudentList');

		var authToken = document.getElementById('authToken').value;

		var teamAList = document.getElementById('team0List');
		var teamBList = document.getElementById('team1List');
		var teamCList = document.getElementById('team2List');
		var teamDList = document.getElementById('team3List');

		//List with handle
		var availableSortable = Sortable.create(availableStudentList, {
			animation : 150,
			group : 'students',
			draggable : '.list-group-item',
			onAdd : function(evt) {
				saveTeamMembers();
			}
		});

		var teamASortable = Sortable.create(teamAList, {
			animation : 150,
			group : 'students',
			draggable : '.list-group-item',
			onAdd : function(evt) {
				saveTeamMembers();
			}
		});

		var teamBSortable = Sortable.create(teamBList, {
			animation : 150,
			group : 'students',
			draggable : '.list-group-item',
			onAdd : function(evt) {
				saveTeamMembers();
			}
		});

		var teamCSortable = Sortable.create(teamCList, {
			animation : 150,
			group : 'students',
			draggable : '.list-group-item',
			onAdd : function(evt) {
				saveTeamMembers();
			}
		});

		var teamDSortable = Sortable.create(teamDList, {
			animation : 150,
			group : 'students',
			draggable : '.list-group-item',
			onAdd : function(evt) {
				saveTeamMembers();
			}
		});

		function saveTeamMembers() {
			var teams = [];

			var teamA = {};
			teamA['id'] = 1;
			teamA['name'] = 'Team A';

			var members = teamASortable.toArray();
			for (var i = 0; i < members.length; i++) {
				members[i] = {
					memberDTO : {
						id : members[i]
					}
				};
			}

			teamA['teamMembers'] = members;

			var teamB = {};
			teamB['id'] = 2;
			teamB['name'] = 'Team B';

			var members = teamBSortable.toArray();
			for (var i = 0; i < members.length; i++) {
				members[i] = {
					memberDTO : {
						id : members[i]
					}
				};
			}

			teamB['teamMembers'] = members;

			var teamC = {};
			teamC['id'] = 3;
			teamC['name'] = 'Team C';

			var members = teamCSortable.toArray();
			for (var i = 0; i < members.length; i++) {
				members[i] = {
					memberDTO : {
						id : members[i]
					}
				};
			}

			teamC['teamMembers'] = members;

			var teamD = {};
			teamD['id'] = 4;
			teamD['name'] = 'Team D';

			var members = teamDSortable.toArray();
			for (var i = 0; i < members.length; i++) {
				members[i] = {
					memberDTO : {
						id : members[i]
					}
				};
			}

			teamD['teamMembers'] = members;

			teams.push(teamA);
			teams.push(teamB);
			teams.push(teamC);
			teams.push(teamD);

			console.info(teams);

			$.ajax({
				url : 'api/team?authToken=' + authToken,
				method : 'POST',
				data : JSON.stringify(teams),
				dataType : 'json',
				contentType : 'application/json; charset=UTF-8',
				success : function(data, status, xhr) {
					console.info(data, status, xhr);
				},
				error : function(xhr, status, errorThrown) {
					console.info(xhr, status, errorThrown);
				}

			});

		}
	</script>


	<!-- Latest compiled and minified JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
		integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
		crossorigin="anonymous"></script>
	<!-- <script src="js/client.js"></script> -->
</body>
</html>