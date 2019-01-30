package com.it.academy.controllers.bookings;

import com.it.academy.common.ControllerUrls;
import com.it.academy.common.ObjContainer;
import com.it.academy.common.ViewUrls;
import com.it.academy.constants.BookingConstants;
import com.it.academy.constants.ErrorConstants;
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
 * Class BookingCreateServlet configures page of booking creation
 */
@WebServlet({"/booking-create"})
public class BookingCreateServlet extends HttpServlet {

    private static final long serialVersionUID = 10L;
    private BookingService bookingService;
    private RoomService roomService;
    private RequestValidator requestValidator;

    public BookingCreateServlet() {
        super();
        bookingService = ObjContainer.getInstance().getBookingService();
        roomService = ObjContainer.getInstance().getRoomService();
        requestValidator = ObjContainer.getInstance().getRequestValidator();
    }

    /**
     * Shows the form to create booking
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (requestValidator.isValid(request)) {
            String number = request.getParameter(RoomConstants.NUMBER.toString());
            if(number != null){
                request.setAttribute(BookingConstants.ROOM_NUMBER.toString(), number);
            }
            request.setAttribute(BookingConstants.URL_TO_POST.toString(), ControllerUrls.BOOKING_CREATE_SERVLET.toString());

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
     * Reads data from creation form and add booking to DB
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

                    response.sendRedirect(request.getContextPath()
                            + ControllerUrls.HOME_SERVLET.toString());
                } else {
                    request.setAttribute(ErrorConstants.ERROR.toString(), ErrorConstants.INVALID_BOOKING_TO_ADD.toString());
                }
            } else {
                request.setAttribute(ErrorConstants.ERROR.toString(), ErrorConstants.INVALID_TIME.toString());
            }
        } else {
            request.setAttribute(ErrorConstants.ERROR.toString(), ErrorConstants.ROOM_NOT_EXIST.toString());
        }

        // Show Error Validator
        if (request.getAttribute(ErrorConstants.ERROR.toString()) != null) {
            doGet(request, response);
        }
    }
}
