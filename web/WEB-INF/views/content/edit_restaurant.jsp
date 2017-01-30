<%--
  Created by IntelliJ IDEA.
  User: VAblovatsky
  Date: 25.01.2017
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="translations"/>
<html>
<head>
    <title>Ресторан</title>

    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" media="all">
    <link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet" media="all">
    <link href="${pageContext.request.contextPath}/resources/css/new-restaurant.css" rel="stylesheet" media="all">
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

<div id="newRestaurant">
    <div>
        <p><fmt:message key="restaurantImage"/> *: </p>
        <input id="image" type="file" name="image" multiple accept="image/*,image/jpeg"/>
    </div>
    <div>
        <p><fmt:message key="restaurantName"/> *: </p> <input id="restaurantName" type="text" class="form-control" placeholder="Название ресторана" />
    </div>
    <div>
        <p><fmt:message key="shortInformation"/> *:</p> <textarea id="shortInformation" rows="4" cols="20" name="text"></textarea>
    </div>
    <div>
        <p><fmt:message key="fullInformation"/> *:</p> <textarea id="information" rows="4" cols="20" name="text"></textarea>
    </div>
    <div >
        <p><fmt:message key="cuisines"/> *: </p>
        <div id="cuisines">
        </div>
    </div>
    <div>
        <p><fmt:message key="workingHours"/> *: </p> <input id="workingHours" name="workingHours" type="text" class="form-control" placeholder="Время работы" />
    </div>
    <div>
        <p><fmt:message key="phone"/> *: </p> <input id="phone" name="phone" type="text" class="form-control" placeholder="Номер телефона" />
    </div>
    <div>
        <p><fmt:message key="address"/> *: </p>
        <input id="addresses" name="addresses" type="text" class="form-control" placeholder="Адрес ресторана" />
        <select size="1" id="countries" name="search_type" OnChange="fillCities()">
            <option value="0"><fmt:message key="selectCity"/></option>
        </select>
        <select size="1" id="cities" name="search_type">
            <option value="0"><fmt:message key="selectCity"/></option>
        </select>
        <button id="add-address" class="btn btn-lg btn-primary btn-block " type="button" onclick="addAddress()">+</button>
    </div>
    <div>
        <p><fmt:message key="website"/> *: </p> <input id="website" name="website" type="text" class="form-control" placeholder="Вэб сайт" />
    </div>
    <button id="button" class="btn btn-lg btn-primary btn-block button-addComment" type="button" onclick="editRestaurant()" ><fmt:message key="apply"/></button>
</div>
</html>
