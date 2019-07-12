<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty
       language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources" />
<fmt:message key="deleteUser.button.delete" var="delete" />
<fmt:message key="deleteUser.massage.deleteUser" var="deleteUser" />
<fmt:message key="deleteUser.massage.userId" var="userId" />
<html>
<head>
    <title>Delete user</title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>
<body>
<h3>${deleteUser}</h3>
<form method="POST" action="${pageContext.request.contextPath}/adminTask">
    <input type="text" pattern="([0-9]+)" minlength="1" maxlength="11"
           placeholder="${userId}" name="deleteUserId">
    <input type="submit" value="${delete}">
</form>

</body>
</html>
