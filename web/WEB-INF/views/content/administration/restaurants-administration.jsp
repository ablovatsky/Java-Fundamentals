<%--
  Created by IntelliJ IDEA.
  User: aVa
  Date: 14.01.2017
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <link href="${pageContext.request.contextPath}/resources/css/restaurants.css" rel="stylesheet" media="all">
    <link href="${pageContext.request.contextPath}/resources/css/header.css" rel="stylesheet" media="all">

</head>

<body>
<jsp:include page="../header.jsp"/>

<div class="content">
    <div class="search">
        <form action="/administration/search" method="POST">
            <input name="name" type="text" class="input-search" placeholder="Поиск" autofocus="true"/>
            <button class="btn btn-lg btn-primary btn-block button-search" type="submit">Поиск</button>
            <select size="1" id="search_type" name="search_type">
                <option value="name" selected>Название</option>
                <option value="city">Город</option>
                <option value="country">Страна</option>
            </select>
        </form>
    </div>

    <div class="restaurants">
        <div id="newRestaurant">
            <form action="/administration/add-restaurants" method="GET">
                <button>Новый ресторан</button>
            </form>
        </div>
        <c:set var="restaurants" value="${sessionScope.restaurants}"/>
        <c:if test="${!empty restaurants}">
            <c:forEach items="${restaurants}" var="restaurant">
                <div class="restaurant">
                    <div id="rest_img">
                        <a href="${pageContext.request.contextPath}/restaurant?id=${restaurant.id}"><img src="${pageContext.request.contextPath}/image?index=${restaurant.id}" alt="Img"/></a>
                    </div>
                    <div id="rest_inf">
                        <a href="${pageContext.request.contextPath}/restaurant?id=${restaurant.id}">Редактировать "${restaurant.name}"</a><br/><br/>
                            ${restaurant.shortInformation}<br/><br/>
                        <a href="http://${restaurant.website}">${restaurant.website}</a>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </div>


</div>
<!-- /container -->
</body>
</html>
