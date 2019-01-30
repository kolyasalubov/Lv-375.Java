package com.it.academy.controllers.admin;

import com.it.academy.common.ControllerUrls;
import com.it.academy.common.ObjContainer;
import com.it.academy.common.ViewUrls;
import com.it.academy.constants.*;
import com.it.academy.controllers.RequestValidator;
import com.it.academy.dto.*;
import com.it.academy.service.PaginationService;
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
 * Class AdminRoomsServlet configures admin preview of the list of rooms
 */
@WebServlet({"/admin-rooms"})
public class AdminRoomsServlet extends HttpServlet{
    private static final long serialVersionUID = 14L;
    private RoomService roomService;
    private PaginationService<RoomDto> paginationService;
    private RequestValidator requestValidator;

    public AdminRoomsServlet() {
        super();
        roomService = ObjContainer.getInstance().getRoomService();
        paginationService =  ObjContainer.getInstance().getPaginationServices().get(PaginationConstants.ROOM_PAGE.toString());
        requestValidator = ObjContainer.getInstance().getRequestValidator();
    }

    /**
     * Shows the page with all the rooms only to admins
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (requestValidator.isValid(request)) {
            if(requestValidator.isAdmin(request)) {

                CollectionDto<RoomDto> rooms = roomService.getRoomCollectionDto();
                if (rooms == null)
                    request.setAttribute(ErrorConstants.ERROR.toString(), ErrorConstants.NO_ROOMS.toString());
                else {
                    // read parameters to create a pagination
                    String pageOffset = request.getParameter(PaginationConstants.PAGE_OFFSET.toString());
                    String page = request.getParameter(PaginationConstants.PAGE.toString());

                    rooms = paginationService.updateCollection(rooms, pageOffset, page);
                    request.setAttribute(RoomConstants.ROOMS.toString(), rooms);
                }

                getServletConfig()
                        .getServletContext()
                        .getRequestDispatcher(ViewUrls.ADMIN_ROOMS_JSP.toString())
                        .forward(request, response);
            } else {
                request.setAttribute(ErrorConstants.ERROR.toString(), ErrorConstants.NOT_THE_ADMIN.toString());
                getServletConfig()
                        .getServletContext()
                        .getRequestDispatcher(request.getContextPath()
                                + ControllerUrls.HOME_SERVLET)
                        .forward(request, response);
            }
        } else {
            response.sendRedirect(request.getContextPath()
                    + ControllerUrls.LOGIN_SERVLET);
        }
    }
}
