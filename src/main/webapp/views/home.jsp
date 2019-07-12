<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty
       language ? language : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<fmt:message key="home.massage.user" var="userMassage"/>
<fmt:message key="home.massage.manager" var="managerMassage"/>
<fmt:message key="home.massage.admin" var="adminMassage"/>
<fmt:message key="home.massage.author" var="author"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>
<body>

<jsp:include page="index.jsp"></jsp:include>

<br/>
<br/>
<footer>
    <p class="text-typing">${userMassage}</p>
    <p class="text-typing">${managerMassage}</p>
    <p class="text-typing">${adminMassage}</p>
    <br/>
    <p>${author}</p>
</footer>
</body>
</html>
