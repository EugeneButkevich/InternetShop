<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Страница добавления нового продукта</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.errorAddProduct1" var="errorAddProduct1" />
<fmt:message bundle="${loc}" key="local.errorAddProduct2" var="errorAddProduct2" />
<fmt:message bundle="${loc}" key="local.errorAddProduct3" var="errorAddProduct3" />
<fmt:message bundle="${loc}" key="local.errorAddProduct4" var="errorAddProduct4" />
<fmt:message bundle="${loc}" key="local.errorAddProduct5" var="errorAddProduct5" />
<fmt:message bundle="${loc}" key="local.errorAddProduct6" var="errorAddProduct6" />
<fmt:message bundle="${loc}" key="local.idNumber" var="idNumber" />
<fmt:message bundle="${loc}" key="local.nameProduct" var="nameProduct" />
<fmt:message bundle="${loc}" key="local.cost" var="cost" />
<fmt:message bundle="${loc}" key="local.quantityMessage1" var="quantityMessage1" />
<fmt:message bundle="${loc}" key="local.quantityMessage2" var="quantityMessage2" />
<fmt:message bundle="${loc}" key="local.nameOfOperation3" var="nameOfOperation3" />
</head>
<body>

	<jsp:include page="modules/header.jsp" />
	
	<c:if test="${sessionScope.errorAddProduct==1}">
		<font color="#CC0000"> ${errorAddProduct1} </font>
		<c:set var="errorAddProduct" value="0" scope="session" />
	</c:if>
	
	<c:if test="${sessionScope.errorAddProduct==2}">
		<font color="#CC0000"> ${errorAddProduct2} </font>
		<c:set var="errorAddProduct" value="0" scope="session" />
	</c:if>
	
	<c:if test="${sessionScope.errorAddProduct==3}">
		<font color="#CC0000"> ${errorAddProduct3} </font>
		<c:set var="errorAddProduct" value="0" scope="session" />
	</c:if>
	
	<c:if test="${sessionScope.errorAddProduct==4}">
		<font color="#CC0000"> ${errorAddProduct4} </font>
		<c:set var="errorAddProduct" value="0" scope="session" />
	</c:if>
	
	<c:if test="${sessionScope.errorAddProduct==5}">
		<font color="#CC0000"> ${errorAddProduct5} </font>
		<c:set var="errorAddProduct" value="0" scope="session" />
	</c:if>
	
	<c:if test="${sessionScope.errorAddProduct==6}">
		<font color="#CC0000"> ${errorAddProduct6} </font>
		<c:set var="errorAddProduct" value="0" scope="session" />
	</c:if>
	
	<form action="controller" method="post">
		<input type="hidden" name="command" value="add_new_product" />
		<table width="50%">
			<tr>
				<td>${idNumber}:</td>
				<td><input type="text" readonly="readonly" name="id_category" value="${sessionScope.id_category}" /></td>
			</tr>
			<tr>
				<td>${nameProduct}:</td>
				<td><input type="text" name="name_product" value="" /></td>
			</tr>
			<tr>
				<td>${cost}, $:</td>
				<td><input type="text" name="cost_product" value="" /></td>
			</tr>
			<tr>
				<td>${quantityMessage1}, ${quantityMessage2}:</td>
				<td><input type="text" name="quantity" value="" /></td>
			</tr>
			<tr>
				<td></td>
				<td align="center"><input type="submit" value="${nameOfOperation3}" /></td>
			</tr>
		</table>
	</form>
</body>
</html>