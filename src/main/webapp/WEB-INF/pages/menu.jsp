<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header data-bs-theme="dark">
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <div class="container-fluid">

            <a class="navbar-brand" href="${pageContext.request.contextPath}">
                Parking Lot
            </a>

            <button class="navbar-toggler" type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarCollapse"
                    aria-controls="navbarCollapse"
                    aria-expanded="false"
                    aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarCollapse">

                <ul class="navbar-nav me-auto mb-2 mb-md-0">

                    <!-- About -->
                    <li class="nav-item">
                        <a class="nav-link
                           ${pageContext.request.requestURI.substring(
                               pageContext.request.requestURI.lastIndexOf('/')
                           ) eq '/about.jsp' ? ' active' : ''}"
                           aria-current="page"
                           href="${pageContext.request.contextPath}/about.jsp">
                            About
                        </a>
                    </li>

                    <!-- Cars (doar dacă are rol) -->
                    <li class="nav-item">
                        <c:if test="${pageContext.request.isUserInRole('READ_CARS')}">
                            <a class="nav-link
                               ${pageContext.request.requestURI.substring(
                                   pageContext.request.requestURI.lastIndexOf('/')
                               ) eq '/cars.jsp' ? ' active' : ''}"
                               aria-current="page"
                               href="${pageContext.request.contextPath}/Cars">
                                Cars
                            </a>
                        </c:if>
                    </li>

                    <!-- Users (doar dacă are rol) -->
                    <li class="nav-item">
                        <c:if test="${pageContext.request.isUserInRole('READ_USERS')}">
                            <a class="nav-link
                               ${pageContext.request.requestURI.substring(
                                   pageContext.request.requestURI.lastIndexOf('/')
                               ) eq '/users.jsp' ? ' active' : ''}"
                               aria-current="page"
                               href="${pageContext.request.contextPath}/Users">
                                Users
                            </a>
                        </c:if>
                    </li>

                    <!-- Link generic -->
                    <li class="nav-item">
                        <a class="nav-link" href="#">Link</a>
                    </li>

                    <!-- Disabled -->
                    <li class="nav-item">
                        <a class="nav-link disabled" aria-disabled="true">
                            Disabled
                        </a>
                    </li>

                </ul>

                <!-- Login / Logout -->
                <ul class="navbar-nav ms-auto">
                    <c:choose>
                        <c:when test="${pageContext.request.remoteUser == null}">
                            <li class="nav-item">
                                <a class="nav-link"
                                   href="${pageContext.request.contextPath}/Login">
                                    Login
                                </a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item">
                                <a class="nav-link"
                                   href="${pageContext.request.contextPath}/Logout">
                                    Logout
                                </a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>

            </div>
        </div>
    </nav>
</header>
