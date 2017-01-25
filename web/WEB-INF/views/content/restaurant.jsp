<%--
  Created by IntelliJ IDEA.
  User: VAblovatsky
  Date: 25.01.2017
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Ресторан</title>

        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" media="all">
        <link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet" media="all">
        <link href="${pageContext.request.contextPath}/resources/css/restaurant.css" rel="stylesheet" media="all">
        <link href="${pageContext.request.contextPath}/resources/css/header.css" rel="stylesheet" media="all">

    </head>
    <body>
    <jsp:include page="header.jsp"/>
    <c:set var="restaurant" value="${requestScope.restaurant}"/>
    <div id="information">
        <p id="headInformation"> ${restaurant.name}</p>
        <p id="textInformation">
            <img src="${pageContext.request.contextPath}/image?index=${restaurant.id}" alt="Img"/>
            ${restaurant.information}
        </p>
        <p></p>
        <d id="text">Время работы: </d>
        <d id="value"> ${restaurant.workingHours}</d>
        <br/>
        <d id="text">Тел.: </d>
        <d id="value"> ${restaurant.phone}</d>
        <br/>
        <d id="text">Адресс: </d>
        <d id="value">
                <c:forEach var="address" items="${restaurant.addresses}">
                ${address.toString()}<br/>
            </c:forEach>
        </d>
        <br/>
        <d id="text">Сайт: </d>
        <d id="value"> <a href="http://${restaurant.website}">${restaurant.website}</a></d>

    </div>
    <div id="comments">
        <p id="headComments">Отзывы</p>
        <c:forEach var="comment" items="${restaurant.comments}">
            <div id="comment">
                <div id="username">
                    <p>
                        ${comment.getUser().getUsername()}
                    </p>
                </div>
                <div id="textComment">
                    <p>
                        ${comment.getComment()}
                    </p>
                </div>
            </div>
        </c:forEach>
    </div>



    </body>
</html>
