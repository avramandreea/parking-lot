package org.example.parkinglot.servlets.users;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.parkinglot.common.UserDto;
import org.example.parkinglot.ejb.UsersBean;
import org.example.parkinglot.ejb.InvoiceBean;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@DeclareRoles({"READ_USERS", "WRITE_USERS", "INVOICING"})
@ServletSecurity(
        value = @HttpConstraint(rolesAllowed = {"READ_USERS"})
)
@WebServlet(name = "Users", value = "/Users")
public class Users extends HttpServlet {

    @Inject
    private UsersBean usersBean;

    @Inject
    private InvoiceBean invoiceBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<UserDto> users = usersBean.findAllUsers();
        request.setAttribute("users", users);

        if (request.isUserInRole("INVOICING")) {

            if (invoiceBean.getUserIds() != null && !invoiceBean.getUserIds().isEmpty()) {
                Collection<String> invoices =
                        usersBean.findUsernamesByUserIds(
                                invoiceBean.getUserIds()
                        );
                request.setAttribute("invoices", invoices);
            }
        }

        request.getRequestDispatcher("/WEB-INF/pages/users/users.jsp")
                .forward(request, response);
    }

    @RolesAllowed("INVOICING")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String[] ids = request.getParameterValues("user_ids");

        if (ids != null) {
            for (String id : ids) {
                invoiceBean.getUserIds().add(Long.parseLong(id));
            }
        }

        response.sendRedirect(request.getContextPath() + "/Users");
    }
}
