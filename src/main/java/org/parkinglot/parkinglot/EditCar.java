package org.parkinglot.parkinglot;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.parkinglot.common.CarDto;
import org.example.parkinglot.common.UserDto;
import org.example.parkinglot.ejb.CarsBean;
import org.example.parkinglot.ejb.UsersBean;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditCar", value = "/EditCar")
public class EditCar extends HttpServlet {

    @Inject
    CarsBean carsBean;

    @Inject
    UsersBean usersBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<UserDto> users = usersBean.findAllUsers();
        request.setAttribute("users", users);


        Long carId = Long.parseLong(request.getParameter("id"));
        CarDto car = carsBean.findById(carId);
        request.setAttribute("car", car);
        request.getRequestDispatcher("/WEB-INF/pages/editCar.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long carId = Long.parseLong(request.getParameter("car_id"));
        String licensePlate = request.getParameter("license_plate");
        String parkingSpot = request.getParameter("parking_spot");
        Long ownerId = Long.parseLong(request.getParameter("owner_id"));

        carsBean.updateCar(carId, licensePlate, parkingSpot, ownerId);

        response.sendRedirect(request.getContextPath() + "/Cars");
    }
}
