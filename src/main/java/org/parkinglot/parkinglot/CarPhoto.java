package org.parkinglot.parkinglot;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.parkinglot.common.CarPhotoDto;
import org.example.parkinglot.ejb.CarsBean;

import java.io.IOException;

@WebServlet(name = "CarPhoto", value = "/CarPhoto")
public class CarPhoto extends HttpServlet {

    @Inject
    CarsBean carsBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        Long carId = Long.parseLong(request.getParameter("id"));
        CarPhotoDto photo = carsBean.findPhotoByCarId(carId);
        if (photo != null) {
            response.setContentType(photo.getFileType());
            response.setContentLength(photo.getFileContent().length);
            response.getOutputStream().write(photo.getFileContent());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);//Eroare 404
        }
    }

}