package com.it.academy.common;

public enum ControllerUrls {

//    ROOT_SERVLET("/"),			// Get
    LOGIN_SERVLET("/login"),	// Get	// Post login+password
//    LOGOUT_SERVLET("/logout"),	// Post
//    REGISTRATION_SERVLET("/registration"),	// Post

    USER_EDIT_SERVLET("/user-edit"),		// Post
    ADMIN_EDIT_SERVLET("/admin-edit"),		// Post
    USER_UPDATE_SERVLET("/user-update"),	// Post

    ROOM_CREATE_SERVLET("/room-create"),	// Post
    ROOM_EDIT_SERVLET("/room-edit"),		// Post
    ROOM_DELETE_SERVLET("/room-delete"), // Post

    BOOKING_CREATE_SERVLET("/booking-create"),	// Post
    BOOKING_EDIT_SERVLET("/booking-edit"),		// Post
    BOOKING_DELETE_SERVLET("/booking-delete"), // Post

    ITEM_COUNT_SERVLET("/itemcount"), // ??? pagination ???
    HOME_SERVLET("/home"), // Post // and pagination

//    ROOM_SERVLET("/room"),
//    ROOM_ARCHIVE_SERVLET("/room-archive"),

//    BOOKINGS_SERVLET("/bookings"),
//    BOOKINGS_ARCHIVE_SERVLET("/bookings-archive"),

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
