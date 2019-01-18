package com.it.academy.dto;

public class BookingRoomDto extends BookingDto {

    private int roomNumber;

    public BookingRoomDto(){}

    public BookingRoomDto(int roomNumber, String startDate, String endDate, String purpose){
        super(startDate, endDate, purpose);
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
