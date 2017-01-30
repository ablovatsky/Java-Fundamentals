<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/resources/css/lang.css" rel="stylesheet" media="all">

<select size="1" id="lang" name="search_type" OnChange="setLang()">
    <c:choose>
        <c:when test="${sessionScope.lang == 'en_US'}">
            <option value="ru_RU"  >Русский</option>
            <option value="en_US" selected>English</option>
        </c:when>
        <c:when test="${sessionScope.lang == 'ru_RU'}">
            <option value="ru_RU" selected>Русский</option>
            <option value="en_US" >English</option>
        </c:when>
        <c:otherwise>
            <option value="ru_RU" selected>Русский</option>
            <option value="en_US" >English</option>
        </c:otherwise>
    </c:choose>
</select>

<script>
    function setLang() {
        var lang = document.getElementById("lang");
        var value = lang.options[lang.selectedIndex].value;
        var url = "/setLang?lang=" + value;
        var XHR = ("onload" in new XMLHttpRequest()) ? XMLHttpRequest : XDomainRequest;
        var xhr = new XHR();
        xhr.open('GET', url, true);
        xhr.onload = function() {
            location.reload();
        };
        xhr.onerror = function() {
            console.log( 'Ошибка ' + this.status);
            location.reload();
        };
        xhr.send();

    }
</script>

