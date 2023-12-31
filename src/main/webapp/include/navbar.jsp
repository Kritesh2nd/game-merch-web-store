<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Navigation bar</title>
    </head>
    <body>
       <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
	    <div class="container">
	        <a class="navbar-brand" href="user?page=index">Pixel <span>Plus</span></a>
	        <div class="order-lg-last btn-group">
                <a href="user?page=gotoCart" class="btn-cart dropdown-toggle dropdown-toggle-split bor">
          	    <span class="flaticon-shopping-bag"></span>
                    <c:if test="${not empty sessUserData && not empty sessCartSize}">
                        <div class="d-flex justify-content-center align-items-center">
                            <small>${sessCartSize}</small>
                        </div>
                    </c:if>
                </a>
                        
            </div>
	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav ml-auto">
	            <li class="nav-item active bor"><a href="user?page=index" class="nav-link colw">Home</a></li>
                    ${sessFirstName}
                    <c:if test="${not empty sessFirstName}">
                    <li class="nav-item bor flexmid colw">Hello, ${sessFirstName}</li>
                    </c:if>
                    <c:if test="${not empty sessFirstName}">
                    <li class="nav-item bor"><a href="account?page=logout" class="nav-link col">Log Out</a></li>
                    </c:if>
                    <c:if test="${empty sessFirstName}">
                    <li class="nav-item bor"><a href="account?page=gotoSignIn" class="nav-link col">Sign In</a></li>
                    </c:if>
	            
	        </ul>
	      </div>
	    </div>
	  </nav>
    </body>
</html>
