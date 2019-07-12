<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty
       language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources" />

<fmt:message key="userAccount.massage.info" var="userInfo" />
<fmt:message key="userAccount.massage.id" var="id" />

<fmt:message key="registrationForm.massage.login" var="login" />
<fmt:message key="registrationForm.massage.phone" var="phone" />
<fmt:message key="registrationForm.massage.email" var="email" />
<fmt:message key="registrationForm.massage.role" var="role" />

<html>
<head>
    <meta charset="UTF-8">
    <title>Info</title>
    <style>
        <%@include file='../css/style.css' %>
        footer {
            position: fixed;
            left: 20px; bottom: 20px;
        }

    </style>
</head>
<body>

<jsp:include page="index.jsp"></jsp:include>
<footer>
<form>
    <table>
        <tr>
            <th>${userInfo} ${loginedUser.userName}</th>
        </tr>
        <tr>
            <td>${id}</td>
            <td>${loginedUser.id}</td>
        </tr>
        <tr>
            <td>${login}</td>
            <td>${loginedUser.userName}</td>
        </tr>
        <tr>
            <td>${phone}</td>
            <td>${loginedUser.phone}</td>
        </tr>
        <tr>
            <td>${email}</td>
            <td>${loginedUser.email}</td>
        </tr>
        <tr>
            <td>${role}</td>
            <td>${loginedUser.role}</td>
        </tr>
    </table>
</form>
</footer>
</body>
</html>
