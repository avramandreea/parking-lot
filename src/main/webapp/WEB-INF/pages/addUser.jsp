<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Add User">

    <h1>Add User</h1>

    <form class="needs-validation"
          novalidate
          method="POST"
          action="${pageContext.request.contextPath}/AddUser">

        <!-- Username -->
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="username">Username</label>
                <input type="text"
                       class="form-control"
                       id="username"
                       name="username"
                       required>
                <div class="invalid-feedback">
                    Username is required.
                </div>
            </div>
        </div>

        <!-- Email -->
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="email">Email</label>
                <input type="email"
                       class="form-control"
                       id="email"
                       name="email"
                       required>
                <div class="invalid-feedback">
                    Email is required.
                </div>
            </div>
        </div>

        <!-- Password -->
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="password">Password</label>
                <input type="password"
                       class="form-control"
                       id="password"
                       name="password"
                       required>
                <div class="invalid-feedback">
                    Password is required.
                </div>
            </div>
        </div>

        <!-- Groups -->
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="user-groups">Groups</label>
                <select class="custom-select d-block w-100"
                        id="user-groups"
                        name="userGroups"
                        multiple
                        required>

                    <c:forEach var="user_group" items="${userGroups}">
                        <option value="${user_group}">
                                ${user_group}
                        </option>
                    </c:forEach>

                </select>
            </div>
        </div>

        <hr class="mb-4">

        <button class="btn btn-primary btn-lg btn-block"
                type="submit">
            Save
        </button>

    </form>

</t:pageTemplate>
