<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page info="HrInformationForHr.jsp" language="java"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local"/>
<!DOCTYPE html>
<html>
<title>Information</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
    html, body, h1, h2, h3, h4, h5, h6 {
        font-family: "Raleway", sans-serif;
    }

</style>
<body>
<c:set var="pageName" value="HrInformationForHr.jsp" scope="session"/>

<!-- Navbar -->
<div class="w3-top">
    <div class="w3-bar w3-black w3-card">
        <a class="w3-bar-item w3-button"
           href="${pageContext.servletContext.contextPath}/HrVacancyShow.jsp">
            <fmt:message key="local.button.vacancy"/></a>

        <a class="w3-bar-item w3-button"
           href="${pageContext.servletContext.contextPath}/HrInformationForHr.jsp">
            <fmt:message key="local.button.info"/></a>

        <a class="w3-bar-item w3-button w3-theme-l1"
           href="${pageContext.servletContext.contextPath}/HrsVacancy.jsp">
            <fmt:message key="local.button.hiring"/></a>

        <a class="w3-bar-item w3-button"
           href="${pageContext.servletContext.contextPath}/CreatingVacancy.jsp">
            <fmt:message key="local.button.add.vacancy"/></a>

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

        <a class="w3-bar-item w3-button w3-right"
           href="${pageContext.servletContext.contextPath}/index.jsp">
            <fmt:message key="local.button.out"/></a>
    </div>
</div>

<div class="w3-main w3-animate-left" style="margin:3.7%;">

    <form class="w3-container w3-card-4 w3-light-grey w3-text-blue"
          style="margin-left: 30%; margin-right: 30%;">
        <h2 class="w3-center">Your Information</h2>

        <div class="w3-row w3-section">
            <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-user"></i></div>
            <div class="w3-rest">
                <a class="w3-input" name="first" type="text">Surname: ${sessionScope.user.surname}</a>
            </div>
        </div>

        <div class="w3-row w3-section">
            <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-user"></i></div>
            <div class="w3-rest">
                <a class="w3-input" name="last" type="text">Name : ${sessionScope.user.name}</a>
            </div>
        </div>

        <div class="w3-row w3-section">
            <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-envelope-o"></i></div>
            <div class="w3-rest">
                <a class="w3-input" name="email" type="text">Login : ${sessionScope.user.login}</a>
            </div>
        </div>
        <div class="w3-row w3-section">
            <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-user"></i></div>
            <div class="w3-rest">
                <a class="w3-input" name="email" type="text">Role : ${sessionScope.user.userRole}</a>
            </div>
        </div>
    </form>
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
