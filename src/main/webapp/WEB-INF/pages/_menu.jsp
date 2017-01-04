<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="border: 1px solid #ccc; padding: 5px; margin-bottom: 20px;">

	<c:if test="${authToken == null}">
		| &nbsp; <a href="${pageContext.request.contextPath}/login">Login</a>
	</c:if>

	<c:if test="${authToken != null}">
		| &nbsp;<a
			href="${pageContext.request.contextPath}/user?${authParameter}=${authToken}">Home</a>
	| &nbsp; <a
			href="${pageContext.request.contextPath}/admin?${authParameter}=${authToken}">Admin</a>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
     | &nbsp;
     <a
				href="${pageContext.request.contextPath}/logout?${authParameter}=${authToken}">Logout</a>
		</c:if>
	</c:if>



</div>