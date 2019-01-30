package com.it.academy.constants;

/**
 * Enum ErrorConstants keeps all constants which are connected with Errors
 */
public enum ErrorConstants {

    NO_ROOMS("There are no rooms yet!"),
    NO_BOOKINGS("There are no bookings yet!"),

    NOT_THE_ADMIN("You are not the admin!"),

    INVALID_BOOKING_TO_ADD("We can not add your booking!"),
    INVALID_BOOKING_TO_UPDATE("We can not update your booking!"),
    INVALID_TIME("This time is not available!"),
    ROOM_NOT_EXIST("The room does not exist!"),
    ROOM_EXIST("The room already exists!"),

    BLOCKED("You are blocked user!"),
    BAD_CREDITS("Bad Login or Password!"),

    EMAIL_EXIST("This email already exists!"),
    PASS_ERROR("Passwords do not match!"),

    ERROR("error");

    private String constant;

    private ErrorConstants(String constant) {
        this.constant = constant;
    }

    @Override
    public String toString() {
        return constant;
    }
}

