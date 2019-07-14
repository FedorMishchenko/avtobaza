<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty
       language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources" />

<fmt:message key="userAccount.massage.info" var="userInfo" />

<fmt:message key="userInfo.button.submit" var="input" />
<fmt:message key="userInfo.placeholder.userId" var="userId" />

<html>
<head>
    <title>User info by id</title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>
<body>
<h3>${userInfo}</h3>
<form method="POST" action="${pageContext.request.contextPath}/info">
    <input type="text" pattern="([0-9]+)" minlength="1" maxlength="11"
           placeholder="${userId}" name="userIdInfo" required>
    <input type="submit" value="${input}">
</form>
</body>
</html>
