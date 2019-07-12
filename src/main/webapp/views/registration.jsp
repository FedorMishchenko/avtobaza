<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty
       language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources" />

<fmt:message key="registration.massage.registration" var="registration" />
<fmt:message key="registration.massage.phone" var="phone" />
<fmt:message key="registration.massage.password" var="password" />
<fmt:message key="registration.massage.role" var="role" />

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



<h3>${registration}</h3>

<p style="color: red;">${errorMessage}</p>

<form method="POST" action="${pageContext.request.contextPath}/registration">
    <input type="hidden" name="redirectId" value="${param.redirectId}" />
    <jsp:include page="registrationForm.jsp"></jsp:include>
</form>
<div class="block">
    <p class="text-typing"><strong><i>${phone}</i></strong></p>
    <p class="text-typing"><strong><i>${password}</i></strong></p>
    <p class="text-typing"><strong><i>${role}</i></strong></p>
</div>
</body>
</html>
