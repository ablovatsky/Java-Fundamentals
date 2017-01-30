<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="translations"/>

<html>
    <head>
        <title>Ресторан</title>

        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" media="all">
        <link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet" media="all">
        <link href="${pageContext.request.contextPath}/resources/css/restaurant.css" rel="stylesheet" media="all">
        <link href="${pageContext.request.contextPath}/resources/css/header.css" rel="stylesheet" media="all">
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.js"></script>

        <script src="${pageContext.request.contextPath}/resources/js/data/getRestaurantData.js"></script>

        <c:set var="routing" value="${sessionScope.routing}"/>
        <script language="JavaScript" type="text/javascript">
            getRestaurant("${routing}");
        </script>


    </head>
    <body>
    <jsp:include page="header.jsp"/>
    <div>

        <div id="information">
            <p id="headInformation"> </p>
            <p id="textInformation">

            </p>
        </div>
        <div id="cuisinesText"><fmt:message key="cuisines"/>: </div>
        <div id="cuisinesValue">

        </div>
        <div id="workingHoursText">Время работы: </div>
        <div id="workingHoursValue"></div>
        <br/>
        <div id="phoneText">Тел.: </div>
        <div id="phoneValue"></div>
        <br/>
        <div id="addressesText">Адресс: </div>
        <div id="addressesValue">

        </div>
        <br/>
        <div id="websiteText">Сайт: </div>
        <div id="websiteValue"></div>
        <div class="download">
            <a id="download"></a>
        </div>


    </div>
    <div id="comments">
        <p id="headComments">Отзывы</p>

        <br/>
        <label>
            <p>
                Добавить коментарий...
            </p>
            <textarea id="newComment" rows="10" cols="116" name="text"></textarea>
        </label>
        <div id="dButton">
            <button id="button" class="btn btn-lg btn-primary btn-block button-addComment" type="button" onclick="addComment('${sessionScope.USER.getId()}')">Добавить</button>
        </div>
    </div>




</html>
