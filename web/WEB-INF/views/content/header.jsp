<%--
  Created by IntelliJ IDEA.
  User: VAblovatsky
  Date: 17.01.2017
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="header">
    <div id="user_menu">
        <ul id="menu">
            <li>
                <img id="user" src="${pageContext.request.contextPath}/resources/images/user.ico">
                <ul>
                    <li>Administrator</li>
                    <li><a href="${pageContext.request.contextPath}">Отзывы</a></li>
                </ul>
            </li>
            <li>
                <img id="setting" src="${pageContext.request.contextPath}/resources/images/settings.jpg">
                <ul>
                    <li><a href="${pageContext.request.contextPath}">Рестораны</a></li>
                    <li><a href="${pageContext.request.contextPath}">Адреса</a></li>
                    <li><a href="${pageContext.request.contextPath}">Пользователи</a></li>
                </ul>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/logout"><img id="exit" src="${pageContext.request.contextPath}/resources/images/exit.png"></a>
            </li>
        </ul>
    </div>

</div>
