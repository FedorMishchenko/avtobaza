<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty
       language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources" />
<fmt:message key="infoForm.massage.truck" var="massageTruck" />
<fmt:message key="infoForm.massage.capacity" var="massageCapacity" />
<fmt:message key="infoForm.massage.status" var="massageStatus" />
<fmt:message key="infoForm.placeholder.truck" var="placeholderTruck" />
<fmt:message key="infoForm.placeholder.capacity" var="placeholderCapacity" />
<fmt:message key="infoForm.placeholder.status" var="placeholderStatus" />
<fmt:message key="infoForm.button.create" var="create" />
<fmt:message key="infoForm.button.cancel" var="cancel" />
<html>
<head>
    <title>User info form</title>
    <style>

        <%@include file='../css/style.css' %>
    </style>
</head>
<body>
<jsp:include page="index.jsp"></jsp:include>
<br/>
<form method="POST" action="${pageContext.request.contextPath}/infoForm">
   <input type="hidden" name="redirectId" value="${param.redirectId}" />
    <table>
        <tr>
            <td>${massageTruck}</td>
            <td><input type="text" placeholder="${placeholderTruck}" name="truck"
                       value= "${truck}" required/> </td>
        </tr>
        <tr>
            <td>${massageStatus}</td>
            <td><input type="text" placeholder="${placeholderStatus}" pattern="(^)(ready|progress|repair)($)"
                       name="tr_status" value= "${tr_status}" required/> </td>
        </tr>
        <tr>
            <td>${massageCapacity}</td>
            <td><input type="text" placeholder="${placeholderCapacity}" name="capacity"
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

<c:if test="${errorMassage != null}">
    <p class="text-typing">${errorMassage}</p>
</c:if>
</body>
</html>
