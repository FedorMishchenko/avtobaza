<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty
       language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources" />
<fmt:message key="accessDenied.massage" var="massage" />
<!DOCTYPE html>
<html>
<head>
    <title>Access Denied</title>
</head>
<style>
    <%@include file='../css/style.css' %>
</style>
<body>

<jsp:include page="index.jsp"></jsp:include>

<br/>
<br/>
<br/>
<h2 id="blink2" style="margin: auto"><strong>${massage}</strong></h2>
</body>
</html>
