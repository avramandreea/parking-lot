<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Add Car">

    <h1>Add Car</h1>

    <form class="needs-validation" novalidate method="POST"
          action="${pageContext.request.contextPath}/AddCar">

        <div class="mb-3">
            <label for="license_plate" class="form-label">License Plate</label>
            <input type="text" class="form-control" id="license_plate" name="license_plate" required>
            <div class="invalid-feedback">
                License Plate is required.
            </div>
        </div>

        <div class="mb-3">
            <label for="parking_spot" class="form-label">Parking Spot</label>
            <input type="text" class="form-control" id="parking_spot" name="parking_spot" required>
            <div class="invalid-feedback">
                Parking Spot is required.
            </div>
        </div>

        <div class="mb-3">
            <label for="owner_id" class="form-label">Owner</label>
            <select class="form-select" id="owner_id" name="owner_id" required>
                <option value="">Choose...</option>
                <c:forEach var="user" items="${users}">
                    <option value="${user.id}">${user.username}</option>
                </c:forEach>
            </select>
            <div class="invalid-feedback">
                Please select an owner.
            </div>
        </div>

        <button class="btn btn-primary" type="submit">Save</button>

    </form>

    <!-- Bootstrap validation JS -->
    <script>
        (() => {
            'use strict';
            const forms = document.querySelectorAll('.needs-validation');

            Array.from(forms).forEach(form => {
                form.addEventListener('submit', event => {

                    if (!form.checkValidity()) {
                        event.preventDefault();
                        event.stopPropagation();
                    }

                    form.classList.add('was-validated');

                }, false);
            });
        })();
    </script>

</t:pageTemplate>
