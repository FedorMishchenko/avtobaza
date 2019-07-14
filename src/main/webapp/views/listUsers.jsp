<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <title>List Users</title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>
<body>
<jsp:include page="index.jsp"></jsp:include>
<br/>
<br/>
<div class="new-select-style">
    <select style="font-style: italic">
        <c:forEach items="${listUsers}" var="list">
            <option>${list.id} || ${list.userName} || ${list.phone} || ${list.email} || ${list.role} </option>
        </c:forEach>
    </select>
</div>
<br/>
<c:if test="${errorMassage != null}">
    <p class="text-typing">${errorMassage}</p>
</c:if>
<jsp:include page="userInfo.jsp"></jsp:include>
</body>
</html>