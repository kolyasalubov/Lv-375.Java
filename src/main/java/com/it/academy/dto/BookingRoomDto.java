package com.it.academy.dto;

/**
 * Class BookingRoomDto represents DTO object for operations with Bookings with roomNumber
 */
public class BookingRoomDto extends BookingDto {

    private String roomNumber;

    public BookingRoomDto(){}

    public BookingRoomDto(BookingDto bookingDto){
        super(bookingDto.getIdBooking(), bookingDto.getUserEmail(), bookingDto.getStartDate(), bookingDto.getEndDate(),
                bookingDto.getStartTime(), bookingDto.getEndTime(), bookingDto.getPurpose());
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
