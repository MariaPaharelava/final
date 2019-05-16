<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page info="EditVacancy.jsp" language="java"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local"/>

<!DOCTYPE html>
<html>
<title>500</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
    body, h1 {
        font-family: "Raleway", sans-serif
    }

    body, html {
        height: 100%
    }

</style>
<body>
<c:set var="pageName" value="500.jsp" scope="session"/>

    <!-- Navbar -->
    <div class="w3-top">
        <div class="w3-bar w3-black w3-card">

            <a class="w3-bar-item w3-button w3-right"
               href="${pageContext.servletContext.contextPath}/index.jsp">
                <fmt:message key="local.button.back"/></a>

            <div class="w3-dropdown-hover w3-hide-small w3-right">
                <button class="w3-button" title="More"><fmt:message key="local.button.language"/>
                    <i class="fa fa-caret-down"></i></button>
                <div class="w3-dropdown-content w3-bar-block w3-card-4">
                    <form action="FontController" method="get">
                        <input type="hidden" name="command" value="change-local">
                        <button name="local" class="w3-bar-item w3-button" value="RU">
                            <fmt:message key="local.button.ru"/></button>
                        <button name="local" class="w3-bar-item w3-button" value="EN">
                            <fmt:message key="local.button.en"/></button>
                    </form>
                </div>
            </div>

        </div>
    </div>


<div class="w3-main w3-animate-left" style="margin-top:10%">
    <div class="w3-main w3-animate-left">
        <h1 class="w3-text-teal w3-center">
            <fmt:message key="local.label.500"/><br> 500
        </h1>
    </div>
    <!-- END MAIN -->
</div>
<!-- Pagination -->

<footer id="myFooter">
    <div class="w3-container w3-theme-l1 w3-center w3-display-bottommiddle" style="width:100%;">
        <p><a><fmt:message key="local.footer"/></a></p>
    </div>
</footer>
</body>
</html>
