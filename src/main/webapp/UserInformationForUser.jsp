<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page info="UserInformationForUser.jsp" language="java"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
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
        <a class="w3-bar-item w3-button w3-right"
           href="${pageContext.servletContext.contextPath}/index.jsp">Logout</a>
    </div>
</div>

<div class="w3-main w3-animate-left" style="margin:3.7%;">

    <form action="/action_page.php" class="w3-container w3-card-4 w3-light-grey w3-text-blue"
          style="margin-left: 30%; margin-right: 30%;">
        <h2 class="w3-center">Your Information</h2>

        <div class="w3-row w3-section">
            <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-user"></i></div>
            <div class="w3-rest">
                <a class="w3-input w3-border" name="first" type="text">First Name</a>
            </div>
        </div>

        <div class="w3-row w3-section">
            <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-user"></i></div>
            <div class="w3-rest">
                <a class="w3-input w3-border" name="last" type="text">Last Name</a>
            </div>
        </div>

        <div class="w3-row w3-section">
            <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-envelope-o"></i></div>
            <div class="w3-rest">
                <a class="w3-input w3-border" name="email" type="text">Login</a>
            </div>
        </div>
    </form>
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
