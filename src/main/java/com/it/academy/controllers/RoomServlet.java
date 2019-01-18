package com.it.academy.controllers;

import com.it.academy.common.ObjContainer;
import com.it.academy.common.ViewUrls;
import com.it.academy.constants.BookingConstants;
import com.it.academy.constants.RoomConstants;
import com.it.academy.constants.UserConstants;
import com.it.academy.dto.BookingUserDto;
import com.it.academy.dto.CollectionDto;
import com.it.academy.dto.RoomDto;
import com.it.academy.service.BookingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * RoomServlet configures bookings in particular room
 */
@WebServlet({"/room"})  //  ROOM_SERVLET
public class RoomServlet extends HttpServlet {

    private static final long serialVersionUID = 5L;
    private BookingService bookingService;

    public RoomServlet() {
        super();
        bookingService = ObjContainer.getInstance().getBookingService();
    }

    /**
     * Shows the bookings in particular room
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.isRequestedSessionIdFromCookie()
                && request.isRequestedSessionIdValid()
                && request.getSession().getAttribute(UserConstants.LOGIN_DTO.toString()) != null) {

            RoomDto roomDto = (RoomDto) request.getAttribute(RoomConstants.ROOM_DTO.toString());

            CollectionDto<BookingUserDto> bookings = bookingService.getFutureBookingUserCollection(roomDto);
            request.setAttribute(BookingConstants.BOOKINGS.toString(), bookings);

            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.ROOM_JSP.toString())
                    .forward(request, response);
        } else {
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
                    .forward(request, response);
        }

    }

}
