package com.it.academy.controllers.bookings;

import com.it.academy.common.ControllerUrls;
import com.it.academy.common.ObjContainer;
import com.it.academy.common.ViewUrls;
import com.it.academy.constants.BookingConstants;
import com.it.academy.constants.RoomConstants;
import com.it.academy.constants.UserConstants;
import com.it.academy.controllers.RequestValidator;
import com.it.academy.dto.*;
import com.it.academy.entity.Room;
import com.it.academy.service.BookingService;
import com.it.academy.service.RoomService;
import com.it.academy.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HomeServlet configures home page
 */
@WebServlet({"/booking-create"})  //  HOME_SERVLET
public class CreateBookingServlet extends HttpServlet {

    private static final long serialVersionUID = 10L;
    private BookingService bookingService;
    private RoomService roomService;
    private UserService userService;

    public CreateBookingServlet() {
        super();
        bookingService = ObjContainer.getInstance().getBookingService();
        roomService = ObjContainer.getInstance().getRoomService();
        userService = ObjContainer.getInstance().getUserService();
    }

    /**
     * Shows the bookings in particular room
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (RequestValidator.isValid(request)) {
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.BOOKING_PROFILE_JSP.toString())
                    .forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath()
                    + ControllerUrls.LOGIN_SERVLET);
        }
    }


    /**
     * Shows the bookings in particular room
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomNumber = request.getParameter(BookingConstants.ROOM_NUMBER.toString());
        String startDate = request.getParameter(BookingConstants.START_DATE.toString());
        String endDate = request.getParameter(BookingConstants.END_DATE.toString());
        String purpose = request.getParameter(BookingConstants.PURPOSE.toString());

        BookingRoomDto bookingRoomDto = new BookingRoomDto();
        bookingRoomDto.setRoomNumber(roomNumber);
        bookingRoomDto.setStartDate(startDate);
        bookingRoomDto.setEndDate(endDate);
        bookingRoomDto.setPurpose(purpose);

        LoginDto loginDto = (LoginDto) request.getSession().getAttribute(UserConstants.LOGIN_DTO.toString());
        RoomDto roomDto = new RoomDto();
        roomDto.setNumber(roomNumber);

        if(roomService.isExist(roomDto)) {
            if (bookingService.isFreeTime(bookingRoomDto, loginDto)) {
                if (bookingService.createRoomBooking(bookingRoomDto, loginDto)) {

                    // TODO go back to page from where we came from
                    response.sendRedirect(request.getContextPath()
                            + ControllerUrls.HOME_SERVLET.toString());
                } else {
                    request.setAttribute("error", "We can not add your booking!");
                }
            } else {
                request.setAttribute("error", "This time is not available!");
            }
        } else {
            request.setAttribute("error", "The room does not exist!");
        }

        // Show Error Validator
        if (request.getAttribute("error") != null) {
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.BOOKING_PROFILE_JSP.toString())
                    .forward(request, response);
        }
    }
}
