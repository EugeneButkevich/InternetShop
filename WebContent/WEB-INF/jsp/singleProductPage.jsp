<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Описание продукта</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.idNumber" var="idNumber" />
<fmt:message bundle="${loc}" key="local.cost" var="cost" />
<fmt:message bundle="${loc}" key="local.quantityMessage1" var="quantityMessage1" />
<fmt:message bundle="${loc}" key="local.quantityMessage2" var="quantityMessage2" />
</head>
<body>
	<jsp:include page="modules/header.jsp" />

	<jsp:useBean id="product" class="by.htp.internetshop.domain.Product" scope="request" />
	<jsp:setProperty property="*" name="product" />

	<h1><jsp:getProperty property="name" name="product" /></h1>
	<table>
		<tr>
			<td>${idNumber}:</td>
			<td><jsp:getProperty property="id" name="product" /></td>
		</tr>
		<tr>
			<td>${cost}:</td>
			<td><jsp:getProperty property="price" name="product" />$</td>
		</tr>
		<tr>
			<td>${quantityMessage1}:</td>
			<%-- 	<c:if test=" ${requestScope.product != null}">
					есть
				</c:if>
				<c:if test="product.quantityInStock == 0">
					нет
				</c:if> --%>
			<td><jsp:getProperty property="quantityInStock" name="product" />
				${quantityMessage2}.</td>
		</tr>
	</table>
	<c:if test="${sessionScope.client != null}">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="order" />
			<input type="hidden" name="id_client" value="${client.id_client}" /> 
			<input type="submit" value="Заказать" />
		</form>
	</c:if>
</body>
</html>