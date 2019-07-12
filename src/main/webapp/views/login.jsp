<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty
       language ? language : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>Login</title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>
<body>
<form>
        <select class="select-css" id="language" name="language" onchange="submit()">
            <option value="ru" ${language == 'ru' ? 'selected' : ''}>RU</option>
            <option value="en" ${language == 'en' ? 'selected' : ''}>EN</option>
        </select>
</form>
<br/>
<br/>
<form method="POST" action="${pageContext.request.contextPath}/login">
    <input type="hidden" name="redirectId" value="${param.redirectId}"/>
    <table style="margin: auto">
        <tr>
            <td>
                <label for="userName"><fmt:message key="login.label.userName"/></label>
            </td>
            <td>
                <input type="text" id="userName" name="userName" pattern="^[A-Za-zА-ЯЁа-яё0-9]+$"
                       value="${user.userName}" required/>
            </td>
        </tr>
        <tr>
            <td>
                <label for="password"><fmt:message key="login.label.password"/></label>
            </td>
            <td>
                <input type="password" id="password" name="password" pattern="^[A-Za-zА-ЯЁа-яё0-9]+$"
                       value="${user.password}" required/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <fmt:message key="login.button.submit" var="buttonValue"/>
                <input type="submit" name="submit" value="${buttonValue}">

                <fmt:message key="login.button.registration" var="registration"/>
                <a href="registration">${registration}</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>