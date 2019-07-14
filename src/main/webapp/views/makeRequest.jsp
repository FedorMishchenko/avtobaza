<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty
       language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources" />

<fmt:message key="makeRequest.button.input" var="input" />
<fmt:message key="makeRequest.placeholder.orderId" var="orderId" />
<fmt:message key="makeRequest.massage.makeRequest" var="makeRequest" />


<html>
<head>
    <title>Make config.request</title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>
<body>
<h3>${makeRequest}</h3>
<form method="POST" action="${pageContext.request.contextPath}/userTask">
    <input type="text" pattern="([0-9]+)" minlength="1" maxlength="11"
           placeholder="${orderId}" name="requestOrderId" required>
    <input type="submit" value="${input}">
</form>
</body>
</html>
