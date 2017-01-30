<%--
  Created by IntelliJ IDEA.
  User: VAblovatsky
  Date: 25.01.2017
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Ресторан</title>

        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" media="all">
        <link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet" media="all">
        <link href="${pageContext.request.contextPath}/resources/css/new-restaurant.css" rel="stylesheet" media="all">
        <link href="${pageContext.request.contextPath}/resources/css/header.css" rel="stylesheet" media="all">
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/administrationJS/getAddRestaurantsData.js"></script>

        <script language="JavaScript" type="text/javascript">
            get_data();
        </script>

    </head>
    <body>
    <jsp:include page="../header.jsp"/>

    <div id="newRestaurant">
        <div>
            <p>Изображение ресторана *: </p>
                <input id="image" type="file" name="image" multiple accept="image/*,image/jpeg"/>
        </div>
        <div>
            <p>Название ресторана *: </p> <input id="restaurantName" type="text" class="form-control" placeholder="Название ресторана" />
        </div>
        <div>
            <p>Краткая информация *:</p> <textarea id="shortInformation" rows="4" cols="20" name="text"></textarea>
        </div>
        <div>
            <p>Полная информация *:</p> <textarea id="information" rows="4" cols="20" name="text"></textarea>
        </div>
        <div >
            <p>Тип кухни *: </p>
            <div id="cuisines">
            </div>
        </div>
        <div>
            <p>Время работы *: </p> <input id="workingHours" name="workingHours" type="text" class="form-control" placeholder="Время работы" />
        </div>
        <div>
            <p>Телефон *: </p> <input id="phone" name="phone" type="text" class="form-control" placeholder="Номер телефона" />
        </div>
        <div>
            <p>Адрес *: </p>
            <input id="addresses" name="addresses" type="text" class="form-control" placeholder="Адрес ресторана" disabled />
            <select size="1" id="countries" name="search_type" OnChange="fillCities()">
                <option value="0">Выберите страну</option>
            </select>
            <select size="1" id="cities" name="search_type">
                <option value="0">Выберите город</option>
            </select>
            <button id="add-address" class="btn btn-lg btn-primary btn-block " type="button" onclick="addAddress()">+</button>
        </div>
        <div>
            <p>Вэб сайт *: </p> <input id="website" name="website" type="text" class="form-control" placeholder="Вэб сайт" />
        </div>
        <button id="button" class="btn btn-lg btn-primary btn-block button-addComment" type="button" onclick="addRestaurant()" >Добавить</button>
    </div>
</html>
