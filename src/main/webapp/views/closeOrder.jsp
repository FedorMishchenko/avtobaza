<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty
       language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources" />
<fmt:message key="closeOrder.button.close" var="close" />
<fmt:message key="closeOrder.massage.orderId" var="orderId" />
<fmt:message key="closeOrder.massage.closeOrder" var="closeOrder" />
<html>
<head>
    <title>Close order</title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>
<body>
<h3>${closeOrder}</h3>
<form method="POST" action="${pageContext.request.contextPath}/userTask">
    <input type="text" pattern="([0-9]+)" minlength="1" maxlength="11"
           placeholder="${orderId}" name="closeOrder">
    <input type="submit" value="${close}">
</form>
</body>
</html>
