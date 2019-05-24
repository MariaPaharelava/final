<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page info="HrVacancyShow.jsp" language="java"
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

<c:set var="pageName" value="HrVacancyShow.jsp" scope="session"/>

<!-- Navbar -->
<div class="w3-top">
    <div class="w3-bar w3-black w3-card">
        <a class="w3-bar-item w3-button w3-theme-l1"
           href="${pageContext.servletContext.contextPath}/HrVacancyShow.jsp">
            <fmt:message key="local.button.vacancy"/></a>

        <a class="w3-bar-item w3-button"
           href="${pageContext.servletContext.contextPath}/HrInformationForHr.jsp">
            <fmt:message key="local.button.info"/></a>

        <a class="w3-bar-item w3-button"
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

<div class="w3-main w3-animate-left" style="margin:3.7%">
    <c:forEach var="vacancies" items="${sessionScope.vacancies}" varStatus="theCount">
        <div class="w3-row" id="${theCount.index}">
            <div class="w3-twothird w3-container">
                <h1 class="w3-text-teal">${vacancies.vacancyPosition}</h1>
                <p>${vacancies.vacancyDescrintion}</p>
                <c:if test="${vacancies.userId eq sessionScope.user.ID}">
                    <div class="w3-row">
                        <form class="w3-quarter" action="FontController" method="post">
                            <input name="command" value="edit-vacancy-button" type="hidden"/>
                            <input name="index" type="hidden" value="${theCount.index}"/>
                            <button class="w3-button w3-section w3-blue w3-ripple" type="submit"
                                    style="min-width:80px">
                                <fmt:message key="local.button.edit"/>
                            </button>
                        </form>
                        <form class="w3-quarter" action="FontController" method="post">
                            <input name="command" value="delete-vacancy" type="hidden"/>
                            <input name="index" type="hidden" value="${theCount.index}"/>
                            <button class="w3-button w3-section w3-red w3-ripple" type="submit"
                                    style="min-width:80px">
                                <fmt:message key="local.button.delete"/>
                            </button>
                        </form>
                    </div>
                </c:if>
                <c:if test="${vacancies.userId != sessionScope.user.ID}">
                    <div class="w3-quarter">
                        <button class="w3-button w3-section w3-gray w3-ripple"
                                style="min-width:80px">
                            <fmt:message key="local.button.edit"/>
                        </button>
                    </div>
                    <div class="w3-quarter">
                        <button class="w3-button w3-section w3-gray w3-ripple"
                                style="min-width:80px">
                            <fmt:message key="local.button.delete"/>
                        </button>
                    </div>
                </c:if>
                <br/>
            </div>
        </div>
    </c:forEach>
    <!-- END MAIN -->
</div>

<c:if test="${sessionScope.message eq 'Set vacancy on session'}">
    <div id="id01" class="w3-modal" style="display: block;">
        <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">

            <div class="w3-center"><br>
                <span onclick="document.getElementById('id01').style.display='none'"
                      class="w3-button w3-xlarge w3-white w3-display-topright" title="Close Modal">x</span>
            </div>

            <form class="w3-container" action="FontController" method="post">
                <input name="command" value="edit-vacancy" type="hidden"/>

                <div class="w3-section">


                    <label class="w3-white">
                        <b><fmt:message key="local.enter.description"/></b>
                    </label>

                    <input class="w3-input w3-border" type="text"
                           placeholder="<fmt:message key="local.enter.description"/>"
                           name="enterVacancyDescription" required
                           value="${requestScope.enterVacancyDescription}"
                           minlength="1" maxlength="255">

                    <button class="w3-button w3-block w3-teal w3-right w3-section w3-padding"
                            onclick="document.getElementById('id01').style.display='none'"
                            type="submit"><fmt:message key="local.button.ok"/></button>
                </div>
            </form>
        </div>
    </div>
</c:if>

<footer id="myFooter">
    <div class="w3-container w3-theme-l1 w3-center">
        <p><a><fmt:message key="local.footer"/></a></p>
    </div>
</footer>

</body>
</html>
