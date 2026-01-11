<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageTemplate pageTitle="Edit User">

    <h1>Edit User</h1>

    <form method="POST"
          action="${pageContext.request.contextPath}/EditUser">

        <input type="hidden" name="id" value="${user.id}" />


        <div class="mb-3">
            <label>Username</label>
            <input type="text"
                   class="form-control"
                   value="${user.username}"
                   readonly />
        </div>

        <div class="mb-3">
            <label>Email</label>
            <input type="email"
                   class="form-control"
                   name="email"
                   value="${user.email}"
                   required />
        </div>

        <div class="mb-3">
            <label>New Password</label>
            <input type="password"
                   class="form-control"
                   name="password" />
        </div>

        <button type="submit"
                class="btn btn-primary">
            Save
        </button>

    </form>

</t:pageTemplate>
