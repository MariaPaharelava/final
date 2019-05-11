<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page info="WorkWithUser.jsp" language="java"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<title>Work With User</title>
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

<c:set var="pageName" value="WorkWithUser.jsp" scope="session"/>

<!-- Navbar -->
<div class="w3-top">
    <div class="w3-bar w3-black w3-card">
        <form action="Controller" method="get">
            <input name="command" value="go-to-for-user" type="hidden"/>
            <a class="w3-bar-item w3-button w3-theme-l1" href="#">Users</a>
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
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu"
     id="myOverlay"></div>

<!-- Main content: shift it to the right by 250 pixels when the sidebar is visible -->
<div class="w3-main w3-animate-left" style="margin:3.7%;">

    <form action="/action_page.php" class="w3-container w3-card-4 w3-light-grey w3-text-blue"
          style="margin-left: 10%; margin-right: 10%;">
        <h2 class="w3-center">All Users</h2>


        <table class="w3-table-all w3-margin-top" id="myTable">
            <tr>
                <th style="width:5%;"></th>
                <th style="width:30%;">Surname</th>
                <th style="width:20%;">Name</th>
                <th style="width:20%;">Role</th>
                <th style="width:20%;">Login</th>
                <th style="width:5%; ">Delete</th>
            </tr>
            <tr>
                <td>
                    <div class="w3-col" style="width:50px"><i class="w3-xlarge fa fa-user-circle-o"></i></div>
                </td>
                <td>Futterkiste</td>
                <td>Alfreds</td>
                <td>Admin</td>
                <td>qwerty</td>
                <td>
                    <div style="width:50px"><a class="w3-text-red w3-right" href="#"><i
                            class=" w3-xlarge fa fa-user-times"></i></a></div>
                </td>
            </tr>
        </table>


        <div class="w3-row w3-section">
            <div class=" w3-right w3-rest">
                <form action="Controller" method="get">
                    <input name="command" value="go-to-for-user" type="hidden"/>
                    <a href="#" style="margin-right:20%" onclick="document.getElementById('id01').style.display='block'"><i class="w3-xlarge fa fa-user-plus"></i></a>
                </form>
            </div>
        </div>
    </form>
    <!-- END MAIN -->
</div>

<div id="id01" class="w3-modal">
    <div class="w3-main w3-animate-left w3-container ">
        <form class="w3-container w3-card-4 w3-light-grey w3-card-4 w3-animate-zoom w3-modal-content"style="margin-left:20%; margin-right:20%;">
            <div class="w3-section">
                <div class="w3-center"><br>
                    <span onclick="document.getElementById('id01').style.display='none'"
                          class="w3-button w3-xlarge w3-white w3-display-topright" title="Close Modal">x</span>
                </div>
                <label><b>Surname</b></label>
                <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Enter Surname" name="usrname" required>

                <label><b>Name</b></label>
                <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Enter Name" name="usrname" required>

                <label><b>Login</b></label>
                <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Enter Username" name="usrname" required>

                <label><b>Password</b></label>
                <input class="w3-input w3-border" type="password" placeholder="Enter Password" name="psw" required>
                <div class="w3-row w3-section w3-text-black">
                    <h5 class="w3-left">Select role: </h5><br><br>
                    <div style="margin:1%;">
                        <p>
                            <input class = "w3-radio" style="width:20%" name="status" type="radio" checked>  User<br>
                            <input class = "w3-radio" style="width:20%" name="status" type="radio">  HR<br>
                            <input class = "w3-radio" style="width:20%" name="status" type="radio">  Admin
                        </p>
                    </div>
                </div>
                <button class="w3-button w3-center w3-teal w3-section w3-padding" type="submit">OK</button>
                <!-- role -->
            </div>
        </form>
    </div>
</div>
<!-- Pagination -->

<footer id="myFooter">
    <div class="w3-container w3-theme-l1 w3-center w3-display-bottommiddle" style="width:100%;">
        <p><a>Powered by Pogorelova Maria</a></p>
    </div>
</footer>

</body>
</html>
