package com.it.academy.controllers.common;

import com.it.academy.common.ControllerUrls;
import com.it.academy.common.ObjContainer;
import com.it.academy.controllers.RequestValidator;
import com.it.academy.common.ViewUrls;
import com.it.academy.constants.RoomConstants;
import com.it.academy.dto.CollectionDto;
import com.it.academy.dto.RoomDto;
import com.it.academy.service.RoomService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * HomeServlet configures home page
 */
@WebServlet({"/home"})  //  HOME_SERVLET
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 4L;
    private RoomService roomService;

    public HomeServlet() {
        super();
        roomService = ObjContainer.getInstance().getRoomService();
    }

    /**
     * Shows the list of rooms
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (RequestValidator.isValid(request)) {
            CollectionDto<RoomDto> rooms = roomService.getRoomCollectionDto();
            request.setAttribute(RoomConstants.ROOMS.toString(), rooms);

            if (rooms == null)
                request.setAttribute("error", "There are no rooms yet!");

            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.HOME_JSP.toString())
                    .forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath()
                    + ControllerUrls.LOGIN_SERVLET);
        }
    }


}
