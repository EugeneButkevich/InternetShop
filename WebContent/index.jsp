<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Главная</title>
<%-- <fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.authorization"
	var="authorization" />
<fmt:message bundle="${loc}" key="local.isRegistered" var="isRegistered" />
<fmt:message bundle="${loc}" key="local.signUp" var="signUp" /> --%>
</head>

<body>

	<jsp:include page="WEB-INF/jsp/modules/header.jsp" />

	<%-- <c:choose>
		<c:when test="${sessionScope.admin!=null}">
			<jsp:include page="WEB-INF/jsp/modules/logOut.jsp" />
		</c:when>
		<c:when test="${sessionScope.client!=null}">
			<jsp:include page="WEB-INF/jsp/modules/logOut.jsp" />
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
	</c:choose> --%>

	<jsp:include page="WEB-INF/jsp/productsPage.jsp" />

</body>
</html>