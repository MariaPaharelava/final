<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ltg" uri="localetags" %>
<%@ page info="WorkWithInterview.jsp" language="java"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local"/>
<!DOCTYPE html>
<html>
<title><fmt:message key="local.title.interviews"/></title>
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

    <form class="w3-container w3-card-4 w3-light-grey w3-text-blue" style="margin-left: 10%; margin-right: 10%;">
        <h2 class="w3-center"><fmt:message key="local.title.interviews"/></h2>


        <table class="w3-table-all w3-margin-top" id="myTable">
            <tr>
                <th style="width:5%;"></th>
                <th style="width:12%;"><fmt:message key="local.label.date"/></th>
                <th style="width:18%;"><fmt:message key="local.label.type"/></th>
                <th style="width:60%;"><fmt:message key="local.label.comment"/></th>
                <th style="width:5%; "><fmt:message key="local.button.delete"/></th>
            </tr>
            <c:forEach var="hiringsInterview" items="${sessionScope.hiringsInterview}" varStatus="theCount">
                <tr id="${theCount.index}">
                    <td>
                        <div class="w3-col" style="width:50px"><i class="w3-xlarge fa fa-laptop"></i></div>
                    </td>
                    <td><ltg:time date="${hiringsInterview.interviewDate}"/></td>
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

            <h2 class="w3-center"><fmt:message key="local.label.create.interview"/>
                <i class="fa fa-pencil-square-o"></i></h2>

            <div class="w3-row w3-section w3-text-black">
                <h5 class="w3-left"><fmt:message key="local.label.type"/>: </h5><br><br>
                <div style="margin:1%;">
                    <p>
                        <input name="enterType" class="w3-radio" type="radio"
                               checked value="${"Phone_Interview"}">
                        <fmt:message key="local.radio.interview.phone"/><br>

                        <input name="enterType" class="w3-radio" type="radio"
                               value="${"Face_To_Face_Interview"}">
                        <fmt:message key="local.radio.interview.face"/>
                    </p>
                </div>
            </div>
            <div class="w3-row w3-section">
                <div class="w3-rest">
                    <h5 class="w3-left w3-text-black"><fmt:message key="local.label.result"/>: </h5>
                    <input class="w3-input w3-border" name="enterResult" type="text"
                           value="${requestScope.enterResult}"
                           placeholder="<fmt:message key="local.create.enter.result"/>"
                           minlength="1" maxlength="255">
                </div>
            </div>

            <div class="w3-row w3-section">
                <div class="w3-rest">
                    <h5 class="w3-left w3-text-black"><fmt:message key="local.label.comment"/>: </h5>
                    <input class="w3-input w3-border" name="enterComment" type="text"
                           value="${requestScope.enterComment}"
                           placeholder="<fmt:message key="local.create.enter.comment"/>"
                           minlength="1" maxlength="255">
                </div>
            </div>
            <p>
                <a class="w3-button w3-left w3-section w3-red w3-ripple"
                   onclick="document.getElementById('id01').style.display='none'">
                    <fmt:message key="local.button.cancel"/></a>
                <button class="w3-button w3-right w3-section w3-blue w3-ripple">
                    <fmt:message key="local.button.ok"/></button>
            </p>
        </form>
        <!-- END MAIN -->
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

