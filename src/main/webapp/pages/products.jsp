<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <section class="ftco-section">
        <div class="container">
            <div class="row justify-content-center pb-5">
                <div class="col-md-7 heading-section text-center ftco-animate">
                    <span class="subheading">Your Ultimate Merchandise Destination</span>
                    <h2>Game Merchandise</h2>
                </div>
            </div>
            <div class="row">
                
                <!-- for each start from here -->
                <c:forEach var="m" items="${merchListFeatured}">
                <div class="col-md-3 d-flex">
                    <div class="product ftco-animate">
                        <div class="img d-flex align-items-center justify-content-center" style="background-image: url(images/${m.image});">
                            <div class="desc">
                                <p class="meta-prod d-flex">
                                    <c:if test="${not empty userData}">
                                    <a href="#" class="d-flex align-items-center justify-content-center"><span class="flaticon-shopping-bag"></span></a>
                                    <a href="#" class="d-flex align-items-center justify-content-center"><span class="flaticon-heart"></span></a>
                                    </c:if>
                                    <a href="#" class="d-flex align-items-center justify-content-center"><span class="flaticon-visibility"></span></a>
                                </p>
                            </div>
                        </div>
                        <div class="text text-center">
                            <c:if test="${m.latest == 'on'}">
                                <span class="new">New</span>
                            </c:if>
                            <span class="category">${m.type}</span>
                            <h2>${m.title}</h2>
                                <span class="price">NRS ${m.price}</span>
                        </div>
                    </div>
                </div>
                </c:forEach>
                <!-- for each start from here -->
          
            </div>
            <div class="row justify-content-center">
                <div class="col-md-4">
                    <a href="#" class="btn btn-primary d-block">View All Products <span class="fa fa-long-arrow-right"></span></a>
                </div>
            </div>
        </div>
    </section>
    </body>
</html>
