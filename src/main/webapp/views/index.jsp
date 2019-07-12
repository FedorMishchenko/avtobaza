<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty
       language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources" />

<fmt:message key="index.button.userInfo" var="userInfo" />
<fmt:message key="index.button.login" var="login" />
<fmt:message key="index.button.logout" var="logout" />
<fmt:message key="index.button.signIn" var="signIn" />
<fmt:message key="index.button.home" var="home" />
<fmt:message key="index.button.user" var="user" />
<fmt:message key="index.button.manager" var="manager" />
<fmt:message key="index.button.admin" var="admin" />
<!DOCTYPE html>
<html lang="${language}">

<head>
    <title>
        Index
    </title>
</head>
<style>
    <%@include file='../css/style.css' %>
</style>
<body>

<a href="${pageContext.request.contextPath}/index">${home}</a>
<c:if test="${loginedUser.role == 'user'}">
    <a href="${pageContext.request.contextPath}/userTask">${user}</a>
</c:if>
<c:if test="${loginedUser.role == 'manager'}">
    <a href="${pageContext.request.contextPath}/managerTask">${manager}</a>
</c:if>
<c:if test="${loginedUser.role == 'admin'}">
    <a href="${pageContext.request.contextPath}/adminTask">${admin}</a>
</c:if>

<a href="${pageContext.request.contextPath}/userInfo">${userInfo}</a>
<a href="${pageContext.request.contextPath}/login">${login}</a>
<a href="${pageContext.request.contextPath}/logout">${logout}</a>
<a href="${pageContext.request.contextPath}/registration">${signIn}</a>

<jsp:include page="login.jsp"></jsp:include>
<%--&nbsp;--%>
</body>
</html>
