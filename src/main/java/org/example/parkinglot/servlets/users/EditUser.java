package org.example.parkinglot.servlets.users;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.parkinglot.common.UserDto;
import org.example.parkinglot.ejb.UsersBean;

import java.io.IOException;

@WebServlet(name = "EditUser", value = "/EditUser")
public class EditUser extends HttpServlet {

    @Inject
    private UsersBean usersBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        UserDto user = usersBean.findById(id);

        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/pages/users/edit-user.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        usersBean.updateEmail(id, email);

        if (password != null && !password.isEmpty()) {
            usersBean.changePassword(id, password);
        }

        response.sendRedirect(request.getContextPath() + "/Users");
    }
}
