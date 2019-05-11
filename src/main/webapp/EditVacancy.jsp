<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page info="EditVacancy.jsp" language="java"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<title>Edit Vacancies</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
    html,body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", sans-serif;}

</style>
<body>

<c:set var="pageName" value="EditVacancy.jsp" scope="session"/>

<!-- Navbar -->
<div class="w3-top">
    <div class="w3-bar w3-black w3-card">
        <form action="Controller" method="get">
            <input name="command" value="go-to-for-user" type="hidden"/>
            <input type="submit" name="goTo" class="w3-bar-item w3-button" value="${"Vacancy"}">
            <input type="submit" name="goTo" class="w3-bar-item w3-button" value="${"My Info"}">
            <input type="submit" name="goTo" class="w3-bar-item w3-button w3-theme-l1" value="${"Hiring"}">
            <input type="submit" name="goTo" class="w3-bar-item w3-button" value="${"Add Vacancy"}">
            <div class="w3-dropdown-hover w3-hide-small w3-right">
                <button class="w3-button" title="More">Language <i class="fa fa-caret-down"></i></button>
                <div class="w3-dropdown-content w3-bar-block w3-card-4">
                    <a class="w3-bar-item w3-button" onclick="">RU</a>
                    <a class="w3-bar-item w3-button" onclick="">EN</a>
                </div>
            </div>
            <input type="submit" name="goTo" class="w3-bar-item w3-button w3-right" value="${"Out"}">
        </form>
    </div>
</div>



<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- Main content: shift it to the right by 250 pixels when the sidebar is visible -->
<div class="w3-main w3-animate-left" style="margin:3.7%;">

    <form action="/action_page.php" class="w3-container w3-card-4 w3-light-grey w3-text-blue" style="margin-left: 30%; margin-right: 30%;">
        <h2 class="w3-center">Edit Vacancy <i class="fa fa-pencil-square-o"></i></h2>

        <div class="w3-row w3-section">
            <div class="w3-rest">
                <h5 class="w3-left w3-text-black">Salary: </h5>
                <input class="w3-input w3-border" name="salary" type="text" placeholder="Salary">
            </div>
        </div>

        <div class="w3-row w3-section w3-text-black">
            <h5 class="w3-left">Select status: </h5><br><br>
            <div style="margin:1%;">
                <p>
                    <input class = "w3-radio" name="status" type="radio" checked>  In Anticipation<br>
                    <input class = "w3-radio" name="status" type="radio">  InitalContact<br>
                    <input class = "w3-radio" name="status" type="radio">  Screening Interview<br>
                    <input class = "w3-radio" name="status" type="radio">  Technical Interview<br>
                    <input class = "w3-radio" name="status" type="radio">  Offer Made<br>
                    <input class = "w3-radio" name="status" type="radio">  Employed
                </p>
            </div>
        </div>
        <div class="w3-row w3-section">
            <div class="w3-rest">
                <h5 class="w3-left w3-text-black">Comment: </h5>
                <input class="w3-input w3-border" name="comment" type="text" placeholder="New Comment">
            </div>
        </div>
        <p>
            <button class="w3-button w3-left w3-section w3-red w3-ripple"> Cancel </button></p>
        <p>
            <button class="w3-button w3-right w3-section w3-blue w3-ripple"> Edit </button></p>
    </form>
    <!-- END MAIN -->
</div>

<!-- Pagination -->

<footer id="myFooter">
    <div class="w3-container w3-theme-l1 w3-center" style="width:100%;">
        <p><a>Powered by Pogorelova Maria</a></p>
    </div>
</footer>

</body>
</html>
