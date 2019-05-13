<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page info="HrsVacancy.jsp" language="java"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html lang="en">
<title>HRs Vacancies</title>
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

<c:set var="pageName" value="HrsVacancy.jsp" scope="session"/>

<!-- Navbar -->
<div class="w3-top">
    <div class="w3-bar w3-black w3-card">
        <form action="Controller" method="get">
            <a name="command" value="go-to-for-user" type="hidden"/>
            <a class="w3-bar-item w3-button" href="${pageContext.servletContext.contextPath}/HrVacancyShow.jsp">Vacancy</a>
            <a class="w3-bar-item w3-button" href="${pageContext.servletContext.contextPath}/HrInformationForHr.jsp">My Info</a>
            <a class="w3-bar-item w3-button w3-theme-l1" href="${pageContext.servletContext.contextPath}/HrsVacancy.jsp">Hiring</a>
            <a class="w3-bar-item w3-button" href="${pageContext.servletContext.contextPath}/CreatingVacancy.jsp">Add Vacancy</a>
            <div class="w3-dropdown-hover w3-hide-small w3-right">
                <button class="w3-button" title="More">Language <i class="fa fa-caret-down"></i></button>
                <div class="w3-dropdown-content w3-bar-block w3-card-4">
                    <a class="w3-bar-item w3-button" onclick="">RU</a>
                    <a class="w3-bar-item w3-button" onclick="">EN</a>
                </div>
            </div>
            <a class="w3-bar-item w3-button w3-right" href="${pageContext.servletContext.contextPath}/index.jsp">Logout</a>
        </form>
    </div>
</div>
<!-- Main content: shift it to the right by 250 pixels when the sidebar is visible -->
<div class="w3-main w3-animate-left" style="margin:3.75%">
    <div class="w3-row-padding">
        <div class="w3-quarter">
            <c:forEach var="hirings" items="${hirings}">
                <ul class="w3-ul w3-border w3-center w3-hover-shadow" \>
                    <li class="w3-green w3-xlarge w3-padding-8">${hirings.vacancyId}</li>
                    <li class="w3-padding-8"><b>${hirings.hrId}</b></li>
                    <li class="w3-padding-8"><b>${hirings.candidateId}</b></li>
                    <li class="w3-padding-8">${hirings.comment}</li>
                    <li class="w3-padding-8">${hirings.hiringStatus}</li>
                    <li class="w3-padding-4">
                        <h3 class="w3-wide">$ ${hirings.offerEmount}</h3>
                        <span class="w3-opacity">per month</span>
                    </li>
                    <li class="w3-light-grey w3-padding-8">
                        <input name="command" value="go-to-for-user" type="hidden"/>
                        <input type="submit" name="goTo" class="w3-button w3-teal w3-padding-large"
                               value="${"Edit"}">
                    </li>
                </ul>
            </c:forEach>
            </form>
        </div>
    </div>


    <!-- END MAIN -->
</div>

<!-- Pagination -->

<footer id="myFooter">
    <div class="w3-container w3-theme-l1 w3-center w3-display-bottommiddle" style="width:100%;">
        <p><a>Powered by Pogorelova Maria</a></p>
    </div>
</footer>

</body>
</html>
