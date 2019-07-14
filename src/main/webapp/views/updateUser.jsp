<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty
       language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources" />

<fmt:message key="updateUser.massage" var="update" />
<fmt:message key="registration.massage.phone" var="phone" />
<fmt:message key="registration.massage.password" var="password" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>
<body>

<jsp:include page="index.jsp"></jsp:include>

<h3>${update}</h3>

<c:if test="${errorMassage != null}">
    <p class="text-typing">${errorMassage}</p>
</c:if>

<form method="POST" action="${pageContext.request.contextPath}/updateUser">
    <input type="hidden" name="redirectId" value="${param.redirectId}" />
    <jsp:include page="registrationForm.jsp"></jsp:include>
</form>
<div class="block">
    <p><strong><i>${phone}</i></strong></p>
    <p><strong><i>${password}</i></strong></p>
</div>
</body>
</html>
