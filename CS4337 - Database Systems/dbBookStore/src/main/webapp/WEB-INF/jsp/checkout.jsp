<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="https://fonts.googleapis.com/css?family=Lora:400,700|Montserrat:300" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans"/>

<html lang="en">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="css/checkout.css">

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


<div id="error-container" class="error-container" style="display:block">
    <h2>
        <c:if test="${not empty errorMessage}">
            <c:out value="${errorMessage}"/>
        </c:if>
    </h2>
</div>
<script type="text/javascript">
    window.onload = function () {
        setTimeout(function () {
            document.getElementById('error-container').style.display = 'none';
        }, 3000);
        return false;
    };
</script>


<form id="loginForm" action="SubmitOrderServlet" method="get">
    <div class="register-container">
        <div class="tab">
            <!--Begin Form Table-->
            <div class="greeting">
                <h1>Enter Order Information</h1>
            </div>
            <table>
                <tr>
                    <th>
                        <div class="wrapper">
                            <label for="shipaddress"><b>Shipping Address</b></label>
                            <input type="text" minlength="1" maxlength="20" placeholder="Your Address" name="shipaddress"
                                   required>
                        </div>
                    </th>
                    <th>
                        <div class="wrapper">
                            <label for="shipcity"><b>Shipping City</b></label>
                            <input type="text" minlength="1" maxlength="20" x placeholder="Your City" name="shipcity"
                                   required>
                        </div>
                    </th>
                </tr>
                <tr>
                    <th>
                        <div class="wrapper">
                            <label for="shipzip"><b>Shipping Zip Code</b></label>
                            <input type="text" placeholder="Your Zip Code" name="shipzip" required maxlength="5"
                                   minlength="5">
                        </div>
                    </th>
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
                </tr>
                <tr>
                    <th>
                        <div class="wrapper">
                            <label for="ccn"><b>Add Credit Card Number?</b></label>
                            <input type="checkbox" onmousedown="changehidden()">
                        </div>
                    </th>
                </tr>
                <tr id="hiddenrow">
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
                            <input type="text" placeholder="Enter Credit Card Number" name="creditcardnumber"
                                   minlength="0" maxlength="16">
                        </div>
                    </th>
                </tr>
            </table>
        </div>
        <button type="submit" name="next-button" id="next-button">Place Order</button>
    </div>
</form>

<script type="text/javascript" src="js/hidden.js"></script>

</body>
</html>