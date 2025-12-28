<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Cars">

    <h1>Cars</h1>

    <form method="POST" action="${pageContext.request.contextPath}/Cars">

        <!-- Add + Delete doar pentru WRITE_CARS -->
        <c:if test="${pageContext.request.isUserInRole('WRITE_CARS')}">
            <a href="${pageContext.request.contextPath}/AddCar"
               class="btn btn-primary btn-lg mb-3">
                Add Car
            </a>

            <button class="btn btn-danger btn-lg mb-3" type="submit">
                Delete Cars
            </button>
        </c:if>

        <div class="container text-center">

            <c:forEach var="car" items="${cars}">
                <div class="row align-items-center mt-2">

                    <!-- Checkbox doar pentru WRITE_CARS -->
                    <c:if test="${pageContext.request.isUserInRole('WRITE_CARS')}">
                        <div class="col">
                            <input type="checkbox"
                                   name="car_ids"
                                   value="${car.id}" />
                        </div>
                    </c:if>

                    <div class="col">
                            ${car.licensePlate}
                    </div>

                    <div class="col">
                            ${car.parkingSpot}
                    </div>

                    <div class="col">
                            ${car.ownerName}
                    </div>

                    <!-- Edit doar pentru WRITE_CARS -->
                    <c:if test="${pageContext.request.isUserInRole('WRITE_CARS')}">
                        <div class="col">
                            <a class="btn btn-secondary"
                               href="${pageContext.request.contextPath}/EditCar?id=${car.id}">
                                Edit Car
                            </a>
                        </div>
                    </c:if>

                </div>
            </c:forEach>

            <h5 class="mt-4">
                Free parking spots: ${numberOfFreeParkingSpots}
            </h5>

        </div>

    </form>

</t:pageTemplate>
