<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page info="index.jsp" language="java"
         contentType="text/html;" %>

<!DOCTYPE html>
<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <title><fmt:message key="local.title.index"/></title>
    <style>
        body, h1 {
            font-family: "Raleway", sans-serif
        }

        body, html {
            height: 100%
        }

        .bgimg {
            min-height: 100%;
            background: url('pictures/foot-bridge.jpg') center;
            background-size: cover;
        }
    </style>
</head>

<body>

<c:set var="pageName" value="index.jsp" scope="session"/>

<!-- NAVIGATION START-->
<div class="bgimg w3-display-container w3-animate-opacity w3-text-white">
    <div class="w3-top">
        <div class="w3-bar w3-black w3-card">

            <a class="w3-bar-item w3-button"
               onclick="document.getElementById('id01').style.display='block'">
                <fmt:message key="local.create.akk.login"/></a>
            <a class="w3-bar-item w3-button"
               onclick="document.getElementById('id02').style.display='block'">
                <fmt:message key="local.create.akk"/></a>

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
        </div>
    </div>
</div>
<!-- NAVIGATION END -->

<!-- MAIN LABLE -->
<div class="w3-display-middle">
    <h1 class="w3-jumbo w3-animate-top w3-text-white"><fmt:message key="local.title.index"/></h1>
    <hr class="w3-border-grey" style="margin:auto;width:40%">
</div>

<!-- IF USER IS BLOCKED -->
<c:if test="${sessionScope.errorMessage eq 'User is Blocked'}">
    <div id="id03" class="w3-modal w3-display-middle" style="display: block;">
        <div class="w3-main w3-animate-left w3-modal-content w3-container w3-card-4 w3-light-grey"
             style="max-width:400px">

            <div class="w3-center"><br>
                <span onclick="document.getElementById('id03').style.display='none'"
                      class="w3-button w3-xlarge w3-display-topright" title="Close Modal">x</span>
                <br>
            </div>

            <div class="w3-center" style="margin-bottom: 40px; margin-top: 10px">
                <label class="w3-text-center w3-light-grey w3-large">
                    <fmt:message key="local.label.block"/>
                </label>
            </div>
            <a class="w3-button w3-block w3-teal w3-right w3-section w3-padding"
               onclick="document.getElementById('id01').style.display='block';
               document.getElementById('id03').style.display='none'">
                <fmt:message key="local.button.ok"/>
            </a>
        </div>
    </div>
    <c:set value="" var="errorMessage" scope="session"/>
</c:if>

<!-- IF USER IS WRITE ANOTHER PASSWORD -->
<c:if test="${sessionScope.errorMessage eq 'User write wrong password'}">
    <div id="id04" class="w3-modal w3-display-middle" style="display: block;">
        <div class="w3-main w3-animate-left w3-modal-content w3-container w3-card-4 w3-light-grey"
             style="max-width:400px">

            <div class="w3-center"><br>
                <span onclick="document.getElementById('id04').style.display='none'"
                      class="w3-button w3-xlarge w3-display-topright" title="Close Modal">x</span>
                <br>
            </div>

            <div class="w3-center" style="margin-bottom: 40px; margin-top: 10px">
                <label class="w3-text-center w3-light-grey w3-large">
                    <fmt:message key="local.label.wrongpassword"/>
                </label>
            </div>
            <a class="w3-button w3-block w3-teal w3-right w3-section w3-padding"
               onclick="document.getElementById('id01').style.display='block';
               document.getElementById('id04').style.display='none'">
                <fmt:message key="local.button.ok"/>
            </a>
        </div>
    </div>
    <c:set value="" var="errorMessage" scope="session"/>
</c:if>

<!-- IF USER IS ABSENT -->
<c:if test="${sessionScope.errorMessage eq 'User is absent'}">
    <div id="id05" class="w3-modal w3-display-middle" style="display: block;">
        <div class="w3-main w3-animate-left w3-modal-content w3-container w3-card-4 w3-light-grey"
             style="max-width:400px">

            <div class="w3-center"><br>
                <span onclick="document.getElementById('id05').style.display='none'"
                      class="w3-button w3-xlarge w3-display-topright" title="Close Modal">x</span>
                <br>
            </div>

            <div class="w3-center" style="margin-bottom: 40px; margin-top: 10px">
                <label class="w3-text-center w3-light-grey w3-large">
                    <fmt:message key="local.label.absent"/>
                </label>
            </div>
            <a class="w3-button w3-block w3-teal w3-right w3-section w3-padding"
               onclick="document.getElementById('id01').style.display='block';
               document.getElementById('id05').style.display='none'">
                <fmt:message key="local.button.ok"/>
            </a>
        </div>
    </div>
    <c:set value="" var="errorMessage" scope="session"/>
</c:if>

