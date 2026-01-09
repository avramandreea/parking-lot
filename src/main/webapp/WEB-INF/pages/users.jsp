<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Users">

    <h1>Users</h1>

    <c:if test="${pageContext.request.isUserInRole('WRITE_USERS')}">
        <a href="${pageContext.request.contextPath}/AddUser"
           class="btn btn-primary btn-lg mb-3">
            Add User
        </a>
    </c:if>

    <form method="POST"
          action="${pageContext.request.contextPath}/Users">

        <button type="submit"
                class="btn btn-secondary mb-3">
            Invoice
        </button>

        <div class="container text-center">

            <div class="row fw-semibold border-bottom pb-2 mb-3">
                <div class="col-1"></div>
                <div class="col">Username</div>
                <div class="col">Email</div>
            </div>

            <c:forEach var="user" items="${users}">
                <div class="row mb-2">
                    <div class="col-1">


                        <input type="checkbox"
                               name="user_ids"
                               value="${user.id}" />
                    </div>
                    <div class="col">${user.username}</div>
                    <div class="col">${user.email}</div>
                </div>
            </c:forEach>

        </div>
    </form>

</t:pageTemplate>
