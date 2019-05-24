<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page info="UserVacancyShow.jsp" language="java"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local"/>

<!DOCTYPE html>
<html>
<title><fmt:message key="local.title.vacancies"/></title>
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
<c:set var="pageName" value="UserVacancyShow.jsp" scope="session"/>

<!-- NAVIBAR -->
<div class="w3-top">
    <div class="w3-bar w3-black w3-card">
        <a class="w3-bar-item w3-button w3-theme-l1"
           href="${pageContext.servletContext.contextPath}/UserVacancyShow.jsp">
            <fmt:message key="local.button.vacancy"/></a>

        <a class="w3-bar-item w3-button"
           href="${pageContext.servletContext.contextPath}/UserInformationForUser.jsp">
            <fmt:message key="local.button.info"/></a>

        <a class="w3-bar-item w3-button"
           href="${pageContext.servletContext.contextPath}/UsersVacancy.jsp">
            <fmt:message key="local.button.hiring"/></a>

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

<!-- ALL VACANCIES -->
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
                    <button class="w3-button w3-section w3-blue w3-ripple">
                        <fmt:message key="local.button.sign.up"/>
                    </button>
                </form>
            </div>
        </c:forEach>
    </div>
</div>
<!-- END MAIN -->


<!-- MESSAGE ABOUT ADD VACANCY BEGIN-->
<c:if test="${sessionScope.message eq 'This vacancy is added'}">
    <div id="id01" class="w3-modal w3-display-middle" style="display: block;">
        <div class="w3-main w3-animate-left w3-modal-content w3-container w3-card-4 w3-light-grey"
             style="max-width:400px">

            <div class="w3-center"><br>
                <span onclick="document.getElementById('id01').style.display='none'"
                      class="w3-button w3-xlarge w3-display-topright" title="Close Modal">x</span>
                <br>
            </div>

            <div class="w3-center" style="margin-bottom: 40px; margin-top: 10px">
                <label class="w3-text-center w3-light-grey w3-large">
                    <fmt:message key="local.lable.singup"/>
                </label>
            </div>
            <a class="w3-button w3-block w3-teal w3-right w3-section w3-padding"
               onclick="document.getElementById('id01').style.display='none'">
                <fmt:message key="local.button.ok"/>
            </a>
        </div>
    </div>
    <c:set value="" var="message" scope="session"/>
</c:if>

<!-- MESSAGE ABOUT EXIST VACANCY BEGIN-->
<c:if test="${sessionScope.message eq 'This hiring is exist'}">
    <div id="id01" class="w3-modal w3-display-middle" style="display: block;">
        <div class="w3-main w3-animate-left w3-modal-content w3-container w3-card-4 w3-light-grey"
             style="max-width:400px">

            <div class="w3-center"><br>
                <span onclick="document.getElementById('id01').style.display='none'"
                      class="w3-button w3-xlarge w3-display-topright" title="Close Modal">x</span>
                <br>
            </div>

            <div class="w3-center" style="margin-bottom: 40px; margin-top: 10px">
                <label class="w3-text-center w3-light-grey w3-large">
                    <fmt:message key="local.lable.exist.vacancy"/>
                </label>
            </div>
            <a class="w3-button w3-block w3-teal w3-right w3-section w3-padding"
               onclick="document.getElementById('id01').style.display='none'">
                <fmt:message key="local.button.ok"/>
            </a>
        </div>
    </div>
    <c:set value="" var="message" scope="session"/>
</c:if>


<footer id="myFooter">
    <div class="w3-container w3-theme-l1 w3-center">
        <p><a><fmt:message key="local.footer"/></a></p>
    </div>
</footer>

</body>
</html>
