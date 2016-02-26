<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="mytag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/main.css" media="all">
<title>Страница с продуктами</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.errorAddCategory" var="errorAddCategory" />
<fmt:message bundle="${loc}" key="local.nameOfOperation1" var="nameOfOperation1" />
<fmt:message bundle="${loc}" key="local.nameOfOperation2" var="nameOfOperation2" />
<fmt:message bundle="${loc}" key="local.nameOfOperation3" var="nameOfOperation3" />
<fmt:message bundle="${loc}" key="local.addCategory1" var="addCategory1" />
<fmt:message bundle="${loc}" key="local.addCategory2" var="addCategory2" />
</head>
<body>

	<c:if test="${sessionScope.errorAddCategory==1}">
		<font color="#CC0000"> ${errorAddCategory} </font>
		<c:set var="errorAddCategory" value="0" scope="session" />
	</c:if>
	
	<c:if test="${sessionScope.admin==null}">
		<table width="50%" cellpadding="0">
			<c:forEach items="${applicationScope.allCategories}" var="category_i">
				<tr>
					<td><h2><c:out value="${category_i.name}"/></h2></td>
					<td><mytag:getproducts category="${category_i}" /></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<c:if test="${sessionScope.admin!=null}">
		<table width="50%">
			<c:forEach items="${applicationScope.allCategories}" var="category_i">
				<tr>
					<td><h2><c:out value="${category_i.name}" /></h2></td>
					<td><mytag:getproductsforadmin category="${category_i}" nameedit="${nameOfOperation1}" 
					namedelete="${nameOfOperation2}" nameadd="${nameOfOperation3}" /></td>
				</tr>
			</c:forEach>
		</table><br/>
		<form action="controller" method="post">
			<input type="hidden" name="command" value="add_new_category_product" />
			<input type="text" name="name_category" value="" title="${addCategory1}"/> 
			<input type="submit" value="${addCategory2}" class="button2"/>
		</form>
	</c:if>
</body>
</html>