<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty
       language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources" />

<fmt:message key="registrationForm.massage.login" var="login" />
<fmt:message key="registrationForm.massage.password" var="password" />
<fmt:message key="registrationForm.massage.phone" var="phone" />
<fmt:message key="registrationForm.massage.email" var="email" />
<fmt:message key="registrationForm.massage.role" var="role" />

<fmt:message key="registrationForm.placeholder.login" var="loginPL" />
<fmt:message key="registrationForm.placeholder.password" var="passwordPL" />
<fmt:message key="registrationForm.placeholder.phone" var="phonePL" />
<fmt:message key="registrationForm.placeholder.email" var="emailPL" />
<fmt:message key="registrationForm.placeholder.role" var="rolePL" />

<fmt:message key="registrationForm.button.submit" var="input" />
<fmt:message key="registrationForm.button.cancel" var="cancel" />

<html>
<head>
    <title>Registration form</title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>
<body>
<form>

    <table>
        <tr>
            <td>${login}</td>
            <td><input type="text" placeholder="${loginPL}" name="userName"
                       minlength="3" maxlength="15" value="${user.userName}"/></td>
        </tr>
        <tr>
            <td>${password}</td>
            <td><input type="password" placeholder="${passwordPL}" name="password"
                       minlength="3" maxlength="15" value="${user.password}" required/></td>
        </tr>
        <tr>
            <td>${email}</td>
            <td><input type="email" pattern="^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$"
                       placeholder="${emailPL}" name="email" value="${user.email}" required/></td>
        </tr>
        <tr>
            <td>${phone}</td>
            <td><input type="text" pattern="^[0-9]+$"
                       minlength="10" maxlength="10" placeholder="${phonePL}"
                       name="phone" value="${user.phone}" required/></td>
        </tr>
        <c:if test="${loginedUser.role == 'admin'}">
            <tr>
                <td>${role}</td>
                <td><input type="text" pattern="(^)(admin|manager|user)($)"
                           placeholder="${rolePL}" name="role" value="${user.role}" required/></td>
            </tr>
        </c:if>
        <tr>
            <td colspan="2">
                <input type="submit" value="${input}"/>
                <a href="${pageContext.request.contextPath}/">${cancel}</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
