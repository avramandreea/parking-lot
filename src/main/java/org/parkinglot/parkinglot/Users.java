package org.parkinglot.parkinglot;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import org.example.parkinglot.common.UserDto;
import org.example.parkinglot.ejb.UsersBean;
import org.example.parkinglot.ejb.InvoiceBean;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

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

        Collection<String> invoices =
                usersBean.findUsernamesByUserIds(invoiceBean.getUserIds());
        request.setAttribute("invoices", invoices);

        request.getRequestDispatcher("/WEB-INF/pages/users.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String[] ids = request.getParameterValues("user_ids");

        if (ids != null) {
            for (String id : ids) {
                invoiceBean.getUserIds().add(Long.parseLong(id));
            }
        }

        response.sendRedirect(request.getContextPath() + "/Users");
    }
}
