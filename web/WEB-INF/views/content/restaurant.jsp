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
    <div>

        <div id="information">
            <p id="headInformation"> ${restaurant.name}</p>
            <p id="textInformation">
                <img src="${pageContext.request.contextPath}/image?index=${restaurant.id}" alt="Img"/>
                ${restaurant.information}
            </p>
        </div>
        <div id="workingHoursText">Время работы: </div>
        <div id="workingHoursValue"> ${restaurant.workingHours}</div>
        <br/>
        <div id="phoneText">Тел.: </div>
        <div id="phoneValue"> ${restaurant.phone}</div>
        <br/>
        <div id="addressesText">Адресс: </div>
        <div id="addressesValue">
                <c:forEach var="address" items="${restaurant.addresses}">
                ${address.toString()}<br/>
            </c:forEach>
        </div>
        <br/>
        <div id="websiteText">Сайт: </div>
        <div id="websiteValue"> <a href="http://${restaurant.website}">${restaurant.website}</a></div>

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
                <div id="date">
                    <p>
                        ${comment.getDate()}
                    </p>
                </div>
                <div id="textComment">
                    <p>
                        ${comment.getComment()}
                    </p>
                </div>
            </div>
        </c:forEach>
        <br/>
        <form action="/addComment" method="POST">
            <label>
                <p>
                    Добавить коментарий...
                </p>
                <textarea rows="10" cols="116" name="text"></textarea>
                <button class="btn btn-lg btn-primary btn-block button-addComment" type="submit">Добавить</button>
            </label>
        </form>


    </div>




    </body>
</html>
