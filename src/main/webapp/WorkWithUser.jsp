<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page info="WorkWithUser.jsp" language="java"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local"/>

<!DOCTYPE html>
<html>
<title><fmt:message key="local.button.users"/></title>
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
        <a class="w3-bar-item w3-button w3-theme-l1"
           href="${pageContext.servletContext.contextPath}/WorkWithUser.jsp">
        <fmt:message key="local.button.users"/></a>
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

<!-- Main content: shift it to the right by 250 pixels when the sidebar is visible -->
<div class="w3-main w3-animate-left" style="margin:3.7%;">

    <h2 class="w3-center"><fmt:message key="local.button.users"/></h2>

    <table class="w3-table-all w3-margin-top" id="myTable">
        <tr>
            <th style="width:5%;"></th>
            <th style="width:25%;"><fmt:message key="local.create.akk.surname"/></th>
            <th style="width:20%;"><fmt:message key="local.create.akk.name"/></th>
            <th style="width:20%;"><fmt:message key="local.create.akk.role"/></th>
            <th style="width:20%;"><fmt:message key="local.create.akk.login"/></th>
            <th style="width:5%; "><fmt:message key="local.button.blocked"/></th>
            <th style="width:5%; "><fmt:message key="local.button.delete"/></th>
        </tr>
        <c:forEach var="user" items="${sessionScope.users}" varStatus="theCount">
            <tr id="${theCount.index}">
                <td>
                    <div class="w3-col" style="width:50px"><i class="w3-xlarge fa fa-user-circle-o"></i></div>
                </td>
                <td>${user.surname}</td>
                <td>${user.name}</td>
                <td>${user.userRole}</td>
                <td>${user.login}</td>
                <td>
                    <form action="FontController" method="get">
                        <input name="command" value="blocked-user" type="hidden"/>
                        <input name="index" type="hidden" value="${theCount.index}"/>
                        <div style="width:50px">
                            <c:if test="${user.blocked eq false}">
                                <button class=" w3-right w3-button w3-wight"><i
                                        class=" w3-xlarge fa fa-toggle-off"></i></button>
                            </c:if>
                            <c:if test="${user.blocked eq true}">
                                <button class=" w3-right w3-button w3-wight"><i
                                        class=" w3-xlarge fa fa-toggle-on"></i></button>
                            </c:if>
                        </div>
                    </form>
                </td>
                <td>
                    <form action="FontController" method="get">
                        <input name="command" value="delete-user" type="hidden"/>
                        <input name="index" type="hidden" value="${theCount.index}"/>
                        <div style="width:50px">
                            <button class="w3-text-red w3-right w3-button w3-wight"><i
                                    class=" w3-xlarge fa fa-user-times"></i></button>
                        </div>
                    </form>
                </td>
            </tr>
        </c:forEach>

    </table>


    <div class="w3-row w3-section">
        <div class=" w3-right w3-rest">
            <a href="#" style="margin-right:20%"
               onclick="document.getElementById('id01').style.display='block'"><i
                    class="w3-xlarge fa fa-user-plus"></i></a>
        </div>
    </div>
    <!-- END MAIN -->
</div>

<div id="id01" class="w3-modal">
    <div class="w3-main w3-animate-left ">
        <form class=" w3-container w3-card-4 w3-light-grey" style="margin-left:20%; margin-right:20%;"
              action="FontController" method="post">
            <input name="command" value="add-user" type="hidden"/>

            <div class="w3-section">
                <div class="w3-center"><br>
                    <span onclick="document.getElementById('id01').style.display='none'" style="margin-right:20%;"
                          class="w3-button w3-xlarge w3-light-grey w3-display-topright" title="Close Modal">x</span>
                </div>
                <label><b><fmt:message key="local.create.akk.surname"/></b></label>
                <input class="w3-input w3-border w3-margin-bottom" type="text"
                       placeholder="<fmt:message key="local.create.akk.enter.surname"/>"
                       name="enterSurname" required value="${requestScope.enterSurname}"
                       minlength="1" maxlength="255">

                <label><b><fmt:message key="local.create.akk.name"/></b></label>
                <input class="w3-input w3-border w3-margin-bottom" type="text"
                       placeholder="<fmt:message key="local.create.akk.enter.name"/>"
                       name="enterName" required value="${requestScope.enterName}"
                       minlength="1" maxlength="255">

                <label><b><fmt:message key="local.create.akk.login"/></b></label>
                <input class="w3-input w3-border w3-margin-bottom" type="text"
                       placeholder="<fmt:message key="local.create.akk.enter.login"/>"
                       name="enterLogin" required value="${requestScope.enterLogin}"
                       minlength="1" maxlength="255">

                <label><b><fmt:message key="local.create.akk.password"/></b></label>
                <input class="w3-input w3-border" type="password"
                       placeholder="<fmt:message key="local.create.akk.enter.password"/>"
                       name="enterPassword" required value="${requestScope.enterPassword}"
                       minlength="1" maxlength="255">

                <div class="w3-row w3-section w3-text-black">
                    <h5 class="w3-left"><fmt:message key="local.label.select.status"/> </h5><br><br>
                    <div style="margin:1%;">
                        <p>
                            <input class="w3-radio" style="width:20%" name="enterStatus" type="radio" checked
                                   value="${"USER"}"> <fmt:message key="local.role.candidate"/><br>
                            <input class="w3-radio" style="width:20%" name="enterStatus" type="radio"
                                   value="${"HR"}"> <fmt:message key="local.role.hr"/><br>
                            <input class="w3-radio" style="width:20%" name="enterStatus" type="radio"
                                   value="${"ADMIN"}"> <fmt:message key="local.role.admin"/>
                        </p>
                    </div>
                </div>
                <button class="w3-button w3-center w3-teal w3-section w3-padding" type="submit"
                        style="width:100%;"><fmt:message key="local.button.ok"/>
                </button>
                <!-- role -->
            </div>
        </form>
    </div>
</div>
<!-- Pagination -->

<footer id="myFooter">
    <div class="w3-container w3-theme-l1 w3-center w3-display-bottommiddle" style="width:100%;">
        <p><a><fmt:message key="local.footer"/></a></p>
    </div>
</footer>

</body>
</html>
