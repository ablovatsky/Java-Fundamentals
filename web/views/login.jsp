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

        <title>Авторизация</title>

        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" media="all">
        <link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet" media="all">

    </head>

    <body>

        <div class="container">

            <form method="POST" action="${pageContext.request.contextPath}/login" class="form-signin">
                <h2 class="form-heading">Авторизация</h2>
                <input name="username" type="text" class="form-control" placeholder="Имя пользователя" autofocus="true"/>
                <input name="password" type="password" class="form-control" placeholder="Пароль"/>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Авторизоваться</button>
                <h4 class="text-center"><a href="${pageContext.request.contextPath}/views/registration.jsp">Регестрация</a></h4>
            </form>

        </div>
    <!-- /container -->
    </body>
</html>
