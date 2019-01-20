package com.it.academy.dto;

public class BookingUserDto extends BookingDto {

    private String userFirstName;
    private String userLastName;

    public BookingUserDto(){}

    public BookingUserDto(String userFirstName, String userLastName, String startDate, String endDate, String purpose){
        super(startDate, endDate, purpose);
        this.userFirstName = userFirstName;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
    }

    public BookingUserDto(BookingDto bookingDto){
        super(bookingDto.getStartDate(), bookingDto.getEndDate(), bookingDto.getPurpose());
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }
}
