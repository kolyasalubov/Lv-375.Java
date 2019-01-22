package com.it.academy.controllers.bookings;

import com.it.academy.common.ControllerUrls;
import com.it.academy.common.ObjContainer;
import com.it.academy.common.ViewUrls;
import com.it.academy.constants.BookingConstants;
import com.it.academy.constants.PaginationConstants;
import com.it.academy.constants.UserConstants;
import com.it.academy.controllers.RequestValidator;
import com.it.academy.dto.BookingRoomDto;
import com.it.academy.dto.BookingUserDto;
import com.it.academy.dto.CollectionDto;
import com.it.academy.dto.LoginDto;
import com.it.academy.service.BookingService;
import com.it.academy.service.PaginationService;

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
    private PaginationService<BookingRoomDto> paginationService;
    private RequestValidator requestValidator;

    public BookingsArchiveServlet() {
        super();
        bookingService = ObjContainer.getInstance().getBookingService();
        paginationService =  ObjContainer.getInstance().getPaginationServices().get(PaginationConstants.BOOKING_ROOM_PAGE.toString());
        requestValidator = ObjContainer.getInstance().getRequestValidator();
    }

    /**
     * Shows the bookings of current user
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (requestValidator.isValid(request)) {

            HttpSession session = request.getSession();
            LoginDto loginDto = (LoginDto) session.getAttribute(UserConstants.LOGIN_DTO.toString());

            CollectionDto<BookingRoomDto> bookings = bookingService.getPastBookingRoomCollection(loginDto);

            if(bookings == null)
                request.setAttribute("error", "There are no bookings!");
            else {
                String pageOffset = request.getParameter(PaginationConstants.PAGE_OFFSET.toString());
                String page = request.getParameter(PaginationConstants.PAGE.toString());

                bookings = paginationService.updateCollection(bookings, pageOffset, page);
                request.setAttribute(BookingConstants.BOOKINGS.toString(), bookings);
            }

            request.setAttribute(BookingConstants.ARCHIVE.toString(), true);
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.BOOKINGS_JSP.toString())
                    .forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath()
                    + ControllerUrls.LOGIN_SERVLET);
        }

    }

}
