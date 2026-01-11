<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Edit car">

    <h1>Edit Car</h1>

    <form method="POST" action="${pageContext.request.contextPath}/EditCar">

        <input type="hidden" name="car_id" value="${car.id}" />

        <div class="mb-3">
            <label class="form-label">License plate</label>
            <input class="form-control"
                   name="license_plate"
                   value="${car.licensePlate}" />
        </div>

        <div class="mb-3">
            <label class="form-label">Parking spot</label>
            <input class="form-control"
                   name="parking_spot"
                   value="${car.parkingSpot}" />
        </div>

        <div class="mb-3">
            <label class="form-label">Owner</label>
            <select class="form-select" name="owner_id">
                <c:forEach var="user" items="${users}">
                    <option value="${user.id}"
                        ${user.username == car.ownerName ? "selected" : ""}>
                            ${user.username}
                    </option>
                </c:forEach>
            </select>
        </div>

        <button class="btn btn-primary" type="submit">
            Save
        </button>

    </form>

    <hr/>

    <h2>Car Photo</h2>

    <c:choose>
        <c:when test="${photo != null}">
            <img src="${pageContext.request.contextPath}/CarPhoto?id=${car.id}"
                 class="img-thumbnail mb-3"
                 style="max-width: 300px;" />
        </c:when>
        <c:otherwise>
            <p>No photo available</p>
        </c:otherwise>
    </c:choose>

    <form class="needs-validation"
          novalidate
          method="POST"
          enctype="multipart/form-data"
          action="${pageContext.request.contextPath}/AddCarPhoto">

        <div class="mb-3">
            License plate: <strong>${car.licensePlate}</strong>
        </div>

        <div class="mb-3">
            <label class="form-label">Photo</label>
            <input type="file"
                   class="form-control"
                   name="file"
                   id="file"
                   required />
            <div class="invalid-feedback">
                Photo is required.
            </div>
        </div>

        <input type="hidden" name="car_id" value="${car.id}" />

        <button class="btn btn-primary btn-lg" type="submit">
            Save
        </button>

    </form>

</t:pageTemplate>
