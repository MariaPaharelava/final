<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page info="WorkWithInterview.jsp" language="java"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<title>Work with interview</title>
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

<c:set var="pageName" value="WorkWithInterview.jsp" scope="session"/>

<!-- Navbar -->
<div class="w3-top">
    <div class="w3-bar w3-black w3-card">
            <a class="w3-bar-item w3-button"
               href="${pageContext.servletContext.contextPath}/HrVacancyShow.jsp">Vacancy</a>
            <a class="w3-bar-item w3-button" href="${pageContext.servletContext.contextPath}/HrInformationForHr.jsp">My
                Info</a>
            <a class="w3-bar-item w3-button w3-theme-l1"
               href="${pageContext.servletContext.contextPath}/HrsVacancy.jsp">Hiring</a>
            <a class="w3-bar-item w3-button" href="${pageContext.servletContext.contextPath}/CreatingVacancy.jsp">Add
                Vacancy</a>
            <div class="w3-dropdown-hover w3-hide-small w3-right">
                <button class="w3-button" title="More">Language <i class="fa fa-caret-down"></i></button>
                <div class="w3-dropdown-content w3-bar-block w3-card-4">
                    <a class="w3-bar-item w3-button" onclick="">RU</a>
                    <a class="w3-bar-item w3-button" onclick="">EN</a>
                </div>
            </div>
            <a class="w3-bar-item w3-button w3-right"
               href="${pageContext.servletContext.contextPath}/index.jsp" onclick="${sessionScope.clear()}">Logout</a>
    </div>
</div>

<!-- Main content: shift it to the right by 250 pixels when the sidebar is visible -->
<div class="w3-main w3-animate-left" style="margin:3.7%;">

    <form class="w3-container w3-card-4 w3-light-grey w3-text-blue" style="margin-left: 10%; margin-right: 10%;">
        <h2 class="w3-center">Interviews</h2>


        <table class="w3-table-all w3-margin-top" id="myTable">
            <tr>
                <th style="width:5%;"></th>
                <th style="width:12%;">Date</th>
                <th style="width:18%;">Type</th>
                <th style="width:60%;">Comment</th>
                <th style="width:5%; ">Delete</th>
            </tr>
            <c:forEach var="hiringsInterview" items="${sessionScope.hiringsInterview}" varStatus="theCount">
                <tr id="${theCount.index}">
                    <td>
                        <div class="w3-col" style="width:50px"><i class="w3-xlarge fa fa-laptop"></i></div>
                    </td>
                    <td>${hiringsInterview.interviewDate}</td>
                    <td>${hiringsInterview.interviewType}</td>
                    <td>${hiringsInterview.comment}</td>
                    <td>
                        <div style="width:50px">
                            <form action="FontController" method="get">
                                <input name="command" value="delete-interview" type="hidden"/>
                                <input name="index" type="hidden" value="${theCount.index}"/>
                                <button class="w3-text-red w3-right w3-button w3-wight" style="margin-right:20%"
                                        type="submit"><i
                                        class=" w3-xlarge fa fa-trash"></i>
                                </button>
                            </form>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>


        <div class="w3-row w3-section">
            <div class=" w3-right w3-rest">
                <a href="#" style="margin-right:20%"
                   onclick="document.getElementById('id01').style.display='block'"><i class="w3-xlarge fa fa-plus"></i></a>

            </div>
        </div>
    </form>
    <!-- END MAIN -->
</div>

<div id="id01" class="w3-modal">
    <div class="w3-main w3-animate-left">

        <form action="FontController" class="w3-container w3-card-4 w3-light-grey w3-text-blue"
              style="margin-left: 30%; margin-right: 30%;">
            <div class="w3-center"><br>
                <span onclick="document.getElementById('id01').style.display='none'" style="margin-right: 30%;"
                      class="w3-button w3-xlarge w3-light-grey w3-display-topright" title="Close Modal">x</span>
            </div>
            <input name="command" value="add-interview" type="hidden"/>

            <div class="w3-center"><br>
                <span onclick="document.getElementById('id01').style.display='none'" style="margin-right: 30%;"
                      class="w3-button w3-xlarge w3-light-grey w3-display-topright" title="Close Modal">x</span>
            </div>

            <h2 class="w3-center">Create Interview <i class="fa fa-pencil-square-o"></i></h2>

            <div class="w3-row w3-section w3-text-black">
                <h5 class="w3-left">Type: </h5><br><br>
                <div style="margin:1%;">
                    <p>
                        <input name="enterType" class="w3-radio" type="radio"
                               checked value="${"Phone_Interview"}"> Phone Interview<br>
                        <input name="enterType" class="w3-radio" type="radio"
                               value="${"Face_To_Face_Interview"}"> Face-To-Face Interview
                    </p>
                </div>
            </div>
            <div class="w3-row w3-section">
                <div class="w3-rest">
                    <h5 class="w3-left w3-text-black">Result: </h5>
                    <input class="w3-input w3-border" name="enterResult" type="text"
                           value="${requestScope.enterResult}" placeholder="Enter result">
                </div>
            </div>

            <div class="w3-row w3-section">
                <div class="w3-rest">
                    <h5 class="w3-left w3-text-black">Comment: </h5>
                    <input class="w3-input w3-border" name="enterComment" type="text"
                           value="${requestScope.enterComment}" placeholder="Enter comment">
                </div>
            </div>
            <p>
                <a class="w3-button w3-left w3-section w3-red w3-ripple"
                   onclick="document.getElementById('id01').style.display='none'"> Cancel </a>
                <button class="w3-button w3-right w3-section w3-blue w3-ripple"> Edit</button>
            </p>
        </form>
        <!-- END MAIN -->
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

