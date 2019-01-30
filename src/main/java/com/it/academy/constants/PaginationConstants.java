package com.it.academy.constants;

/**
 * Enum PaginationConstants keeps all constants which are connected with Pagination
 */
public enum PaginationConstants {

    PAGE_OFFSET("pageOffset"),
    PAGE("page"),

    ROOM_PAGE("room"),
    USER_PAGE("user"),
    BOOKING_ROOM_PAGE("bookingRoom"),
    BOOKING_USER_PAGE("bookingUser");

    private String constant;

    private PaginationConstants(String constant) {
        this.constant = constant;
    }

    @Override
    public String toString() {
        return constant;
    }
}
