package org.parkinglot.parkinglot;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.parkinglot.ejb.UsersBean;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@ServletSecurity(
        value = @HttpConstraint(rolesAllowed = {"WRITE_USERS"})
)
@WebServlet(name = "AddUser", value = "/AddUser")
public class AddUser extends HttpServlet {

    @Inject
    private UsersBean usersBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String> userGroups = Arrays.asList(
                "READ_CARS",
                "WRITE_CARS",
                "READ_USERS",
                "WRITE_USERS"
        );

        request.setAttribute("userGroups", userGroups);
        request.getRequestDispatcher("/WEB-INF/pages/addUser.jsp")
                .forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String[] selectedGroups = request.getParameterValues("userGroups");

        if (username == null || username.isEmpty()
                || email == null || email.isEmpty()
                || password == null || password.isEmpty()
                || selectedGroups == null || selectedGroups.length == 0) {

            request.setAttribute("errorMessage", "All fields are required.");

            doGet(request, response);
            return;
        }

        usersBean.createUser(
                username,
                email,
                password,
                Arrays.asList(selectedGroups)
        );

        response.sendRedirect(request.getContextPath() + "/Users");
    }
}
