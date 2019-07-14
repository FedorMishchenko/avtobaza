<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty
       language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources" />
<fmt:message key="infoGet.massage.userInfo" var="userInfo" />
<fmt:message key="infoGet.massage.truck" var="truck" />
<fmt:message key="infoGet.massage.capacity" var="capacity" />
<fmt:message key="infoGet.massage.status" var="status" />
<fmt:message key="infoGet.massage.userId" var="userId" />
<html>
<head>
    <title>user info</title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>
<body>
<jsp:include page="index.jsp"></jsp:include>
<br/>
<br/>
<form>
    <table>
        <tr>
            <th>${userInfo}</th>
        </tr>
        <tr>
            <td>${truck}</td>
            <td>${infoForm.truck}</td>
        </tr>
        <tr>
            <td>${status}</td>
            <td>${infoForm.status}</td>
        </tr>
        <tr>
            <td>${capacity}</td>
            <td>${infoForm.capacity}</td>
        </tr>
        <tr>
            <td>${userId}:</td>
            <td>${infoForm.userID}</td>
        </tr>
    </table>
</form>
<br/>
<c:if test="${errorMassage != null}">
    <p class="text-typing">${errorMassage}</p>
</c:if>
</body>
</html>
