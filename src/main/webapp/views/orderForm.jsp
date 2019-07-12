<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty
       language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources" />
<fmt:message key="orderForm.massage.newOrder" var="newOrder" />
<fmt:message key="orderForm.massage.startPoint" var="startPoint" />
<fmt:message key="orderForm.massage.destination" var="destination" />
<fmt:message key="orderForm.massage.distance" var="distance" />
<fmt:message key="orderForm.massage.status" var="status" />
<fmt:message key="orderForm.button.cancel" var="cancel" />
<fmt:message key="orderForm.button.create" var="create" />
<fmt:message key="orderForm.massage.statusMassage" var="statusMassage" />
<fmt:message key="orderForm.massage.distanceMassage" var="distanceMassage" />

<fmt:message key="orderForm.placeholder.start" var="start" />
<fmt:message key="orderForm.placeholder.dist" var="dist" />
<fmt:message key="orderForm.placeholder.destin" var="destin" />
<fmt:message key="orderForm.placeholder.status" var="statusPl" />

<html>
<head>
    <title>OrderForm</title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>
<body>
<jsp:include page="index.jsp"></jsp:include>
<h3>${newOrder}</h3>

<form method="POST" action="${pageContext.request.contextPath}/orderForm">
    <input type="hidden" name="redirectId" value="${param.redirectId}" />
    <table>
        <tr>
            <td>${startPoint}</td>
            <td><input type="text" placeholder="${start}" name="startPoint" minlength="1" maxlength="15"
                       value= "${order.startPoint}" required/> </td>
        </tr>
        <tr>
            <td>${destination}</td>
            <td><input type="text" placeholder="${destin}" name="destination"minlength="1" maxlength="15"
                       value= "${order.destination}" required/> </td>
        </tr>
        <tr>
            <td>${distance}</td>
            <td><input type="text" placeholder="${dist}" name="distance" pattern="^[0-9]+$"
                       value= "${order.distance}" required/> </td>
        </tr>
        <tr>
            <td>${status}</td>
            <td><input type="text" placeholder="${statusPl}" name="status" pattern="(^)(open|progress|close|cancel)($)"
                       value= "${order.status}" required/> </td>
        </tr>
        <tr>
            <td colspan ="2">

                <input type="submit" value= "${create}" />
                <a href="${pageContext.request.contextPath}/">${cancel}</a>
            </td>
        </tr>
    </table>
</form>

        <p class="text-typing">${massage}</p>
        <p class="text-typing">${statusMassage}</p>
        <p class="text-typing">${distanceMassage}</p>

</body>
</html>
