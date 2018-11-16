<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="https://fonts.googleapis.com/css?family=Lora:400,700|Montserrat:300" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans" />

<html lang="en">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="css/account-creation.css">

<body>

<!-- Navigation -->
<c:choose>
    <c:when test="${usertype == 'logged'}">
        <div class="topnav">
            <a class="active" href="index">Home</a>
            <div class="topnav-right">
                <a href="order">Orders</a>
                <a href="cart">Cart</a>
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


<form id="loginForm" action="AccountCreationServlet" method="get">
    <div class="register-container">
        <div class="tab">
            <!--Begin Form Table-->
            <div class="greeting">
                <h1>Let's Get To Know You</h1>
            </div>
            <table>
                <tr>
                    <th>
                        <div class="wrapper">
                            <label for="fname"><b>First Name</b></label>
                            <input type="text" minlength="1" maxlength="20" placeholder="Enter First Name" name="fname" required>
                        </div>
                    </th>
                    <th>
                        <div class="wrapper">
                            <label for="lname"><b>Last Name</b></label>
                            <input type="text" minlength="1" maxlength="20" x placeholder="Enter Last Name" name="lname" required>
                        </div>
                    </th>
                </tr>
                <tr>
                    <th>
                        <div class="wrapper">
                            <label for="phone"><b>Phone Number</b></label>
                            <input type="text" placeholder="Enter Phone Number" name="phone" required maxlength="10" minlength="10">
                        </div>
                    </th>
                    <th>
                        <div class="wrapper">
                            <label for="email"><b>Email Address</b></label>
                            <input type="email" maxlength="50" minlength="1" placeholder="Enter Email Address" name="email" required>
                        </div>
                    </th>
                </tr>
                <tr>
                    <th>
                        <div class="wrapper">
                            <label for="address"><b>Address</b></label>
                            <input type="text" minlength="1" maxlength="50" placeholder="Enter Address" name="address" required>
                        </div>
                    </th>
                    <th>
                        <div class="wrapper">
                            <label for="city"><b>City</b></label>
                            <input type="text" placeholder="Enter City" name="city" required minlength="1" maxlength="30">
                        </div>
                    </th>
                </tr>
                <tr>
                    <th>
                        <div class="wrapper">
                            <label for="state"><b>State</b></label>
                            <select name="state">
                                <option value="alabama">AL</option>
                                <option value="alaska">AK</option>
                                <option value="arizona">AZ</option>
                                <option value="arkansas">AR</option>
                                <option value="california">CA</option>
                                <option value="colorado">CO</option>
                                <option value="connecticut">CT</option>
                                <option value="delaware">DE</option>
                                <option value="florida">FL</option>
                                <option value="georgia">GA</option>
                                <option value="hawaii">HI</option>
                                <option value="idaho">ID</option>
                                <option value="illinois">IL</option>
                                <option value="indiana">IN</option>
                                <option value="iowa">IA</option>
                                <option value="kansas">KS</option>
                                <option value="kentucky">KY</option>
                                <option value="louisiana">LA</option>
                                <option value="maine">ME</option>
                                <option value="maryland">MD</option>
                                <option value="massachusetts">MA</option>
                                <option value="michigan">MI</option>
                                <option value="minnesota">MN</option>
                                <option value="mississippi">MS</option>
                                <option value="missouri">MO</option>
                                <option value="montana">MT</option>
                                <option value="nebraska">NE</option>
                                <option value="nevada">NV</option>
                                <option value="new hampshire">NH</option>
                                <option value="new jersey">NJ</option>
                                <option value="new mexico">NM</option>
                                <option value="new york">NY</option>
                                <option value="north carolina">NC</option>
                                <option value="north dakota">ND</option>
                                <option value="ohio">OH</option>
                                <option value="oklahoma">OK</option>
                                <option value="oregon">OR</option>
                                <option value="pennsylvania">PA</option>
                                <option value="rhode island">RI</option>
                                <option value="south carolina">SC</option>
                                <option value="south dakota">SD</option>
                                <option value="tennessee">TN</option>
                                <option value="texas">TX</option>
                                <option value="utah">UT</option>
                                <option value="vermont">VT</option>
                                <option value="virginia">VA</option>
                                <option value="washington">WA</option>
                                <option value="west virginia">WV</option>
                                <option value="wisconsin">WI</option>
                                <option value="wyoming">W</option>
                            </select>
                        </div>
                    </th>
                    <th>
                        <div class="wrapper">
                            <label for="zip"><b>Zip</b></label>
                            <input type="text" placeholder="Enter Zip" name="zip" required minlength="5" maxlength="5">
                        </div>
                    </th>
                </tr>
            </table>
            <!--End Form Table-->
        </div>

        <div class="tab">
            <div class="greeting">
                <h1>Almost There!</h1>
            </div>
            <table>
                <tr>
                    <th>
                        <div class="wrapper">
                            <label for="uname"><b>Username</b></label>
                            <input type="text" minlength="1" maxlength="20" placeholder="Enter Username" name="uname" required>
                        </div>
                    </th>
                    <th>
                        <div class="wrapper">
                            <label for="psw"><b>Password</b></label>
                            <input type="password" minlength="1" maxlength="20" placeholder="Enter Password" name="psw" required>
                        </div>
                    </th>
                </tr>
                <tr>
                    <th>
                        <div class="wrapper">
                            <label for="creditcardtype"><b>Credit Card Holder</b></label>
                            <select name="creditcardtype">
                                <option value="amex">Amex</option>
                                <option value="discover">Discover</option>
                                <option value="mc">Master Card</option>
                                <option value="visa">Visa</option>
                            </select>
                        </div>
                    </th>
                    <th>
                        <div class="wrapper">
                            <label for="creditcardnumber"><b>Credit Card Number</b></label>
                            <input type="text" placeholder="Enter Credit Card Number" name="creditcardnumber" required minlength="16" maxlength="16">
                        </div>
                    </th>
                </tr>
            </table>
        </div>


        <div class="bottom-register">
            <button type="button" name="next-button" id="next-button" onclick="nextPrev(1)">Next</button>
            <div class="progress-cirlces" style="text-align:center;margin-top:40px;">
                <span class="step"></span>
                <span class="step"></span>
            </div>
        </div>

    </div>

</form>

<script type="text/javascript" src="js/login-transition.js"></script>

</body>
</html>