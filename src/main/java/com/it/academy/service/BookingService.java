package com.it.academy.service;

import com.it.academy.common.ObjContainer;
import com.it.academy.dao.BookingDao;
import com.it.academy.dao.RoomDao;
import com.it.academy.dao.UserDao;
import com.it.academy.dto.*;
import com.it.academy.entity.Booking;
import com.it.academy.entity.Room;
import com.it.academy.entity.User;

import java.util.ArrayList;
import java.util.List;

public class BookingService {

    private BookingDao bookingDao;
    private UserDao userDao;
    private RoomDao roomDao;

    public BookingService(){
        bookingDao = ObjContainer.getInstance().getBookingDao();
    }

    public BookingService(BookingDao bookingDao){
        this.bookingDao = bookingDao;
    }

    private Booking dtoToBooking(BookingDto bookingDto, UserDto userDto){
        Booking booking = new Booking();
        booking.setId(bookingDto.getIdBooking());
        booking.setStartDate(bookingDto.getStartDate());
        booking.setEndDate(bookingDto.getEndDate());
        booking.setPurpose(bookingDto.getPurpose());

        List<User> users = userDao.getByFieldName("email", userDto.getEmail());
        booking.setUserId(users.get(0).getId());
        return booking;
    }

    private Booking roomDtoToBooking(BookingRoomDto bookingRoomDto, UserDto userDto){
        Booking booking = dtoToBooking(bookingRoomDto, userDto);
        List<Room> rooms = roomDao.getByFieldName("number", String.valueOf(bookingRoomDto.getRoomNumber()));
        booking.setRoomId(rooms.get(0).getId());
        return booking;
    }

    private BookingUserDto bookingToBookingUserDto(Booking booking){
        BookingUserDto dto = new BookingUserDto();
        dto.setStartDate(booking.getStartDate());
        dto.setEndDate(booking.getEndDate());
        dto.setPurpose(booking.getPurpose());

        User user = userDao.getById(booking.getUserId());
        dto.setUserFirstName(user.getFirstName());
        dto.setUserLastName(user.getLastName());

        return dto;
    }

    private BookingRoomDto bookingToBookingRoomDto(Booking booking){
        BookingRoomDto dto = new BookingRoomDto();
        dto.setStartDate(booking.getStartDate());
        dto.setEndDate(booking.getEndDate());
        dto.setPurpose(booking.getPurpose());

        Room room = roomDao.getById(booking.getRoomId());
        dto.setRoomNumber(room.getNumber());

        return dto;
    }


    public boolean createBooking(BookingDto bookingDto, RoomDto roomDto, UserDto userDto){
        Booking booking = dtoToBooking(bookingDto, userDto);
        List<Room> rooms = roomDao.getByFieldName("number", String.valueOf(roomDto.getNumber()));
        booking.setRoomId(rooms.get(0).getId());
        return saveBookingToDB(booking);
    }

    public boolean createRoomBooking(BookingRoomDto bookingRoomDto, UserDto userDto){
        Booking booking = roomDtoToBooking(bookingRoomDto, userDto);
        return saveBookingToDB(booking);
    }

    private boolean saveBookingToDB(Booking booking){
        boolean result = true;
        try{
            if (bookingDao.isExist(booking))
                throw new RuntimeException();
            bookingDao.insert(booking);
        } catch (Exception e){
            System.out.println("RuntimeException: " + e.getMessage());
            result = false;
        } return result;
    }

    public boolean updateRoomBooking(BookingRoomDto bookingRoomDto, UserDto userDto){
        boolean result = true;
        Booking booking = roomDtoToBooking(bookingRoomDto, userDto);
        try {
            if (bookingDao.isExist(booking))
                throw new RuntimeException();
            bookingDao.updateEntityById(booking);
        } catch (Exception e){
            System.out.println("RuntimeException: " + e.getMessage());
            result = false;
        } return result;
    }

    public CollectionDto<BookingRoomDto> getFutureBookingRoomCollection(LoginDto loginDto){
        return getBookingRoomCollection(loginDto, "future");
    }

    public CollectionDto<BookingRoomDto> getPastBookingRoomCollection(LoginDto loginDto){
        return getBookingRoomCollection(loginDto, "past");
    }

    public CollectionDto<BookingUserDto> getFutureBookingUserCollection(RoomDto roomDto){
        return getBookingUserCollection(roomDto, "future");
    }

    public CollectionDto<BookingUserDto> getPastBookingUserCollection(RoomDto roomDto){
        return getBookingUserCollection(roomDto, "past");
    }

    private CollectionDto<BookingRoomDto> getBookingRoomCollection(LoginDto loginDto, String time){
        List<BookingRoomDto> dtos = new ArrayList<>();
        CollectionDto<BookingRoomDto> collection = null;
        try {
            List<User> users = userDao.getByFieldName("email", loginDto.getEmail());
            String userId = String.valueOf(users.get(0).getId());
            List<Booking> bookings = getByTime("user_id", userId, time);
            for(Booking booking : bookings)
                dtos.add(bookingToBookingRoomDto(booking));
            collection = new CollectionDto<>(dtos);
        } catch (Exception e) {
            System.out.println("RuntimeException: " + e.getMessage());
        }
        return collection;
    }

    private CollectionDto<BookingUserDto> getBookingUserCollection(RoomDto roomDto, String time){
        List<BookingUserDto> dtos = new ArrayList<>();
        CollectionDto<BookingUserDto> collection = null;
        try {
            List<Room> rooms = roomDao.getByFieldName("number", String.valueOf(roomDto.getNumber()));
            String roomId = String.valueOf(rooms.get(0).getId());
            List<Booking> bookings = getByTime("room_id", roomId, time);
            for(Booking booking : bookings)
                dtos.add(bookingToBookingUserDto(booking));
            collection = new CollectionDto<>(dtos);
        } catch (Exception e) {
            System.out.println("RuntimeException: " + e.getMessage());
        }
        return collection;
    }


    private List<Booking> getByTime(String fieldName, String fieldValue, String time){
        switch (time) {
            case "past":
                return bookingDao.getPastByField(fieldName, fieldValue);
            case "future":
                return bookingDao.getFutureByField(fieldName, fieldValue);
            default:
                return null;
        }
    }
}
