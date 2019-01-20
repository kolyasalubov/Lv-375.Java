package com.it.academy.dto;

public class BookingRoomDto extends BookingDto {

    private String roomNumber;

    public BookingRoomDto(){}

    public BookingRoomDto(BookingDto bookingDto){
        super(bookingDto.getStartDate(), bookingDto.getEndDate(), bookingDto.getPurpose());
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
