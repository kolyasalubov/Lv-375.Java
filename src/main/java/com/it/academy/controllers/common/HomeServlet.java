package com.it.academy.controllers.common;

import com.it.academy.common.ControllerUrls;
import com.it.academy.common.ObjContainer;
import com.it.academy.constants.ErrorConstants;
import com.it.academy.constants.PaginationConstants;
import com.it.academy.controllers.RequestValidator;
import com.it.academy.common.ViewUrls;
import com.it.academy.constants.RoomConstants;
import com.it.academy.dto.CollectionDto;
import com.it.academy.dto.RoomDto;
import com.it.academy.service.PaginationService;
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
    private PaginationService<RoomDto> paginationService;
    private RequestValidator requestValidator;

    public HomeServlet() {
        super();
        roomService = ObjContainer.getInstance().getRoomService();
        paginationService =  ObjContainer.getInstance().getPaginationServices().get(PaginationConstants.ROOM_PAGE.toString());
        requestValidator = ObjContainer.getInstance().getRequestValidator();
    }

    /**
     * Shows the list of rooms
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (requestValidator.isValid(request)) {

            CollectionDto<RoomDto> rooms = roomService.getRoomCollectionDto();

            if (rooms == null)
                request.setAttribute(ErrorConstants.ERROR.toString(),  ErrorConstants.NO_ROOMS.toString());
            else {
                String pageOffset = request.getParameter(PaginationConstants.PAGE_OFFSET.toString());
                String page = request.getParameter(PaginationConstants.PAGE.toString());

                rooms = paginationService.updateCollection(rooms, pageOffset, page);
                request.setAttribute(RoomConstants.ROOMS.toString(), rooms);
            }

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
