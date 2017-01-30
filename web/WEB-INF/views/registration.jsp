<%--
  Created by IntelliJ IDEA.
  User: aVa
  Date: 14.01.2017
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="translations"/>

<html>
	<head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

		<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" media="all">
		<link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet" media="all">

		<title>Регистрация</title>

	</head>

	<body>
    <jsp:include page="lang.jsp"/>
        <div class="">
            <form method="POST" action="${pageContext.request.contextPath}/registration" class="form-signin">
                <h2 class="form-signin-heading"><fmt:message key="regestration"/></h2>
                <input type="text" name="username" class="form-control" placeholder="<fmt:message key="username"/>" autofocus="true"/>
                <input type="password" name="password" class="form-control" placeholder="<fmt:message key="password"/>"/>
                <input type="password" name="confirmPassword" class="form-control" placeholder="<fmt:message key="confirmation"/>"/>
                <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="regestration"/></button>
            </form>
            <h4 class="text-center"><a href="${pageContext.request.contextPath}/login"><fmt:message key="authentication"/></a></h4>
        </div>
	</body>
</html>