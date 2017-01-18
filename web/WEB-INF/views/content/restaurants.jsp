<%--
  Created by IntelliJ IDEA.
  User: aVa
  Date: 14.01.2017
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<jsp:include page="header.jsp"/>

<div class="container">
    <h2 class="form-heading">Скоро тут будут отображаться лучшие рестораны мира. Оставайтесь с нами.</h2>
    <form method="GET" action="${pageContext.request.contextPath}/logout" class="form-signin">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Выход</button>
    </form>
    <h4 class="text-center"><a href="${pageContext.request.contextPath}/restaurants">Добавить ресторан</a></h4>

</div>
<!-- /container -->
</body>
</html>
