package myArticles.edu.controllers;

public enum ControllerUrls {
    //Login Logout and simplyUrl

    ROOT_SERVLET("/"),            // Get
    LOGIN_SERVLET("/login"),    // Get	// Post login+password
    LOGOUT_SERVLET("/logout"),

    //AdminPage

    ADMIN_LOGIN_SERVLET("/adminlogin"),

    //Register, Edit,  Update, Cancel User

    USER_CREATE_SERVLET("/userregister"),    // Get?
    USER_EDIT_SERVLET("/useredit"),        // Post
    USER_UPDATE_SERVLET("/userupdate"),    // Post
    USER_CANCEL_SERVLET("/usercancel"),    // Post

    //Register, Edit,  Update, Cancel and Delete Articles

    ATRICLE_CREATE_SERVLET("/articleadd"),    // Post
    ATRICLE_EDIT_SERVLET("/articleedit"),        // Post
    ATRICLE_UPDATE_SERVLET("/articleupdate"),    // Post
    ATRICLE_CANCEL_SERVLET("/articlecancel"),    // Post
    ITEM_DELETE_SERVLET("/articledelete"),

    //MainPage
    ITEM_COUNT_SERVLET("/itemcount"), // Post
    USER_ITEMS_SERVLET("/useritems"), // Post // and pagination

    //Initialization
    INITIALIZATION("/initialization");

    private String url;

    ControllerUrls(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url;
    }
    }
