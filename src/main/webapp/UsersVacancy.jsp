<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page info="UsersVacancy.jsp" language="java"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<title>Hiring</title>
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
        <c:url value="UserVacancyShow.jsp" var="newUrlVacancy" />
        <a class="w3-bar-item w3-button"
           href="<c:out value="${ newUrlVacancy }"/>">Vacancy</a>

        <c:url value="UserInformationForUser.jsp" var="newUrlInfo" />
        <a class="w3-bar-item w3-button"
           href="<c:out value="${ newUrlInfo }"/>">My Info</a>

        <c:url value="UsersVacancy.jsp" var="newUrlHiring" />
        <a class="w3-bar-item w3-button w3-theme-l1"
           href="<c:out value="${ newUrlHiring }"/>">Hiring</a>

        <div class="w3-dropdown-hover w3-hide-small w3-right">
            <button class="w3-button" title="More">Language <i class="fa fa-caret-down"></i></button>
            <div class="w3-dropdown-content w3-bar-block w3-card-4">
                <a class="w3-bar-item w3-button" onclick="">RU</a>
                <a class="w3-bar-item w3-button" onclick="">EN</a>
            </div>
        </div>

        <c:url value="index.jsp" var="newUrlIndex" />
        <a class="w3-bar-item w3-button w3-right" href="<c:out value="${ newUrlIndex }"/>">Logout</a>
    </div>
</div>

<div class="w3-main w3-animate-left" style="margin:3.75%">
    <div class="w3-row-padding">
        <c:forEach var="hiring" items="${sessionScope.hirings}" varStatus="theCount">
            <div class="w3-quarter" id="${theCount.index}">
                <ul class="w3-ul w3-border w3-center w3-hover-shadow">
                    <li class="w3-green w3-xlarge w3-padding-8">${hiring.vacancyName}</li>
                    <li class="w3-padding-8"><b>${hiring.candidateSurname} ${hiring.candidateName}</b></li>
                    <li class="w3-padding-8"><b>${hiring.hrSurname} ${hiring.hrName}</b></li>

                    <c:if test="${not empty hiring.comment}">
                        <li class="w3-padding-8">${hiring.comment}</li>
                    </c:if>
                    <c:if test="${empty hiring.comment}">
                        <li class="w3-text-grey w3-padding-8">No Value Set</li>
                    </c:if>

                    <li class="w3-padding-8">${hiring.hiringStatus}</li>

                    <li class="w3-padding-4">
                        <c:if test="${not empty hiring.offerEmount and hiring.offerEmount != 0}">
                            <h3 class="w3-wide">$ ${hiring.offerEmount}</h3>
                            <span class="w3-opacity">per month</span>
                        </c:if>
                        <c:if test="${empty hiring.offerEmount and hiring.offerEmount == 0}">
                            <h3 class="w3-text-grey w3-wide">No Value Set</h3>
                            <span class="w3-opacity w3-text-wight">___</span>
                        </c:if>
                    </li>
                    <li class="w3-light-grey w3-padding-16">
                        <form action="FontController" method="get" class="w3-row w3-container">
                            <input name="command" value="delete-hiring-by-user" type="hidden"/>
                            <input name="index" type="hidden" value="${theCount.index}"/>
                            <button class="w3-button w3-red w3-padding-large">Cancel</button>
                        </form>
                    </li>
                </ul>
            </div>
        </c:forEach>

    </div>
</div>
<!-- END MAIN -->

<!-- Pagination -->

<footer id="myFooter">
    <div class="w3-container w3-theme-l1 w3-center w3-display-bottommiddle" style="width:100%;">
        <p><a>Powered by Pogorelova Maria</a></p>
    </div>
</footer>

</body>
</html>
