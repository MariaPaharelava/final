<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page info="UserVacancyShow.jsp" language="java"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<title>Vacancies</title>
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

<!-- Navbar -->
<div class="w3-top">
    <div class="w3-bar w3-black w3-card">
        <a class="w3-bar-item w3-button w3-theme-l1"
           href="${pageContext.servletContext.contextPath}/UserVacancyShow.jsp">Vacancy</a>
        <a class="w3-bar-item w3-button"
           href="${pageContext.servletContext.contextPath}/UserInformationForUser.jsp">My Info</a>
        <a class="w3-bar-item w3-button"
           href="${pageContext.servletContext.contextPath}/UsersVacancy.jsp">Hiring</a>
        <div class="w3-dropdown-hover w3-hide-small w3-right">
            <button class="w3-button" title="More">Language <i class="fa fa-caret-down"></i></button>
            <div class="w3-dropdown-content w3-bar-block w3-card-4">
                <a class="w3-bar-item w3-button" onclick="">RU</a>
                <a class="w3-bar-item w3-button" onclick="">EN</a>
            </div>
        </div>
        <a class="w3-bar-item w3-button w3-right" href="${pageContext.servletContext.contextPath}/index.jsp">Logout</a>
    </div>
</div>

<div class="w3-main w3-animate-left" style="margin:3.7%">

    <div class="w3-main w3-animate-left" style="margin:3.7%">
        <c:forEach var="vacancies" items="${sessionScope.vacancies}" varStatus="theCount">
            <div class="w3-row" id="${theCount.index}">
                <div class="w3-twothird w3-container">
                    <h1 class="w3-text-teal">${vacancies.vacancyPosition}</h1>
                    <p>${vacancies.vacancyDescrintion}</p>
                    <br/>
                </div>
                <form action="FontController" method="get" class="w3-row w3-container">
                    <input name="command" value="add-hiring" type="hidden"/>
                    <input name="index" type="hidden" value="${theCount.index}"/>
                    <button class="w3-button w3-section w3-blue w3-ripple">Sign up for an interview</button>
                </form>
            </div>
        </c:forEach>
        <!-- END MAIN -->
    </div>
    <!-- END MAIN -->
</div>

<!-- Pagination -->

<footer id="myFooter">
    <div class="w3-container w3-theme-l1 w3-center">
        <p><a>Powered by Pogorelova Maria</a></p>
    </div>
</footer>

</body>
</html>
