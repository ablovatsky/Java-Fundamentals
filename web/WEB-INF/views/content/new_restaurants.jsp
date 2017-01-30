
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="translations"/>
<html>
<head>
    <title>Рестораны</title>

    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" media="all">
    <link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet" media="all">
    <link href="${pageContext.request.contextPath}/resources/css/restaurants.css" rel="stylesheet" media="all">
    <link href="${pageContext.request.contextPath}/resources/css/header.css" rel="stylesheet" media="all">
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.js"></script>

    <script src="${pageContext.request.contextPath}/resources/js/data/getRestaurantsShortData.js"></script>
    <c:set var="routing" value="${sessionScope.routing}"/>
    <script language="JavaScript" type="text/javascript">
        getRestaurants("${routing}");
    </script>


</head>
<body>
    <jsp:include page="header.jsp"/>

    <div class="content">
        <div class="search">
            <input id="search_value" type="text" class="input-search" placeholder="<fmt:message key="find"/>" autofocus="true"/>
            <button class="btn btn-lg btn-primary btn-block button-search" type="submit" onclick="getRestaurantsBy()"><fmt:message key="find"/></button>
            <select size="1" id="search_type" name="search_type" >
                <option value="name" selected><fmt:message key="name"/></option>
                <option value="city"><fmt:message key="city"/></option>
                <option value="country"><fmt:message key="country"/></option>
            </select>
        </div>
    </div>

    <div class="cuisine">
        <jsp:include page="left_menu.jsp"/>
    </div>

    <div id="restaurants">

    </div>

</body>

</html>
