<%--
  Created by IntelliJ IDEA.
  User: VAblovatsky
  Date: 17.01.2017
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="translations"/>
<jsp:include page="../lang.jsp"/>
<div class="header">
    <div class="user_menu">
        <ul id="menu">
            <li>
                <a id="first_li" href="${pageContext.request.contextPath}/restaurants"><fmt:message key="restaurants"/></a>
            </li>
            <li>
                <fmt:message key="administration"/>
                <div class="d_ul">
                </div>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/administration/restaurants"><fmt:message key="restaurants"/></a></li>
                       <%-- <li><a href="${pageContext.request.contextPath}">Адреса</a></li>
                        <li><a href="${pageContext.request.contextPath}/administration/users">Пользователи</a></li>--%>
                    </ul>

            </li>

        </ul>
    </div>
    <div class="user">
        <img id="user" src="${pageContext.request.contextPath}/resources/images/user.ico">
        ${sessionScope.USER.username}
        <a href="${pageContext.request.contextPath}/logout"><img id="exit" src="${pageContext.request.contextPath}/resources/images/exit.png"></a>
    </div>

</div>
