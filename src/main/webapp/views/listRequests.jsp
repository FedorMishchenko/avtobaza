<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>List Requests</title>
    <style>
        <%@include file='../css/style.css' %>
        footer {
            position: fixed;
            left: 20px; bottom: 20px;
        }
    </style>
</head>
<body>
<jsp:include page="index.jsp"></jsp:include>
<br/>
<br/>
<div class="new-select-style">
    <select style="font-style: italic">
        <c:forEach items="${listRequests}" var="list">
            <option>|| id: ${list.id} || user id: ${list.userId} ||order id: ${list.orderId} </option>
        </c:forEach>
    </select>
</div>
<footer>
    <jsp:include page="userInfo.jsp"></jsp:include>
</footer>
</body>
</html>
