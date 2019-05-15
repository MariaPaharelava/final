<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page info="index.jsp" language="java"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <title>Time to find a job</title>
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

    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var="loc"/>

    <fmt:message bundle="${loc}" key="local.login" var="login"/>
    <fmt:message bundle="${loc}" key="local.create.akk" var="createAkk"/>
    <fmt:message bundle="${loc}" key="local.button.language" var="language"/>

    <fmt:message bundle="${loc}" key="local.login.login" var="loginLogin"/>
    <fmt:message bundle="${loc}" key="local.login.password" var="loginPassword"/>
    <fmt:message bundle="${loc}" key="local.login.enter.login" var="loginEnterLogin"/>

    <fmt:message bundle="${loc}" key="local.create.akk.surname" var="createAkkSurname"/>
    <fmt:message bundle="${loc}" key="local.create.akk.enter.surname" var="createAkkEnterSurname"/>
    <fmt:message bundle="${loc}" key="local.create.akk.enter.name" var="createAkkEnterName"/>
    <fmt:message bundle="${loc}" key="local.create.akk.name" var="createAkkName"/>
    <fmt:message bundle="${loc}" key="local.create.akk.enter.login" var="createAkkEnterLogin"/>
    <fmt:message bundle="${loc}" key="local.create.akk.login" var="createAkkLogin"/>
    <fmt:message bundle="${loc}" key="local.create.akk.enter.password" var="createAkkEnterPassword"/>
    <fmt:message bundle="${loc}" key="local.create.akk.password" var="createAkkPassword"/>

    <fmt:message bundle="${loc}" key="local.lable" var="lable"/>
    <fmt:message bundle="${loc}" key="local.button.out" var="out"/>

</head>
<body>

<div class="bgimg w3-display-container w3-animate-opacity w3-text-white">

    <div class="w3-display-middle">
        <h1 class="w3-jumbo w3-animate-top">${lable}</h1>
        <hr class="w3-border-grey" style="margin:auto;width:40%">
    </div>

    <!-- NAVIGATION START-->
    <div class="w3-top">
        <div class="w3-bar w3-black w3-card">
            <a class="w3-bar-item w3-button"
               onclick="document.getElementById('id01').style.display='block'">${login}</a>
            <a class="w3-bar-item w3-button"
               onclick="document.getElementById('id02').style.display='block'">${createAkk}</a>
            <div class="w3-dropdown-hover w3-hide-small w3-right">
                <button class="w3-button" title="More">${language} <i class="fa fa-caret-down"></i></button>
                <form action="FontController" method="post">
                    <div class="w3-dropdown-content w3-bar-block w3-card-4">
                        <input name="command" value="change-local" type="hidden">

                        <a class="w3-bar-item w3-button" name="local">RU</a>
                        <a class="w3-bar-item w3-button" name="local">EN</a>
                    </div>
                </form>

            </div>
        </div>
    </div>
    <!-- NAVIGATION END -->

</div>

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
                <label class="w3-white"><b>${loginLogin}</b></label>
                <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="${createAkkEnterLogin}"
                       name="enterLogin" required value="${requestScope.createAkkEnterLogin}">
                <label class="w3-white"><b>${loginPassword}</b></label>
                <input class="w3-input w3-border" type="password" placeholder="${createAkkEnterPassword}"
                       name="enterPassword" required value="${requestScope.createAkkEnterPassword}">

                <button class="w3-button w3-block w3-teal w3-right w3-section w3-padding"
                        type="submit">${loginEnterLogin}</button>
            </div>
        </form>

    </div>
</div>
<!-- LOGIN IN END-->

<div id="id02" class="w3-modal">
    <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">
        <div class="w3-center"><br>
            <span onclick="document.getElementById('id02').style.display='none'"
                  class="w3-button w3-xlarge w3-white w3-display-topright" title="Close Modal">x</span>
        </div>

        <form role="form" class="w3-container" action="FontController" method="post">
            <div class="w3-section">

                <input name="command" value="registration" type="hidden"/>

                <label class="w3-white"><b>${createAkkSurname}</b></label>
                <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="${createAkkEnterSurname}"
                       name="enterUserSurname" required value="${requestScope.enterUserSurname}">

                <label class="w3-white"><b>${createAkkName}</b></label>
                <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="${createAkkEnterName}"
                       name="enterUserName" required value="${requestScope.enterUserName}">

                <label class="w3-white"><b>${createAkkLogin}</b></label>
                <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="${createAkkEnterLogin}"
                       name="enterLogin" required value="${requestScope.enterLogin}">

                <label class="w3-white"><b>${createAkkPassword}</b></label>
                <input class="w3-input w3-border" type="password" placeholder="${createAkkEnterPassword}"
                       name="enterPassword" required value="${requestScope.enterPassword}">


                <button class="w3-button w3-block w3-teal w3-right w3-section w3-padding" type="submit">OK</button>
            </div>
        </form>
    </div>
</div>

<div class="w3-text-white w3-display-bottomleft w3-padding-large">
    Powered by Pogorelova Maria
</div>

</body>
</html>
