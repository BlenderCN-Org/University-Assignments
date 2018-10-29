<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="https://fonts.googleapis.com/css?family=Lora:400,700|Montserrat:300" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans"/>

<html lang="en">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="css/index.css">

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

<div class=header-container>
    <img id="bookstoreheader" src="img/img_bookstoreheader.png" alt="Bookstore Header">
    <div class="shopping-list">
        <table id="shopping-table">
            <tr id="title-row">
                <td>Isbn</td>
                <td>Author</td>
                <td>Title</td>
                <td>Price</td>
                <td>Subject</td>
            </tr>
            <c:forEach items="${indexitems}" var="b">
                <tr id="regular-row">
                    <td>${b.getIsbn()}</td>
                    <td>${b.getAuthor()}</td>
                    <td>${b.getTitle()}</td>
                    <td>$${b.getPrice()}</td>
                    <td>${b.getSubject()}</td>
                    <td>
                        <a href="CartAddServlet?addToCart&${userid}&${b.getIsbn()}">Add
                            To Cart</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

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