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

    /**
     * Afișează formularul de creare utilizator
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lista de roluri disponibile (exact ca în laborator)
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

    /**
     * Creează utilizatorul
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String[] selectedGroups = request.getParameterValues("userGroups");

        // Validare minimă
        if (username == null || username.isEmpty()
                || email == null || email.isEmpty()
                || password == null || password.isEmpty()
                || selectedGroups == null || selectedGroups.length == 0) {

            request.setAttribute("errorMessage", "All fields are required.");

            doGet(request, response);
            return;
        }

        // Creează utilizatorul + rolurile
        usersBean.createUser(
                username,
                email,
                password,
                Arrays.asList(selectedGroups)
        );

        // După creare → înapoi la listă
        response.sendRedirect(request.getContextPath() + "/Users");
    }
}
