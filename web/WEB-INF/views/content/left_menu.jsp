<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="cuisines" value="${requestScope.cuisines}"/>
<c:if test="${!empty cuisines}">
    <ul class="left_menu">
        <c:forEach items = "${cuisines}" var="cuisine">
            <li>
                <a href="${pageContext.request.contextPath}/search?cuisine=${cuisine.name}">${cuisine.name}</a>
            </li>
        </c:forEach>
    </ul>
</c:if>

