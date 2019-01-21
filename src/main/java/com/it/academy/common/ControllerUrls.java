package com.it.academy.common;

public enum ControllerUrls {

    ROOT_SERVLET("/"),
    LOGIN_SERVLET("/login"),
    LOGOUT_SERVLET("/logout"),
    REGISTRATION_SERVLET("/registration"),

    USER_SERVLET("/profile"),        //TODO
    USER_EDIT_SERVLET("/user-edit"),        //TODO
    ADMIN_EDIT_SERVLET("/admin-edit"),	    //TODO
    USER_UPDATE_SERVLET("/user-update"),    //TODO
    ROOM_CREATE_SERVLET("/room-create"),	//TODO
    ROOM_EDIT_SERVLET("/room-edit"),		//TODO
    ROOM_DELETE_SERVLET("/room-delete"),    //TODO

    BOOKING_CREATE_SERVLET("/booking-create"),
    BOOKING_EDIT_SERVLET("/booking-edit"),
    BOOKING_DELETE_SERVLET("/booking-delete"),

    ITEM_COUNT_SERVLET("/itemcount"), // ??? pagination ???
    HOME_SERVLET("/home"),

    ROOM_SERVLET("/room"),
    ROOM_ARCHIVE_SERVLET("/room-archive"),

    BOOKINGS_SERVLET("/bookings"),  //TODO
    BOOKINGS_ARCHIVE_SERVLET("/bookings-archive"), //TODO

    ADMIN_ROOMS_SERVLET("/admin-rooms"), //TODO
    ADMIN_USERS_SERVLET("/admin-users"); //TODO

    private String url;

    private ControllerUrls(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url;
    }
}
