<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty
       language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources" />
<fmt:message key="adminPage.button.newOrders" var="newOrders" />
<fmt:message key="adminPage.button.orders" var="orders" />
<fmt:message key="adminPage.button.byDistance" var="byDistance" />
<fmt:message key="adminPage.button.byId" var="byId" />
<fmt:message key="adminPage.button.byUserId" var="byUserId" />
<fmt:message key="adminPage.button.byDate" var="byDate" />
<fmt:message key="adminPage.button.newAccount" var="newAccount" />
<fmt:message key="adminPage.button.admins" var="admins" />
<fmt:message key="adminPage.button.users" var="users" />
<fmt:message key="adminPage.massage" var="name" />
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
<a href="order"class="a">${newOrders}</a>
<a href="allOrders"class="a">${orders}</a>
<br/>
<a href="byDistance"class="a">${byDistance}</a>
<a href="byID"class="a">${byId}</a>
<a href="byUserID"class="a">${byUserId}</a>
<a href="byDate"class="a">${byDate}</a>
<br/>
<a href="registration"class="a">${newAccount}</a>
<a href="listUsers"class="a">${users}</a>
<a href="admin"class="a">${admins}</a>

<p style="color: red;">${errorMessage}</p>
<jsp:include page="setUserRole.jsp"></jsp:include>
<br/>
<jsp:include page="userInfo.jsp"></jsp:include>
<br/>
<jsp:include page="deleteUser.jsp"></jsp:include>
<br/>
<jsp:include page="deleteOrder.jsp"></jsp:include>

</body>
</html>
