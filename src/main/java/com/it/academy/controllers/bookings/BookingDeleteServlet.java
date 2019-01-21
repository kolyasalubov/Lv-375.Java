package com.it.academy.controllers.bookings;

import com.it.academy.common.ControllerUrls;
import com.it.academy.common.ObjContainer;
import com.it.academy.common.ViewUrls;
import com.it.academy.constants.BookingConstants;
import com.it.academy.constants.RoomConstants;
import com.it.academy.controllers.RequestValidator;
import com.it.academy.dto.BookingRoomDto;
import com.it.academy.dto.RoomDto;
import com.it.academy.entity.Booking;
import com.it.academy.service.BookingService;
import com.it.academy.service.RoomService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HomeServlet configures home page
 */
@WebServlet({"/booking-delete"})
public class BookingDeleteServlet extends HttpServlet{

    private static final long serialVersionUID = 9L;
    private BookingService bookingService;

    public BookingDeleteServlet() {
        super();
        bookingService = ObjContainer.getInstance().getBookingService();
    }

    /**
     * Shows the bookings in particular room
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (RequestValidator.isValid(request)) {
            String id = request.getParameter(BookingConstants.ID.toString());
            BookingRoomDto bookingRoomDto = new BookingRoomDto();
            bookingRoomDto.setIdBooking(id);

            bookingRoomDto = bookingService.getBookingRoomDto(bookingRoomDto); // to set RoomNumber
            bookingService.deleteRoomBooking(bookingRoomDto);

            String urlToGoBack = request.getParameter(BookingConstants.URL_TO_GO_BACK.toString());
            if(urlToGoBack.contains("room")) {
                request.getSession().setAttribute(RoomConstants.NUMBER.toString(), bookingRoomDto.getRoomNumber());
            }

            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(urlToGoBack)
                    .forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath()
                    + ControllerUrls.LOGIN_SERVLET);
        }
    }
}
