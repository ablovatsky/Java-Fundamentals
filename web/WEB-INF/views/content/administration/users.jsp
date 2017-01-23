<%--
  Created by IntelliJ IDEA.
  User: aVa
  Date: 14.01.2017
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Рестораны</title>

    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" media="all">
    <link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet" media="all">
    <link href="${pageContext.request.contextPath}/resources/css/header.css" rel="stylesheet" media="all">

</head>

<body>
<jsp:include page="../header.jsp"/>

<div class="">
    <c:set var="users" value="${requestScope.listUsers}"/>
    <c:if test="${!empty users}">
        <table>
            <tr>
            <th width="100">ID</th>
            <th width="200">Имя</th>
            <th width="200">Роли</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr >
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td><a href="${pageContext.request.contextPath}/user?userId${user.id}=">Получить роль</a></td>
                </tr>
            </c:forEach>
    </table>
</c:if>


</div>
<!-- /container -->
</body>
</html>
