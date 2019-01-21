package com.it.academy.controllers.rooms;

import com.it.academy.common.ControllerUrls;
import com.it.academy.common.ObjContainer;
import com.it.academy.common.ViewUrls;
import com.it.academy.constants.BookingConstants;
import com.it.academy.constants.RoomConstants;
import com.it.academy.controllers.RequestValidator;
import com.it.academy.dto.BookingRoomDto;
import com.it.academy.dto.RoomDto;
import com.it.academy.service.BookingService;
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
@WebServlet({"/room-delete"})
public class RoomDeleteServlet extends HttpServlet{

    private static final long serialVersionUID = 17L;
    private RoomService roomService;
    private RequestValidator requestValidator;

    public RoomDeleteServlet() {
        super();
        roomService = ObjContainer.getInstance().getRoomService();
        requestValidator = ObjContainer.getInstance().getRequestValidator();
    }

    /**
     * Shows the bookings in particular room
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (requestValidator.isValid(request)) {
            String id = request.getParameter(RoomConstants.ID.toString());
            RoomDto roomDto = new RoomDto();
            roomDto.setIdRoom(id);

            roomService.deleteRoom(roomDto);

            response.sendRedirect(request.getContextPath()
                    + ControllerUrls.ADMIN_ROOMS_SERVLET);
        } else {
            response.sendRedirect(request.getContextPath()
                    + ControllerUrls.LOGIN_SERVLET);
        }
    }
}
