<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="border: 1px solid #ccc; padding: 5px; margin-bottom: 20px;">

	<a href="${pageContext.request.contextPath}/?authToken=${authToken}">Home</a>

	| &nbsp; <a
		href="${pageContext.request.contextPath}/user?authToken=${authToken}">User
		Info</a> | &nbsp; <a
		href="${pageContext.request.contextPath}/admin?authToken=${authToken}">Admin</a>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
  
     | &nbsp;
     <a
			href="${pageContext.request.contextPath}/logout?authToken=${authToken}">Logout</a>

	</c:if>

</div>