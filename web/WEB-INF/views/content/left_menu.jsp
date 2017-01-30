<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="translations"/>
<script src="${pageContext.request.contextPath}/resources/js/data/getRestaurantsShortData.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.js"></script>

<script src="${pageContext.request.contextPath}/resources/js/data/getCuisinesData.js"></script>

<script language="JavaScript" type="text/javascript">
    getCuisines();

</script>
<ul id="left_menu">
    <p id="cuisineType"><fmt:message key="cuisines"/></p>
</ul>


