<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.logOut" var="logOut" />
<fmt:message bundle="${loc}" key="local.greeting" var="greeting" />
<fmt:message bundle="${loc}" key="local.myShoppingCart" var="myShoppingCart" />
<fmt:message bundle="${loc}" key="local.authorization" var="authorization" />
<fmt:message bundle="${loc}" key="local.isRegistered" var="isRegistered" />
<fmt:message bundle="${loc}" key="local.signUp" var="signUp" />
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="localization.jsp" />

	<c:choose>
		<c:when test="${sessionScope.admin!=null}">
			<p align="right">${greeting}
				<c:out value="${sessionScope.admin.login}!"></c:out>
			<form action="controller" method="post">
				<input type="hidden" name="command" value="log_out" />
				<p align="right">
					<input type="submit" value="${logOut}" />
				</p>
			</form>
		</c:when>
		<c:when test="${sessionScope.client!=null}">
			<p align="right">${greeting}
				<c:out value="${sessionScope.client.login}!"></c:out> <br /> 
				<a href="controller?command=show_orders&id_client=${client.id}"> ${myShoppingCart} </a> <br />
			<form action="controller" method="post">
				<input type="hidden" name="command" value="log_out" />
				<p align="right">
					<input type="submit" value="${logOut}" />
				</p>
			</form>
		</c:when>
		<c:otherwise>
			<p align="right">
				<a href="authorizationPage.jsp"> ${authorization} </a>
			</p>
			<p align="right">${isRegistered}</p>
			<p align="right">
				<a href="registrationPage.jsp"> ${signUp} </a>
			</p>
		</c:otherwise>
	</c:choose>
</body>
</html>