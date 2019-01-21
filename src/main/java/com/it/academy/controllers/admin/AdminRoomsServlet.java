package com.it.academy.controllers.admin;

import com.it.academy.common.ControllerUrls;
import com.it.academy.common.ObjContainer;
import com.it.academy.common.ViewUrls;
import com.it.academy.constants.BookingConstants;
import com.it.academy.constants.RoomConstants;
import com.it.academy.constants.UserConstants;
import com.it.academy.controllers.RequestValidator;
import com.it.academy.dto.*;
import com.it.academy.service.RoomService;
import com.it.academy.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * RoomServlet configures archive of bookings in particular room
 */
@WebServlet({"/admin-rooms"})
public class AdminRoomsServlet extends HttpServlet{
    private static final long serialVersionUID = 14L;
    private RoomService roomService;
    private RequestValidator requestValidator;

    public AdminRoomsServlet() {
        super();
        roomService = ObjContainer.getInstance().getRoomService();
        requestValidator = ObjContainer.getInstance().getRequestValidator();
    }

    /**
     * Shows the bookings in particular room
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (requestValidator.isValid(request)) {
            if(requestValidator.isAdmin(request)) {

                CollectionDto<RoomDto> rooms = roomService.getRoomCollectionDto();
                request.setAttribute(RoomConstants.ROOMS.toString(), rooms);

                if (rooms == null)
                    request.setAttribute("error", "There are no rooms yet!");

                getServletConfig()
                        .getServletContext()
                        .getRequestDispatcher(ViewUrls.ADMIN_ROOMS_JSP.toString())
                        .forward(request, response);
            } else {
                request.setAttribute("error", "You are not the admin!");
                getServletConfig()
                        .getServletContext()
                        .getRequestDispatcher(ViewUrls.HOME_JSP.toString())
                        .forward(request, response);
            }
        } else {
            response.sendRedirect(request.getContextPath()
                    + ControllerUrls.LOGIN_SERVLET);
        }
    }
}
