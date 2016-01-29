<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Список клиентов</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.listOfClients" var="listOfClients" />
<fmt:message bundle="${loc}" key="local.idOfClient" var="idOfClient" />
<fmt:message bundle="${loc}" key="local.loginOfCLient" var="loginOfClient" />
<fmt:message bundle="${loc}" key="local.addToBlacklist1" var="addToBlacklist1" />
<fmt:message bundle="${loc}" key="local.addToBlacklist2" var="addToBlacklist2" />
<fmt:message bundle="${loc}" key="local.registeredClientsIsNull" var="registeredClientsIsNull" />
</head>
<body>
	<jsp:include page="modules/header.jsp" />

	<h1>${listOfClients}</h1>

	<c:if test="${sessionScope.clients != null}">
		<table border="1px" cellspacing="0">
			<tr>
				<th>${idOfClient}</th>
				<th>${loginOfClient}</th>
				<th>${addToBlacklist1}</th>
			</tr>
			<c:forEach items="${sessionScope.clients}" var="client">
				<tr>
					<td><c:out value="${client.id}" /></td>
					<td><c:out value="${client.login}" /></td>
					<td align="center">
						<form action="controller" method="post">
							<input type="hidden" name="command" value="add_to_blacklist" /> <input
								type="hidden" name="id" value="${client.id}" /> <input
								type="submit" value="${addToBlacklist2}!" />
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${sessionScope.clients == null}">
		${registeredClientsIsNull}
	</c:if>
</body>
</html>