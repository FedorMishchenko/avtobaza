<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty
       language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources" />

<fmt:message key="manager.massage.userName" var="userName" />
<fmt:message key="manager.button.createOrder" var="createOrder" />
<fmt:message key="manager.button.orders" var="orders" />
<fmt:message key="manager.button.newOrders" var="newOrders" />
<fmt:message key="manager.button.requests" var="requests" />
<fmt:message key="manager.massage.approveOrder" var="approveOrder" />
<fmt:message key="manager.placeholder.userId" var="userId" />
<fmt:message key="manager.placeholder.orderId" var="orderId" />
<fmt:message key="manager.button.input" var="input" />


<fmt:message key="adminPage.button.byDistance" var="byDistance" />
<fmt:message key="adminPage.button.byId" var="byId" />
<fmt:message key="adminPage.button.byUserId" var="byUserId" />
<fmt:message key="adminPage.button.byDate" var="byDate" />
<fmt:message key="adminPage.button.users" var="users" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MANAGER TASK</title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>

<body>

<jsp:include page="index.jsp"></jsp:include>
<br />
${userName} <b>${loginedUser.userName}</b>
<br />
<a href="orderForm"class="a">${createOrder}</a>
<a href="order"class="a">${newOrders}</a>
<a href="allOrders"class="a">${orders}</a>
<br/>
<a href="byDistance"class="a">${byDistance}</a>
<a href="byID"class="a">${byId}</a>
<a href="byUserID"class="a">${byUserId}</a>
<a href="byDate"class="a">${byDate}</a>
<br/>
<a href="listUsers"class="a">${users}</a>
<a href="listRequests"class="a">${requests}</a>
<br/>
<c:if test="${errorMassage != null}">
    <p class="text-typing">${errorMassage}</p>
</c:if>
<br/>
<br/>
<h3>${approveOrder}</h3>
<form method="POST" action="${pageContext.request.contextPath}/managerTask">
    <input type="text" pattern="([0-9]+)" minlength="1" maxlength="11"
           placeholder="${userId}" name="approveUserId" required>
    <input type="text" pattern="([0-9]+)" minlength="1" maxlength="11"
           placeholder="${orderId}" name="approveOrderId" required>
    <input type="submit" value="${input}">
</form>
</body>
</html>
