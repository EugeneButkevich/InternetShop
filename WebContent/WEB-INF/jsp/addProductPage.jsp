<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/main.css" media="all">
<title>Страница добавления нового продукта</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.errorAddOrEditProduct1" var="errorAddOrEditProduct1" />
<fmt:message bundle="${loc}" key="local.errorAddOrEditProduct2" var="errorAddOrEditProduct2" />
<fmt:message bundle="${loc}" key="local.errorAddOrEditProduct3" var="errorAddOrEditProduct3" />
<fmt:message bundle="${loc}" key="local.errorAddOrEditProduct4" var="errorAddOrEditProduct4" />
<fmt:message bundle="${loc}" key="local.errorAddOrEditProduct5" var="errorAddOrEditProduct5" />
<fmt:message bundle="${loc}" key="local.errorAddOrEditProduct6" var="errorAddOrEditProduct6" />
<fmt:message bundle="${loc}" key="local.idNumberOfCategory" var="idNumberOfCategory" />
<fmt:message bundle="${loc}" key="local.nameProduct" var="nameProduct" />
<fmt:message bundle="${loc}" key="local.cost" var="cost" />
<fmt:message bundle="${loc}" key="local.quantityMessage1" var="quantityMessage1" />
<fmt:message bundle="${loc}" key="local.quantityMessage2" var="quantityMessage2" />
<fmt:message bundle="${loc}" key="local.nameOfOperation3" var="nameOfOperation3" />
</head>
<body>

	<jsp:include page="modules/header.jsp" />
	
	<c:if test="${requestScope.errorAddOrEditProduct==1}">
		<font color="#CC0000"> ${errorAddOrEditProduct1} </font>
	</c:if>
	
	<c:if test="${requestScope.errorAddOrEditProduct==2}">
		<font color="#CC0000"> ${errorAddOrEditProduct2} </font>
	</c:if>
	
	<c:if test="${requestScope.errorAddOrEditProduct==3}">
		<font color="#CC0000"> ${errorAddOrEditProduct3} </font>
	</c:if>
	
	<c:if test="${requestScope.errorAddOrEditProduct==4}">
		<font color="#CC0000"> ${errorAddOrEditProduct4} </font>
	</c:if>
	
	<c:if test="${requestScope.errorAddOrEditProduct==5}">
		<font color="#CC0000"> ${errorAddOrEditProduct5} </font>
	</c:if>
	
	<c:if test="${requestScope.errorAddOrEditProduct==6}">
		<font color="#CC0000"> ${errorAddOrEditProduct6} </font>
	</c:if>
	
	<form action="controller" method="post">
		<input type="hidden" name="command" value="add_new_product" />
		<table width="50%">
			<tr>
				<td>${idNumberOfCategory}:</td>
				<td><input type="text" readonly="readonly" name="id_category" value="${requestScope.id_category}" /></td>
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
				<td align="left"><input type="submit" value="${nameOfOperation3}" /></td>
			</tr>
		</table>
	</form>
</body>
</html>