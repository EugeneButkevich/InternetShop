<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/main.css" media="all">
<title>Корзина</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.product" var="product" />
<fmt:message bundle="${loc}" key="local.quantityMessage3" var="quantityMessage3" />
<fmt:message bundle="${loc}" key="local.cost" var="cost" />
<fmt:message bundle="${loc}" key="local.cancelOrder" var="cancelOrder" />
<fmt:message bundle="${loc}" key="local.messageAboutReturn1" var="messageAboutReturn1" />
<fmt:message bundle="${loc}" key="local.messageAboutReturn2" var="messageAboutReturn2" />
</head>
<body>

	<jsp:include page="modules/header.jsp" />

	<table>
		<tr>
			<th align="left">${product}</th>
			<th width="150"  align="center">${quantityMessage3}</th>
			<th align="center">${cost}</th>
			<th></th>
		</tr>
		<c:forEach items="${orders}" var="order">
			<tr>
				<td>
					<font size="+1">
 						<a href="controller?command=show_information_about_product&id_product=${order.value[0].id}">${order.value[0].name}</a>
					</font>
				</td>
				<td align="center">${order.value[2]}</td>
				<td align="center">${order.value[1]}</td>
				<td align="right" width="150">
					<form action="controller" method="post">
						<input type="hidden" name="command" value="cancel_order" /> 
						<input type="hidden" name="id_client" value="${sessionScope.client.id}" />
						<input type="hidden" name="id_order" value="${order.key}" />
						<input type="hidden" name="id_product" value="${order.value[0].id}" /> 
						<input type="hidden" name="quantity_of_products_in_order" value="${order.value[2]}" /> 
						<input type="hidden" name="quantity_of_products_in_stock" value="${order.value[0].quantityInStock}" />
						<input type="submit" value="${cancelOrder}!" class="button1" />
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	<p>
		${messageAboutReturn1} <a href="index.jsp">${messageAboutReturn2}</a>
	</p>
</body>
</html>