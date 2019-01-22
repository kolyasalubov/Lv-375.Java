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
import com.softserve.edu.items.services.CarService;

/**
 * Servlet implementation class DeleteCarServlet
 */
@WebServlet(ControllerUrl.CAR_DELETE_SERVLET)
public class DeleteCarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CarService carService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCarServlet() {
	carService = IocContainer.get().getCarService();
	// super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// if session is valid, look for the isCar parameter and delete car object
	if (SessionManager.checkSession(request)) {
	    Long id = Long.parseLong(request.getParameter("idCar"));
	    // System.out.println("id=" + id);
	    carService.deleteCar(id);
	    getServletConfig().getServletContext().getRequestDispatcher(ViewUrls.USER_CARS_JSP.toString())
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

	doGet(request, response);

    }

}
