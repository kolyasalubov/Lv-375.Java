package com.it.academy.constants;

public enum BookingConstants {

    ID("idBooking"),
    ROOM_NUMBER("roomNumber"),
    START_DATE("startDate"),
    END_DATE("endDate"),
    PURPOSE("purpose"),

    BOOKINGS("bookings"),
    BOOKING_CREATE("booking-create"),
    BOOKING_EDIT("booking-edit"),

    BOOKING_DTO("bookingDto"),
    URL_TO_POST("urlToPost"),

    ARCHIVE("archive");

    private String constant;

    private BookingConstants(String constant) {
        this.constant = constant;
    }

    @Override
    public String toString() {
        return constant;
    }
}
