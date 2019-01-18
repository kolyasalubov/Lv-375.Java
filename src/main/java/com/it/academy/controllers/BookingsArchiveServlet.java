package com.it.academy.controllers;

import com.it.academy.common.ObjContainer;
import com.it.academy.common.ViewUrls;
import com.it.academy.constants.BookingConstants;
import com.it.academy.constants.UserConstants;
import com.it.academy.dto.BookingRoomDto;
import com.it.academy.dto.CollectionDto;
import com.it.academy.dto.LoginDto;
import com.it.academy.service.BookingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * BookingsServlet configures bookings of current user
 */
@WebServlet({"/bookings-archive"})  //  BOOKINGS_ARCHIVE_SERVLET
public class BookingsArchiveServlet extends HttpServlet {

    private static final long serialVersionUID = 8L;
    private BookingService bookingService;

    public BookingsArchiveServlet() {
        super();
        bookingService = ObjContainer.getInstance().getBookingService();
    }

    /**
     * Shows the bookings of current user
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.isRequestedSessionIdFromCookie()
                && request.isRequestedSessionIdValid()
                && request.getSession().getAttribute(UserConstants.LOGIN_DTO.toString()) != null) {

            HttpSession session = request.getSession();
            LoginDto loginDto = (LoginDto) session.getAttribute(UserConstants.LOGIN_DTO.toString());

            CollectionDto<BookingRoomDto> bookings = bookingService.getPastBookingRoomCollection(loginDto);
            request.setAttribute(BookingConstants.BOOKINGS.toString(), bookings);

            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.BOOKINGS_ARCHIVE_JSP.toString())
                    .forward(request, response);
        } else {
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
                    .forward(request, response);
        }

    }

}
