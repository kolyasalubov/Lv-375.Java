package com.softserve.edu.items.controllers.cars;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softserve.edu.container.IocContainer;
import com.softserve.edu.items.controllers.ControllerUrl;
import com.softserve.edu.items.controllers.SessionManager;
import com.softserve.edu.items.controllers.ViewUrls;
import com.softserve.edu.items.dto.CarDto;
import com.softserve.edu.items.entity.FieldNames;
import com.softserve.edu.items.services.CarService;

/**
 * Servlet implementation class EditCarServlet
 */
@WebServlet(ControllerUrl.CAR_EDIT_SERVLET)
public class EditCarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CarService carService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCarServlet() {
	carService = IocContainer.get().getCarService();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// If session is valid, look for the idCar instance and get carDto, add it to
	// request
	if (SessionManager.checkSession(request)) {
	    CarDto carDto;
	    if (request.getParameter("idCar") != null) {
		Long id = Long.parseLong(request.getParameter("idCar"));
		carDto = carService.getCarDto(id);
		getServletConfig().getServletContext().setAttribute("carDto", carDto);
	    }

	    getServletConfig().getServletContext().getRequestDispatcher(ViewUrls.CAR_PROFILE_JSP.toString())
		    .forward(request, response);
	} else {
	    getServletConfig().getServletContext().getRequestDispatcher(ViewUrls.LOGIN_JSP.toString()).forward(request,
		    response);
	}
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// If session valid parse all the parameters
	if (SessionManager.checkSession(request)) {
	    CarDto carDto = null;
	    Long id = Long.parseLong(request.getParameter("idCar"));

	    carDto = carService.getCarDto(id);

	    carDto.setId(id);

	    if (request.getParameter(FieldNames.BRAND.toString()) != null
		    && request.getParameter(FieldNames.BRAND.toString()).length() > 0) {
		carDto.setBrand(request.getParameter(FieldNames.BRAND.toString()));
	    }

	    if (request.getParameter(FieldNames.MODEL.toString()) != null
		    && request.getParameter(FieldNames.MODEL.toString()).length() > 0) {
		carDto.setModel(request.getParameter(FieldNames.MODEL.toString()));
	    }

	    if (request.getParameter(FieldNames.GEARBOX.toString()) != null
		    && request.getParameter(FieldNames.GEARBOX.toString()).length() > 0) {
		carDto.setGearBox(request.getParameter(FieldNames.GEARBOX.toString()));
	    }

	    if (request.getParameter(FieldNames.DETAILS.toString()) != null
		    && request.getParameter(FieldNames.DETAILS.toString()).length() > 0) {
		carDto.setDetails(request.getParameter(FieldNames.DETAILS.toString()));
	    }

	    if (request.getParameter(FieldNames.ENGINE_CAPACITY.toString()) != null
		    && request.getParameter(FieldNames.ENGINE_CAPACITY.toString()).length() > 0) {
		carDto.setEngineCapacity(Integer.parseInt(request.getParameter(FieldNames.ENGINE_CAPACITY.toString())));
	    }

	    if (request.getParameter(FieldNames.YEAR.toString()) != null
		    && request.getParameter(FieldNames.YEAR.toString()).length() > 0) {
		carDto.setYear(Integer.parseInt(request.getParameter(FieldNames.YEAR.toString())));
	    }

	    if (request.getParameter(FieldNames.MILEAGE.toString()) != null
		    && request.getParameter(FieldNames.MILEAGE.toString()).length() > 0) {
		carDto.setMileage(Integer.parseInt(request.getParameter(FieldNames.MILEAGE.toString())));
	    }

	    if (request.getParameter(FieldNames.PRICE.toString()) != null
		    && request.getParameter(FieldNames.PRICE.toString()).length() > 0) {
		carDto.setPrice(Integer.parseInt(request.getParameter(FieldNames.PRICE.toString())));
	    }

	    // Create DTO from parsed parameters and send it
	    carService.setCarDto(carDto, carDto.getIdUser());

	    getServletConfig().getServletContext().setAttribute("carDto", carDto);
	    getServletConfig().getServletContext().getRequestDispatcher(ViewUrls.CAR_PROFILE_JSP.toString())
		    .forward(request, response);

	} else {
	    getServletConfig().getServletContext().getRequestDispatcher(ViewUrls.LOGIN_JSP.toString()).forward(request,
		    response);
	}
    }

}
