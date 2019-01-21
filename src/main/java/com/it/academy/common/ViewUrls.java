package com.it.academy.common;

public enum ViewUrls {

    LOGIN_JSP("/WEB-INF/views/common/login.jsp"),

    HOME_JSP("/WEB-INF/views/common/home.jsp"),
    ROOM_JSP("/WEB-INF/views/rooms/room.jsp"),
    ROOM_ARCHIVE_JSP("/WEB-INF/views/rooms/roomArchive.jsp"),
    BOOKINGS_JSP("/WEB-INF/views/bookings/bookings.jsp"),
    BOOKINGS_ARCHIVE_JSP("/WEB-INF/views/bookings/bookingsArchive.jsp"),

    USER_PROFILE_JSP("/WEB-INF/views/users/userProfile.jsp"),
    USER_PROFILE_EDIT_JSP("/WEB-INF/views/users/userProfileEdit.jsp"),
    ROOM_PROFILE_JSP("/WEB-INF/views/rooms/roomProfile.jsp"),
    BOOKING_PROFILE_JSP("/WEB-INF/views/bookings/bookingProfile.jsp"),

    ADMIN_USERS_JSP("/WEB-INF/views/admin/adminUsers.jsp"),
    ADMIN_ROOMS_JSP("/WEB-INF/views/admin/adminRooms.jsp");

    private String url;

    private ViewUrls(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url;
    }
}
