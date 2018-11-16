<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="https://fonts.googleapis.com/css?family=Lora:400,700|Montserrat:300" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans"/>

<html lang="en">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="css/orders.css">

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

<div id=header-container>

    <div id="shopping-list">
        <b class="b-header">Orders</b>
        <c:forEach items="${orderitems}" var="o">
            <tr>
                <button class="accordion">Order Number: "${o.getOno()}"</button>
                <div class="panel">
                    <table>
                        <tr>
                            <td>Shipped On: "${o.getShipped().toString()}"</td>
                            <td>Receive By: "${o.getReceived().toString()}"</td>
                            <td>Address: "${o.getshipAddress()}"</td>
                        </tr>
                        <tr>
                            <td>City: "${o.getShipCity()}"</td>
                            <td>State: "${o.getShipState()}"</td>
                            <td>Zip: "${o.getShipZip()}"</td>
                        </tr>
                    </table>
                </div>
            </tr>
        </c:forEach>
    </div>
</div>
<script>
    var acc = document.getElementsByClassName("accordion");
    var i;

    for (i = 0; i < acc.length; i++) {
        acc[i].addEventListener("click", function () {
            /* Toggle between adding and removing the "active" class,
            to highlight the button that controls the panel */
            this.classList.toggle("active");

            /* Toggle between hiding and showing the active panel */
            var panel = this.nextElementSibling;
            if (panel.style.display === "block") {
                panel.style.display = "none";
            } else {
                panel.style.display = "block";
            }
        });
    }
</script>


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

</body>
</html>