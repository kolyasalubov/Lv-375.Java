package com.it.academy.common;

import com.it.academy.constants.PaginationConstants;
import com.it.academy.controllers.RequestValidator;
import com.it.academy.dao.BookingDao;
import com.it.academy.dao.RoomDao;
import com.it.academy.dao.UserDao;
import com.it.academy.dto.*;
import com.it.academy.service.BookingService;
import com.it.academy.service.PaginationService;
import com.it.academy.service.RoomService;
import com.it.academy.service.UserService;

import java.util.HashMap;
import java.util.Map;


public class ObjContainer {

    private static volatile ObjContainer instance = null;

    private UserDao userDao;
    private RoomDao roomDao;
    private BookingDao bookingDao;

    private UserService userService;
    private RoomService roomService;
    private BookingService bookingService;
    private Map<String, PaginationService> paginationServices;

    private DateParser dateParser;
    private RequestValidator requestValidator;

    private ObjContainer(){
        initDaos();
        dateParser = new DateParser();
        initServices();
        requestValidator = new RequestValidator(userService);
        initPagination();
    }

    private void initDaos(){
        userDao = new UserDao();
        roomDao = new RoomDao();
        bookingDao = new BookingDao();
    }

    private void initServices(){
        userService = new UserService(userDao);
        roomService = new RoomService(roomDao);
        bookingService = new BookingService(bookingDao, userDao, roomDao, dateParser);
    }

    private void initPagination(){
        paginationServices = new HashMap<>();
        paginationServices.put(PaginationConstants.ROOM_PAGE.toString(), new PaginationService<RoomDto>());
        paginationServices.put(PaginationConstants.USER_PAGE.toString(), new PaginationService<UserDto>());
        paginationServices.put(PaginationConstants.BOOKING_USER_PAGE.toString(), new PaginationService<BookingUserDto>());
        paginationServices.put(PaginationConstants.BOOKING_ROOM_PAGE.toString(), new PaginationService<BookingRoomDto>());
    }

    public static ObjContainer getInstance() {
        if (instance == null) {
            synchronized (ObjContainer.class) {
                if (instance == null) {
                    instance = new ObjContainer();
                }
            }
        }
        return instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public RoomDao getRoomDao() {
        return roomDao;
    }

    public void setRoomDao(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    public BookingDao getBookingDao() {
        return bookingDao;
    }

    public void setBookingDao(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public RoomService getRoomService() {
        return roomService;
    }

    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    public BookingService getBookingService() {
        return bookingService;
    }

    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public DateParser getDateParser() {
        return dateParser;
    }

    public void setDateParser(DateParser dateParser) {
        this.dateParser = dateParser;
    }

    public RequestValidator getRequestValidator() {
        return requestValidator;
    }

    public void setRequestValidator(RequestValidator requestValidator) {
        this.requestValidator = requestValidator;
    }

    public Map<String, PaginationService> getPaginationServices() {
        return paginationServices;
    }

    public void setPaginationServices(HashMap<String, PaginationService> paginationServices) {
        this.paginationServices = paginationServices;
    }
}
