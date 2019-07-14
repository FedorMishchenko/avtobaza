<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty
       language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources" />

<fmt:message key="registration.massage.role" var="role" />
<fmt:message key="setUserRole.button.input" var="input" />
<fmt:message key="setUserRole.massage" var="massage" />
<fmt:message key="setUserRole.placeholder.role" var="userRole" />
<fmt:message key="setUserRole.placeholder.userId" var="userId" />

<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>${massage}</h3>
<form method="POST" action="${pageContext.request.contextPath}/adminTask">
    <input type="text" pattern="(^)(admin|manager|user)($)"
           placeholder="${userRole}" name="setUserRole" required>
    <input type="text" pattern="([0-9]+)" minlength="1" maxlength="11"
           placeholder="${userId}" name="setUserId" required>
    <input type="submit" value="${input}">
</form>
<p class="text-typing"><strong><i>${role}</i></strong></p>
</body>
</html>
