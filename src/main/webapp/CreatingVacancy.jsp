<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page info="CreateVacancy.jsp" language="java"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local"/>

<!DOCTYPE html>
<html>
<title><fmt:message key="local.title.create.vacancy"/></title>
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

<c:set var="pageName" value="CreatingVacancy.jsp" scope="session"/>

<!-- Navbar -->
<div class="w3-top">
    <div class="w3-bar w3-black w3-card">
        <a class="w3-bar-item w3-button"
           href="${pageContext.servletContext.contextPath}/HrVacancyShow.jsp">
            <fmt:message key="local.button.vacancy"/></a>

        <a class="w3-bar-item w3-button"
           href="${pageContext.servletContext.contextPath}/HrInformationForHr.jsp">
            <fmt:message key="local.button.info"/></a>

        <a class="w3-bar-item w3-button"
           href="${pageContext.servletContext.contextPath}/HrsVacancy.jsp">
            <fmt:message key="local.button.hiring"/></a>

        <a class="w3-bar-item w3-button w3-theme-l1"
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

<!-- FORM -->
<div class="w3-main w3-animate-left" style="margin-top:3.7%;">

    <form class="w3-container w3-card-4 w3-light-grey w3-text-blue" style="margin-left: 30%; margin-right: 30%;"
          action="FontController" method="post">
        <input name="command" value="create-vacancy" type="hidden"/>

        <h2 class="w3-center"><fmt:message key="local.title.create.vacancy"/>
            <i class="fa fa-edit"></i></h2>

        <div class="w3-row w3-section">
            <div class="w3-rest">
                <input class="w3-input w3-border" name="enterVacancyName" type="text"
                       placeholder="<fmt:message key="local.enter.position"/>"
                       required value="${requestScope.enterVacancyName}"
                       minlength="1" maxlength="255">
            </div>
        </div>

        <div class="w3-row w3-section">
            <div class="w3-rest">
                <input class="w3-input w3-border" name="enterVacancyDescription" type="text"
                       placeholder="<fmt:message key="local.enter.description"/>"
                       required value="${requestScope.enterVacancyDescription}"
                       minlength="1" maxlength="255">
            </div>
        </div>

        <p class="w3-center">
            <button class="w3-button w3-blue w3-ripple"><fmt:message key="local.button.ok"/></button>
        </p>

    </form>
    <!-- END MAIN -->
</div>

<!-- IF VACANCY IS EXIST -->
<c:if test="${sessionScope.errorMessage eq 'This vacancy is exist'}">
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
    <c:set value="" var="errorMessage" scope="session"/>
</c:if>

<footer id="myFooter">
    <div class="w3-container w3-theme-l1 w3-center w3-display-bottommiddle" style="width:100%;">
        <p><a><fmt:message key="local.footer"/></a></p>
    </div>
</footer>

</body>
</html>
