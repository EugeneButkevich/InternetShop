<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Страница авторизации</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.errorMessageAuthorization1" var="errorMessageAuthorization1" />
<fmt:message bundle="${loc}" key="local.errorMessageAuthorization2" var="errorMessageAuthorization2" />
<fmt:message bundle="${loc}" key="local.login" var="login" />
<fmt:message bundle="${loc}" key="local.password" var="password" />
<fmt:message bundle="${loc}" key="local.entry" var="entry" />
<fmt:message bundle="${loc}" key="local.isRegistered" var="isRegistered" />
<fmt:message bundle="${loc}" key="local.signUp" var="signUp" />
</head>

<body>
	<jsp:include page="WEB-INF/jsp/modules/localization.jsp" />

	<c:if test="${sessionScope.errorAuthorization==1}">
		<font color="#CC0000"> ${errorMessageAuthorization1} </font>
		<c:set var="errorAuthorization" value="0" scope="session" />
	</c:if>
	
	<c:if test="${sessionScope.errorAuthorization==2}">
		<font color="#CC0000"> ${errorMessageAuthorization2} </font>
		<c:set var="errorAuthorization" value="0" scope="session" />
	</c:if>
	
	<form action="controller" method="post">
		<input type="hidden" name="command" value="authorization" />
		<table>
			<tr>
				<td>${login}:</td>
				<td><input type="text" name="login" value="" /></td>
			</tr>
			<tr>
				<td>${password}:</td>
				<td><input type="text" name="password" value="" /></td>
			</tr>
		</table>
		<input type="submit" value="${entry}" /> <br />
	</form>

	${isRegistered}
	<a href="registrationPage.jsp"> ${signUp} </a>
</body>
</html>