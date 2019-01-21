package com.it.academy.service;

import com.it.academy.common.ObjContainer;
import com.it.academy.dao.BookingDao;
import com.it.academy.dao.RoomDao;
import com.it.academy.dao.UserDao;
import com.it.academy.dto.*;
import com.it.academy.entity.Booking;
import com.it.academy.entity.Room;
import com.it.academy.entity.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BookingService {

    private BookingDao bookingDao;
    private UserDao userDao;
    private RoomDao roomDao;
    private DateParser dateParser;

    public BookingService() {
        bookingDao = ObjContainer.getInstance().getBookingDao();
        userDao = ObjContainer.getInstance().getUserDao();
        roomDao = ObjContainer.getInstance().getRoomDao();
        dateParser = ObjContainer.getInstance().getDateParser();
    }

    public BookingService(BookingDao bookingDao, UserDao userDao, RoomDao roomDao, DateParser dateParser) {
        this.bookingDao = bookingDao;
        this.userDao = userDao;
        this.roomDao = roomDao;
        this.dateParser = dateParser;
    }

    private Booking dtoToBooking(BookingDto bookingDto, LoginDto loginDto) {
        Booking booking = new Booking();
        if (bookingDto.getIdBooking() != null)
            booking.setId(Long.parseLong(bookingDto.getIdBooking()));

        booking.setStartDate(dateParser.parseToDb(bookingDto.getStartDate()));
        booking.setEndDate(dateParser.parseToDb(bookingDto.getEndDate()));
        booking.setPurpose(bookingDto.getPurpose());

        List<User> users = userDao.getByFieldName("email", loginDto.getEmail());
        booking.setUserId(users.get(0).getId());
        return booking;
    }

    private Booking roomDtoToBooking(BookingRoomDto bookingRoomDto, LoginDto loginDto) {
        Booking booking = dtoToBooking(bookingRoomDto, loginDto);
        List<Room> rooms = roomDao.getByFieldName("number", bookingRoomDto.getRoomNumber());
        booking.setRoomId(rooms.get(0).getId());
        return booking;
    }


    private BookingDto bookingToBookingDto(Booking booking) {
        BookingDto dto = new BookingDto();

        Map<String, String> startDateTime = dateParser.parseToDisplay(booking.getStartDate());
        Map<String, String> endDateTime = dateParser.parseToDisplay(booking.getEndDate());

        dto.setStartDate(startDateTime.get("date"));
        dto.setStartTime(startDateTime.get("time"));
        dto.setEndDate(endDateTime.get("date"));
        dto.setEndTime(endDateTime.get("time"));

        dto.setPurpose(booking.getPurpose());
        dto.setIdBooking(String.valueOf(booking.getId()));
        dto.setUserEmail(userDao.getById(booking.getUserId()).getEmail());

        return dto;
    }

    private BookingUserDto bookingToBookingUserDto(Booking booking) {
        BookingUserDto dto = new BookingUserDto(bookingToBookingDto(booking));

        User user = userDao.getById(booking.getUserId());
        dto.setUserFirstName(user.getFirstName());
        dto.setUserLastName(user.getLastName());

        return dto;
    }

    private BookingRoomDto bookingToBookingRoomDto(Booking booking) {
        BookingRoomDto dto = new BookingRoomDto(bookingToBookingDto(booking));

        Room room = roomDao.getById(booking.getRoomId());
        dto.setRoomNumber(String.valueOf(room.getNumber()));

        return dto;
    }


//    public boolean createBooking(BookingDto bookingDto, RoomDto roomDto, LoginDto loginDto) {
//        Booking booking = dtoToBooking(bookingDto, loginDto);
//        List<Room> rooms = roomDao.getByFieldName("number", String.valueOf(roomDto.getNumber()));
//        booking.setRoomId(rooms.get(0).getId());
//        return saveBookingToDB(booking);
//    }

    public boolean createRoomBooking(BookingRoomDto bookingRoomDto, LoginDto loginDto) {
        Booking booking = roomDtoToBooking(bookingRoomDto, loginDto);
        return saveBookingToDB(booking);
    }

    public boolean isFreeTime(BookingRoomDto bookingRoomDto, LoginDto loginDto) {
        Booking booking = roomDtoToBooking(bookingRoomDto, loginDto);
        return !bookingDao.isExist(booking);
    }

    public boolean isFreeTimeExceptFromCurrent(BookingRoomDto bookingRoomDto, LoginDto loginDto) {
        Booking booking = roomDtoToBooking(bookingRoomDto, loginDto);
        return !bookingDao.isExistExceptFromCurrent(booking);
    }

    private boolean saveBookingToDB(Booking booking) {
        boolean result = true;
        try {
            bookingDao.insert(booking);
        } catch (Exception e) {
            System.out.println("RuntimeException: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean updateRoomBooking(BookingRoomDto bookingRoomDto, LoginDto loginDto) {
        boolean result = true;
        Booking booking = roomDtoToBooking(bookingRoomDto, loginDto);
        try {
            bookingDao.updateEntityById(booking);
        } catch (Exception e) {
            System.out.println("RuntimeException: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean deleteRoomBooking(BookingRoomDto bookingRoomDto) {
        boolean result = true;
        try {
            bookingDao.deleteById(Long.parseLong(bookingRoomDto.getIdBooking()));
        } catch (Exception e) {
            System.out.println("RuntimeException: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public BookingRoomDto getBookingRoomDto(BookingRoomDto bookingRoomDto){
        BookingRoomDto result = null;
        try {
            Booking booking = bookingDao.getById(Long.parseLong(bookingRoomDto.getIdBooking()));
            result = bookingToBookingRoomDto(booking);
        } catch (Exception e) {
            System.out.println("RuntimeException: " + e.getMessage());
        }
        return result;
    }

    public CollectionDto<BookingRoomDto> getFutureBookingRoomCollection(LoginDto loginDto) {
        return getBookingRoomCollection(loginDto, false);
    }

    public CollectionDto<BookingRoomDto> getPastBookingRoomCollection(LoginDto loginDto) {
        return getBookingRoomCollection(loginDto, true);
    }

    public CollectionDto<BookingUserDto> getFutureBookingUserCollection(RoomDto roomDto) {
        return getBookingUserCollection(roomDto, false);
    }

    public CollectionDto<BookingUserDto> getPastBookingUserCollection(RoomDto roomDto) {
        return getBookingUserCollection(roomDto, true);
    }

    private CollectionDto<BookingRoomDto> getBookingRoomCollection(LoginDto loginDto, boolean isPast) {
        List<BookingRoomDto> dtos = new ArrayList<>();
        CollectionDto<BookingRoomDto> collection = null;
        try {
            List<User> users = userDao.getByFieldName("email", loginDto.getEmail());
            String userId = String.valueOf(users.get(0).getId());
            List<Booking> bookings = getByTime("user_id", userId, isPast);
            for (Booking booking : bookings)
                dtos.add(bookingToBookingRoomDto(booking));
            collection = new CollectionDto<>(dtos);
        } catch (Exception e) {
            System.out.println("RuntimeException: " + e.getMessage());
        }
        return collection;
    }

    private CollectionDto<BookingUserDto> getBookingUserCollection(RoomDto roomDto, boolean isPast) {
        List<BookingUserDto> dtos = new ArrayList<>();
        CollectionDto<BookingUserDto> collection = null;
        try {
            List<Room> rooms = roomDao.getByFieldName("number", String.valueOf(roomDto.getNumber()));
            String roomId = String.valueOf(rooms.get(0).getId());
            List<Booking> bookings = getByTime("room_id", roomId, isPast);
            for (Booking booking : bookings)
                dtos.add(bookingToBookingUserDto(booking));
            collection = new CollectionDto<>(dtos);
        } catch (Exception e) {
            System.out.println("RuntimeException: " + e.getMessage());
        }
        return collection;
    }


    private List<Booking> getByTime(String fieldName, String fieldValue, boolean isPast) {
        return isPast ? bookingDao.getPastByField(fieldName, fieldValue)
                : bookingDao.getFutureByField(fieldName, fieldValue);

    }
}
