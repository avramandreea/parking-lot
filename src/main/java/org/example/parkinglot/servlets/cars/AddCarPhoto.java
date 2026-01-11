package org.example.parkinglot.servlets.cars;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.example.parkinglot.common.CarDto;
import org.example.parkinglot.ejb.CarsBean;

import java.io.IOException;
import java.io.InputStream;

@MultipartConfig
@ServletSecurity(
        value = @HttpConstraint(rolesAllowed = {"WRITE_CARS"})
)
@WebServlet(name = "AddCarPhoto", value = "/AddCarPhoto")
public class AddCarPhoto extends HttpServlet {

    @Inject
    CarsBean carsBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long carId = Long.parseLong(request.getParameter("id"));
        CarDto car = carsBean.findById(carId);

        request.setAttribute("car", car);

        request.getRequestDispatcher("/WEB-INF/pages/cars/addCarPhoto.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long carId = Long.parseLong(request.getParameter("car_id"));
        Part filePart = request.getPart("file");

        String filename = getFileName(filePart);
        String fileType = filePart.getContentType();

        InputStream inputStream = filePart.getInputStream();
        byte[] fileContent = inputStream.readAllBytes();
        inputStream.close();

        carsBean.addPhotoToCar(carId, filename, fileType, fileContent);


        response.sendRedirect(
                request.getContextPath() + "/EditCar?id=" + carId
        );
    }

    private String getFileName(Part part) {
        String header = part.getHeader("content-disposition");
        for (String cd : header.split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1)
                        .trim()
                        .replace("\"", "");
            }
        }
        return null;
    }
}
