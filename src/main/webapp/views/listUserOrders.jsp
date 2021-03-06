<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <title>List Orders</title>
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
        <c:forEach items="${listUserOrders}" var="list">
            <option>${list.id} || ${list.startPoint} || ${list.destination} || ${list.distance}
                || ${list.status} || ${list.date} || ${list.userId}</option>
        </c:forEach>
    </select>
</div>
</body>
</html>