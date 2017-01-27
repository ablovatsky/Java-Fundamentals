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
        <link href="${pageContext.request.contextPath}/resources/css/new-restaurant.css" rel="stylesheet" media="all">
        <link href="${pageContext.request.contextPath}/resources/css/header.css" rel="stylesheet" media="all">
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.js"></script>

    </head>
    <body>
    <jsp:include page="../header.jsp"/>
    <c:set var="cuisines" value="${requestScope.cuisines}"/>
    <c:set var="countries" value="${requestScope.countries}"/>
    <div id="newRestaurant">
        <div>
            <p>Изображение ресторана *: </p>
            <form enctype="multipart/form-data" method="post">
                <input id="image" type="file" name="photo" multiple accept="image/*,image/jpeg"/>
            </form>
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
        <div>
            <p>Тип кухни *: </p>
            <c:forEach var="cuisine" items="${cuisines}">
                <input type="checkbox" id="${cuisine.getName()}" name="checkBox" value="${cuisine.getId()}">
                <label from="${cuisine.getName()}">${cuisine.getName()}</label>
            </c:forEach>
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
            <select size="1" id="country" name="search_type">
                <option value="">Выберите страну</option>
                <c:forEach var="country" items="${countries}">
                    <option value=${country.getId()}>${country.getName()}</option>
                </c:forEach>
            </select>
            <select size="1" id="city" name="search_type">
                <option value="">Выберите город</option>
            </select>
            <button id="add-address" class="btn btn-lg btn-primary btn-block " type="button" >+</button>
        </div>
        <div>
            <p>Вэб сайт *: </p> <input id="website" name="website" type="text" class="form-control" placeholder="Вэб сайт" />
        </div>
        <button id="button" class="btn btn-lg btn-primary btn-block button-addComment" type="button" >Добавить</button>
    </div>


        <script language="JavaScript" type="text/javascript">
            $("#country")
                .change(function () {
                    $( "#country").find("option:selected" ).each(function() {
                        var json ="{country: {id:" + $(this).val() + "}}";
                        $.ajax({
                            type: "POST",
                            data: json,
                            url: "/administration/getCities"
                        })
                        $("#city").html('<option value="">Выберите город</option><option value="">'+$(this).text()+'</option>');
                    });

                })
                .change();


            $(document).ready(function () {
                $("#button").click(function () {
                    var cuisines = document.getElementsByName("checkBox");
                    for(var i=0; i<cuisines.length; i++)
                        if (cuisines[i].checked) {
                            alert( cuisines[i].value );
                        }

                })
            })
        </script>

</html>