<!-- IF USER IS EXIST -->
<c:if test="${sessionScope.errorMessage eq 'This login is already in use'}">
    <div id="id06" class="w3-modal w3-display-middle" style="display: block;">
        <div class="w3-main w3-animate-left w3-modal-content w3-container w3-card-4 w3-light-grey"
             style="max-width:400px">

            <div class="w3-center"><br>
                <span onclick="document.getElementById('id06').style.display='none'"
                      class="w3-button w3-xlarge w3-display-topright" title="Close Modal">x</span>
                <br>
            </div>

            <div class="w3-center" style="margin-bottom: 40px; margin-top: 10px">
                <label class="w3-text-center w3-light-grey w3-large">
                    <fmt:message key="local.label.exist"/>
                </label>
            </div>
            <a class="w3-button w3-block w3-teal w3-right w3-section w3-padding"
               onclick="document.getElementById('id02').style.display='block';
               document.getElementById('id06').style.display='none'">
                <fmt:message key="local.button.ok"/>
            </a>
        </div>
    </div>
    <c:set value="" var="errorMessage" scope="session"/>
</c:if>

<!-- LOGIN IN BEGIN-->
<div id="id01" class="w3-modal">
    <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">

        <div class="w3-center"><br>
            <span onclick="document.getElementById('id01').style.display='none'"
                  class="w3-button w3-xlarge w3-white w3-display-topright" title="Close Modal">x</span>
        </div>

        <form class="w3-container" action="FontController" method="post">
            <input name="command" value="authorization" type="hidden"/>

            <div class="w3-section">
                <label class="w3-white">
                    <b><fmt:message key="local.create.akk.login"/></b>
                </label>

                <input class="w3-input w3-border w3-margin-bottom" type="text"
                       placeholder="<fmt:message key="local.create.akk.enter.login"/>"
                       name="enterLogin" required value="${requestScope.createAkkEnterLogin}"
                       minlength="1" maxlength="255">

                <label class="w3-white">
                    <b><fmt:message key="local.create.akk.password"/></b>
                </label>

                <input class="w3-input w3-border" type="password"
                       placeholder="<fmt:message key="local.create.akk.enter.password"/>"
                       name="enterPassword" required value="${requestScope.createAkkEnterPassword}"
                       minlength="1" maxlength="255">

                <button class="w3-button w3-block w3-teal w3-right w3-section w3-padding"
                        type="submit"><fmt:message key="local.login.enter.login"/></button>
            </div>
        </form>
    </div>
</div>
<!-- LOGIN IN END-->

<!-- CREATE ACCOUNT BEGIN-->
<div id="id02" class="w3-modal">
    <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">
        <div class="w3-center"><br>
            <span onclick="document.getElementById('id02').style.display='none'"
                  class="w3-button w3-xlarge w3-white w3-display-topright" title="Close Modal">x</span>
        </div>

        <form role="form" class="w3-container" action="FontController" method="post">
            <div class="w3-section">

                <input name="command" value="registration" type="hidden"/>

                <label class="w3-white"><b>
                    <fmt:message key="local.create.akk.surname"/></b>
                </label>

                <input class="w3-input w3-border w3-margin-bottom" type="text"
                       placeholder="<fmt:message key="local.create.akk.enter.surname"/>"
                       name="enterUserSurname" required value="${requestScope.enterUserSurname}"
                       minlength="1" maxlength="255">

                <label class="w3-white">
                    <b><fmt:message key="local.create.akk.name"/></b>
                </label>

                <input class="w3-input w3-border w3-margin-bottom" type="text"
                       placeholder="<fmt:message key="local.create.akk.enter.name"/>"
                       name="enterUserName" required value="${requestScope.enterUserName}"
                       minlength="1" maxlength="255">

                <label class="w3-white"><b><fmt:message key="local.create.akk.login"/></b></label>
                <input class="w3-input w3-border w3-margin-bottom" type="text"
                       placeholder="<fmt:message key="local.create.akk.enter.login"/>"
                       name="enterLogin" required value="${requestScope.enterLogin}"
                       minlength="1" maxlength="255">

                <label class="w3-white"><b>
                    <fmt:message key="local.create.akk.password"/></b>
                </label>

                <input class="w3-input w3-border" type="password"
                       placeholder="<fmt:message key="local.create.akk.enter.password"/>"
                       name="enterPassword" required value="${requestScope.enterPassword}"
                       minlength="1" maxlength="255">

                <button class="w3-button w3-block w3-teal w3-right w3-section w3-padding"
                        type="submit"><fmt:message key="local.button.ok"/></button>
            </div>
        </form>
    </div>
</div>
<!-- CREATE ACCOUNT END-->

<div class="w3-text-white w3-display-bottomleft w3-padding-large">
    <p><a><fmt:message key="local.footer"/></a></p>
</div>

</body>
</html>
