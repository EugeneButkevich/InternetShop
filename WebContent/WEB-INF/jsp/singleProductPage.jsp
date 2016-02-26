<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/main.css" media="all">
<title>Страница продукта</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.errorOrder1" var="errorOrder1" />
<fmt:message bundle="${loc}" key="local.errorOrder2" var="errorOrder2" />
<fmt:message bundle="${loc}" key="local.errorOrder3" var="errorOrder3" />
<fmt:message bundle="${loc}" key="local.errorOrder4" var="errorOrder4" />
<fmt:message bundle="${loc}" key="local.idNumberOfProduct" var="idNumberOfProduct" />
<fmt:message bundle="${loc}" key="local.cost" var="cost" />
<fmt:message bundle="${loc}" key="local.quantityMessage1" var="quantityMessage1" />
<fmt:message bundle="${loc}" key="local.quantityMessage2" var="quantityMessage2" />
<fmt:message bundle="${loc}" key="local.orderMessage" var="orderMessage" />
<fmt:message bundle="${loc}" key="local.quantityMessage3" var="quantityMessage3" />
</head>
<body>
	<jsp:include page="modules/header.jsp" />

	<jsp:useBean id="product" class="by.htp.internetshop.domain.Product" scope="request" />
	<jsp:setProperty property="*" name="product" />

	<h1><jsp:getProperty property="name" name="product" /></h1>
	 
	<c:if test="${requestScope.errorOrder==1}">
		<font color="#CC0000"> ${errorOrder1} </font>
	</c:if>
	
	<c:if test="${requestScope.errorOrder==2}">
		<font color="#CC0000"> ${errorOrder2} </font>
	</c:if>
	
	<c:if test="${requestScope.errorOrder==3}">
		<font color="#CC0000"> ${errorOrder3} </font>
	</c:if>
	
	<c:if test="${requestScope.errorOrder==4}">
		<font color="#CC0000"> ${errorOrder4} </font>
	</c:if>
	
	<table class="table2">
		<tr>
			<td>${idNumberOfProduct}:</td>
			<td><jsp:getProperty property="id" name="product" /></td>
		</tr>
		<tr>
			<td>${cost}:</td>
			<td><jsp:getProperty property="price" name="product" />$</td>
		</tr>
		<tr>
			<td>${quantityMessage1}:</td>
			<td><jsp:getProperty property="quantityInStock" name="product" />
				${quantityMessage2}.</td>
		</tr>

		<tr>
			<td>
				<c:if test="${sessionScope.client != null}">
					<form action="controller" method="post">
						<input type="hidden" name="command" value="order" /> 
						<input type="hidden" name="id_client" value="${client.id}" /> 
						<input type="hidden" name="id_product" value="${product.id}" />
						${quantityMessage3}: 
						<input type="text" name="number_of_instances" value="" class="input1"/> 
						<input type="submit" value="${orderMessage}" />
					</form>
				</c:if>
			</td>
		</tr>
	</table>
</body>
</html>