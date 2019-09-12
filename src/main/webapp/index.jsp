<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <c:url value="/index.js" var="indexScriptUrl"/>
        <c:url value="/login.js" var="loginScriptUrl"/>
        <c:url value="/logged.js" var="loggedScriptUrl"/>
        <script src="${indexScriptUrl}"></script>   
        <script src="${loginScriptUrl}"></script>   
        <script src="${loggedScriptUrl}"></script>   

        <title>Restaurant Simulator</title>
    </head>
<body>
<div id="login-content" class="content">
    <h1>Login</h1>
    <form id="login-form" onsubmit="return false;">
        <input type="text" name="email">
        <input type="password" name="password">
        <button id="login-button" onclick="onLoginButtonClicked()">Login</button>
    </form>
</div>
<div id="register-content" class="content">
    <h1>Register</h1>
    <form id="register-form" onsubmit="return false;">
            <input type="text" name="name" placeholder="User name">
            <input type="text" name="email" placeholder="E-mail">
            <input type="password" name="password" placeholder="Password">
            <button id="register-button" onclick="onRegisterButtonClicked()">Register</button>
    </form>
</div>
<div id="navbar" class="content hidden">
    <nav>
        <ul class="main-nav">
            <li>
                <p id="nav-restaurant" class="nav-links">Restaurants</p>
            </li>
            <li>
                <p id="nav-food" class="nav-links"> Foods</p>
            </li>
            <li>
                <p id="nav-ordering" class="nav-links"> Orders</p>
            </li>
            <li>
                <p id="nav-money" class="nav-links"> Money</p>
            </li>
            <li>
                <p id="nav-logout" class="nav-links" onclick="onLogoutClicked()"> Logout</p>
            </li>
        </ul>
    </nav>
</div>
<div id="restaurants" class="content hidden">
    <table id="restaurants-table">
        <div>Restaurants</div>
        <tbody>
        </tbody>
    </table>
</div>
<div id="foods" class="content hidden">
    <table id="foods-table">
        <div>Foods</div>
        <tbody>
        </tbody>
    </table>
</div>
<div id="profile-content" class="content hidden">
    <h1>Profile</h1>

</div>
<div id="admin-content" class="content hidden">
    <form id="add-restaurant" onsubmit="return false;">
        <input type="text" placeholder="Restaurant name" name="restaurant-name">
        <button id="addRestaurant" type="submit" onclick="addRestaurant()">Add Restaurant</button>
    </form>
    <form id="add-food" onsubmit="return false;">
        <input type="text" name="food-name">
        <button id="addFood" type="submit" onclick="addFood()">Add Food</button>
    </form>
</div>

</body>
</html>
