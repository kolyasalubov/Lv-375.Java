package com.it.academy.common;


/**
 * Enum ControllerUrls keeps all urls which are mapped from the Servlets
 */
public enum ControllerUrls {

    ROOT_SERVLET("/"),
    LOGIN_SERVLET("/login"),
    LOGOUT_SERVLET("/logout"),
    REGISTRATION_SERVLET("/registration"),

    USER_SERVLET("/profile"),
    USER_EDIT_SERVLET("/user-edit"),

    ROOM_CREATE_SERVLET("/room-create"),
    ROOM_EDIT_SERVLET("/room-edit"),
    ROOM_DELETE_SERVLET("/room-delete"),

    BOOKING_CREATE_SERVLET("/booking-create"),
    BOOKING_EDIT_SERVLET("/booking-edit"),
    BOOKING_DELETE_SERVLET("/booking-delete"),

    HOME_SERVLET("/home"),

    ROOM_SERVLET("/room"),
    ROOM_ARCHIVE_SERVLET("/room-archive"),

    BOOKINGS_SERVLET("/bookings"),
    BOOKINGS_ARCHIVE_SERVLET("/bookings-archive"),

    ADMIN_ROOMS_SERVLET("/admin-rooms"),
    ADMIN_USERS_SERVLET("/admin-users");

    private String url;

    private ControllerUrls(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url;
    }
}
