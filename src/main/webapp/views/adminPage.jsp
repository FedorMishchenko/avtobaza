<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty
       language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources" />
<fmt:message key="adminPage.button.newOrders" var="newOrders" />
<fmt:message key="adminPage.button.orders" var="orders" />
<fmt:message key="adminPage.button.newAccount" var="newAccount" />
<fmt:message key="adminPage.button.admins" var="admins" />
<fmt:message key="adminPage.button.users" var="users" />
<fmt:message key="adminPage.massage" var="name" />
<fmt:message key="adminPage.massage.bydate" var="bydate" />
<fmt:message key="adminPage.massage.byid" var="byid" />
<fmt:message key="adminPage.massage.bydistance" var="bydistance" />
<fmt:message key="adminPage.massage.byuser" var="byuser" />
<fmt:message key="adminPage.massage.sort" var="sort" />
<fmt:message key="manager.button.input" var="input" />
<html>
<head>
    <title>Admin Task</title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>
<body>

<jsp:include page="index.jsp"></jsp:include>
<br/>
${name} <b>${loginedUser.userName}</b>
<br/>
<a href="order" class="a">${newOrders}</a>
<a href="allOrders" class="a">${orders}</a>
<br/>
<a href="registration" class="a">${newAccount}</a>
<a href="listUsers" class="a">${users}</a>
<a href="admin" class="a">${admins}</a>

<c:if test="${errorMassage != null}">
    <p class="text-typing">${errorMassage}</p>
</c:if>
<br/>
<jsp:include page="setUserRole.jsp"></jsp:include>
<br/>
<jsp:include page="deleteUser.jsp"></jsp:include>
<br/>
<jsp:include page="deleteOrder.jsp"></jsp:include>
<br/>
<h3>${sort}</h3>
<p class="text-typing"><strong><i>${bydate}</i></strong></p>
<p class="text-typing"><strong><i>${byid}</i></strong></p>
<p class="text-typing"><strong><i>${bydistance}</i></strong></p>
<p class="text-typing"><strong><i>${byuser}</i></strong></p>

<form method="POST" action="${pageContext.request.contextPath}/orderSort">
    <input type="text" pattern="([1-4]{1})" minlength="1" maxlength="1"
           placeholder="number" name="number" required>
    <input type="submit" value="${input}">
</form>
<br/>
</body>
</html>
