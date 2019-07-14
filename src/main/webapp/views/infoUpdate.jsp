<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty
       language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources" />

<fmt:message key="infoGet.massage.truck" var="truck" />
<fmt:message key="infoGet.massage.capacity" var="capacity" />
<fmt:message key="infoGet.massage.status" var="status" />
<fmt:message key="infoUpdate.massage.userInfoUpdate" var="userInfoUpdate" />
<fmt:message key="infoUpdate.placeholder.truck" var="plTruck" />
<fmt:message key="infoUpdate.placeholder.status" var="plStatus" />
<fmt:message key="infoUpdate.placeholder.capacity" var="plCapacity" />
<fmt:message key="infoForm.button.create" var="create" />
<fmt:message key="infoForm.button.cancel" var="cancel" />
<fmt:message key="infoGet.massage.userId" var="userId" />
<fmt:message key="infoGet.massage.userInfo" var="userInfo" />

<html>
<head>
    <title>User info update</title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>
<body>
<jsp:include page="index.jsp"></jsp:include>
<br/>
<form method="POST" action="${pageContext.request.contextPath}/infoUpdate">
    <input type="hidden" name="redirectId" value="${param.redirectId}" />
    <table>
        <tr>
            <th>${userInfoUpdate}</th>
        </tr>
        <tr>
            <td>${truck}</td>
            <td><input type="text" placeholder="${plTruck}" name="truck"
                       value= "${truck}" required/> </td>
        </tr>
        <tr>
            <td>${status}</td>
            <td><input type="text" placeholder="${plStatus}" pattern="(^)(ready|progress|repair)($)"
                       name="tr_status" value= "${tr_status}" required /> </td>
        </tr>
        <tr>
            <td>${capacity}</td>
            <td><input type="text" placeholder="${plCapacity}" name="capacity"
                       pattern="^[0-9]+$" minlength="1" maxlength="3" value= "${capacity}" required/> </td>
        </tr>
        <tr>
            <td colspan ="2">

                <input type="submit" value= "${create}" />
                <a href="${pageContext.request.contextPath}/">${cancel}</a>
            </td>
        </tr>
    </table>
</form>
<br/>
<c:if test="${errorMassage != null}">
<p class="text-typing">${errorMassage}</p>
</c:if>
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
            <td>${userId}</td>
            <td>${infoForm.userID}</td>
        </tr>
    </table>
</form>
