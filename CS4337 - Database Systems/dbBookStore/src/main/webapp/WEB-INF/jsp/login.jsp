<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="https://fonts.googleapis.com/css?family=Lora:400,700|Montserrat:300" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans" />

<html lang="en">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="css/login.css">

<body>

<!-- Navigation -->
<c:choose>
    <c:when test="${usertype == 'logged'}">
        <div class="topnav">
            <a class="active" href="index">Home</a>
            <div class="topnav-right">
                <a href="#account">Account</a>
                <a href="#card">Cart</a>
                <a href="LoginAcceptanceServlet?logout-button">Logout</a>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div class="topnav">
            <a class="active" href="index">Home</a>
            <div class="topnav-right">
                <a href="login">Login</a>
                <a href="account-creation">Register</a>
            </div>
        </div>
    </c:otherwise>
</c:choose>

<div id ="error-container" class="error-container" style="display:block">
    <h2>
        <c:if test="${not empty errorMessage}">
            <c:out value="${errorMessage}"/>
        </c:if>
    </h2>
</div>
<script type="text/javascript">
    window.onload = function () {
        setTimeout(function () {
            document.getElementById('error-container').style.display='none';
        }, 3000);
        return false;
    };
</script>

<form id="loginForm" action="LoginAcceptanceServlet" method="get">
    <div class="register-container">
        <div class="login">
            <div class="greeting">
                <h1>Login to the Bookstore</h1>
            </div>
            <div class="table-container">
                <table>
                    <tr>
                        <th>
                            <div class="wrapper">
                                <label for="uname"><b>Username</b></label>
                                <input type="text" placeholder="Enter Username" name="uname" required>
                            </div>
                        </th>
                        <th>
                            <div class="wrapper">
                                <label for="psw"><b>Password</b></label>
                                <input type="password" placeholder="Enter Password" name="psw" required>
                            </div>
                        </th>
                    </tr>
                </table>
            </div>
        </div>

        <div class="bottom-register">
            <button type="submit" name="next-button" id="next-button">Login</button>
        </div>

    </div>
</form>

</body>
</html>