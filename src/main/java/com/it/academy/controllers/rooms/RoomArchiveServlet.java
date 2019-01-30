package com.it.academy.controllers.rooms;

import com.it.academy.common.ControllerUrls;
import com.it.academy.common.ObjContainer;
import com.it.academy.common.ViewUrls;
import com.it.academy.constants.*;
import com.it.academy.controllers.RequestValidator;
import com.it.academy.dto.BookingUserDto;
import com.it.academy.dto.CollectionDto;
import com.it.academy.dto.RoomDto;
import com.it.academy.entity.Booking;
import com.it.academy.service.BookingService;
import com.it.academy.service.PaginationService;
import com.it.academy.service.RoomService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class RoomArchiveServlet configures archive of bookings in particular room
 */
@WebServlet({"/room-archive"})
public class RoomArchiveServlet extends HttpServlet {

    private static final long serialVersionUID = 6L;
    private BookingService bookingService;
    private RoomService roomService;
    private PaginationService<BookingUserDto> paginationService;
    private RequestValidator requestValidator;

    public RoomArchiveServlet() {
        super();
        bookingService = ObjContainer.getInstance().getBookingService();
        roomService = ObjContainer.getInstance().getRoomService();
        paginationService =  ObjContainer.getInstance().getPaginationServices().get(PaginationConstants.BOOKING_USER_PAGE.toString());
        requestValidator = ObjContainer.getInstance().getRequestValidator();
    }

    /**
     * Shows archive of the bookings in particular room
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (requestValidator.isValid(request)) {

            String number = request.getParameter(RoomConstants.NUMBER.toString());
            RoomDto roomDto = new RoomDto();
            roomDto.setNumber(number);
            roomDto = roomService.fillRoomDtoInfo(roomDto);

            CollectionDto<BookingUserDto> bookings = bookingService.getPastBookingUserCollection(roomDto);

            if(bookings == null)
                request.setAttribute(ErrorConstants.ERROR.toString(), ErrorConstants.NO_BOOKINGS.toString());
            else {
                String pageOffset = request.getParameter(PaginationConstants.PAGE_OFFSET.toString());
                String page = request.getParameter(PaginationConstants.PAGE.toString());

                bookings = paginationService.updateCollection(bookings, pageOffset, page);
                request.setAttribute(BookingConstants.BOOKINGS.toString(), bookings);
            }

            request.setAttribute(RoomConstants.ROOM_DTO.toString(), roomDto);
            request.setAttribute(BookingConstants.ARCHIVE.toString(), true);

            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.ROOM_JSP.toString())
                    .forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath()
                    + ControllerUrls.LOGIN_SERVLET);
        }

    }

}
