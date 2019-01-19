package com.it.academy.controllers;

import com.it.academy.common.ObjContainer;
import com.it.academy.common.ViewUrls;
import com.it.academy.constants.RoomConstants;
import com.it.academy.constants.UserConstants;
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
        if (request.isRequestedSessionIdFromCookie()
                && request.isRequestedSessionIdValid()
                && request.getSession().getAttribute(UserConstants.LOGIN_DTO.toString()) != null) {
            CollectionDto<RoomDto> rooms = roomService.getRoomCollectionDto();
            request.setAttribute(RoomConstants.ROOMS.toString(), rooms);

            System.out.println("home");
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.HOME_JSP.toString())
                    .forward(request, response);
        } else { //TODO change to sendRedirect
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
                    .forward(request, response);
        }
    }


}
