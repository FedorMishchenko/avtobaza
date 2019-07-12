<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty
       language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources" />

<fmt:message key="userPage.massage.userName" var="userName" />
<fmt:message key="userPage.button.userInfo" var="userInfo" />
<fmt:message key="userPage.button.userOrders" var="userOrders" />
<fmt:message key="userPage.button.newOrders" var="newOrders" />
<fmt:message key="userPage.button.updateUser" var="updateUser" />
<fmt:message key="userPage.button.createInfo" var="createInfo" />
<fmt:message key="userPage.button.updateInfo" var="updateInfo" />


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>USER PAGE</title>
    <style>
        <%@include file='../css/style.css' %>
    </style>

</head>
<body>
<jsp:include page="index.jsp"></jsp:include>
<br/>
${userName} <b>${loginedUser.userName}</b>
<br/>
<a href="listOrders">${userOrders}</a>
<a href="order"class="a">${newOrders}</a>
<br/>
<a href="updateUser">${updateUser}</a>
<br/>
<a href="infoGet"> ${userInfo} </a>
<a href="infoForm"> ${createInfo} </a>
<a href="infoUpdate"> ${updateInfo} </a>
<br/>
<jsp:include page="makeRequest.jsp"></jsp:include>
<br/>
<jsp:include page="closeOrder.jsp"></jsp:include>

</body>
</html>

