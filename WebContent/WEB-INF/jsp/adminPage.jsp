<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Страница админа</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.listOfClients"
	var="listOfClients" />
<fmt:message bundle="${loc}" key="local.productsPage" var="productsPage" />
<fmt:message bundle="${loc}" key="local.blacklist" var="blacklist" />
</head>
<body>

	<jsp:include page="modules/header.jsp" />

	<a href="controller?command=show_products"> ${productsPage} </a> <br /> <br />

	<a href="controller?command=show_correct_clients"> ${listOfClients} </a> <br /> <br />
	
	<a href="controller?command=show_blacklist"> ${blacklist} </a> <br /> <br />

</body>
</html>