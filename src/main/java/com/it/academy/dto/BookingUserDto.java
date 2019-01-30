package com.it.academy.dto;

/**
 * Class BookingUserDto represents DTO object for operations with Bookings with user info
 */
public class BookingUserDto extends BookingDto {

    private String userFirstName;
    private String userLastName;

    public BookingUserDto(){}

    public BookingUserDto(BookingDto bookingDto){
        super(bookingDto.getIdBooking(), bookingDto.getUserEmail(), bookingDto.getStartDate(), bookingDto.getEndDate(),
                bookingDto.getStartTime(), bookingDto.getEndTime(), bookingDto.getPurpose());
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
