package com.it.academy.controllers.rooms;

import com.it.academy.common.ControllerUrls;
import com.it.academy.common.ObjContainer;
import com.it.academy.common.ViewUrls;
import com.it.academy.constants.BookingConstants;
import com.it.academy.constants.RoomConstants;
import com.it.academy.constants.UserConstants;
import com.it.academy.controllers.RequestValidator;
import com.it.academy.dto.BookingUserDto;
import com.it.academy.dto.CollectionDto;
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
 * RoomServlet configures archive of bookings in particular room
 */
@WebServlet({"/room-archive"})  //  ROOM_ARCHIVE_SERVLET
public class RoomArchiveServlet extends HttpServlet {

    private static final long serialVersionUID = 6L;
    private BookingService bookingService;
    private RoomService roomService;

    public RoomArchiveServlet() {
        super();
        bookingService = ObjContainer.getInstance().getBookingService();
        roomService = ObjContainer.getInstance().getRoomService();
    }

    /**
     * Shows archive of the bookings in particular room
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (RequestValidator.isValid(request)) {

            String number = request.getParameter(RoomConstants.NUMBER.toString());
            RoomDto roomDto = new RoomDto();
            roomDto.setNumber(number);
            roomDto = roomService.fillRoomDtoInfo(roomDto);

            CollectionDto<BookingUserDto> bookings = bookingService.getPastBookingUserCollection(roomDto);

            request.setAttribute(BookingConstants.BOOKINGS.toString(), bookings);
            request.setAttribute(RoomConstants.ROOM_DTO.toString(), roomDto);
            request.setAttribute(BookingConstants.ARCHIVE.toString(), true);

            if(bookings == null)
                request.setAttribute("error", "There are no bookings!");
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
