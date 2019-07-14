<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty
       language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources" />
<fmt:message key="deleteOrder.button.delete" var="delete" />
<fmt:message key="deleteOrder.massage.deleteOrder" var="deleteOrder" />
<fmt:message key="deleteOrder.massage.orderId" var="orderId" />

<html>
<head>
    <title>Delete order</title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>
<body>
<h3>${deleteOrder}</h3>
<form method="POST" action="${pageContext.request.contextPath}/adminTask">
    <input type="text" pattern="([0-9]+)" minlength="1" maxlength="11"
           placeholder="${orderId}" name="deleteOrderId" required>
    <input type="submit" value="${delete}">
</form>
</body>
</html>
