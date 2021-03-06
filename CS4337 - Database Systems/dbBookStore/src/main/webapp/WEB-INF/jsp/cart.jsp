<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="https://fonts.googleapis.com/css?family=Lora:400,700|Montserrat:300" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans"/>

<html lang="en">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="css/cart.css">

<body>

<!-- Navigation -->
<c:choose>
    <c:when test="${usertype == 'logged'}">
        <div class="topnav">
            <a class="active" href="index">Home</a>
            <div class="topnav-right">
                <a href="profile">Profile</a>
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

<div class="page-container">
    <div>
        <b class="b-header">Cart</b>
        <div id="cart-container" class="cart-container">
            <table id="cart-table">
                <thead>
                <tr id="cart-header">
                    <td>Isbn</td>
                    <td>Title</td>
                    <td>Price</td>
                    <td>Qty</td>
                    <td>Total</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${cartitems}" var="c">
                    <tr id="regular-row">
                        <td>${c.getIsbn()}</td>
                        <td>${c.getTitle()}</td>
                        <td>$${c.getPrice()}</td>
                        <td>${c.getQty()}</td>
                        <td>$${c.getTotal()}</td>
                        <td><a href="CartRemoveServlet?${c.getIsbn()}&rem">Remove</a></td>
                        <td><a href="CartRemoveServlet?${c.getIsbn()}&inc">Increase</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <b class="b-header2">Account Summary</b>
    <div id="details-container" class="details-container">
        <table id="summary-table" class="summary-table">
            <tr class="identifier">
                <td class="h-identifier">Sub Total:</td>
                <td class="identifier">$${subtotal}</td>
            </tr>
            <tr class="identifier">
                <td class="h-identifier">Tax:</td>
                <td class="identifier">$${tax}</td>
            </tr>
            <tr class="identifier">
                <td class="h-identifier">Grand Total:</td>
                <td class="identifier">$${grandtotal}</td>
            </tr>
        </table>
        <a href="OrderDetailsServlet" name="next-button" id="next-button">Checkout</a>
        <a href="SubmitOrderServlet" name="smart-button" id="smart-button">Quick Buy</a>

    </div>
</div>


</body>
</html>