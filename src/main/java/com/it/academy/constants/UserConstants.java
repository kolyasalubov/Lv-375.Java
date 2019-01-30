package com.it.academy.constants;

/**
 * Enum UsersConstants keeps all constants which are connected with Users
 */
public enum UserConstants {

    ID("idUser"),
    EMAIL("email"),
    PASSWORD("password"),
    PASSWORD_REPEAT("passwordRepeat"),
    FIRST_NAME("firstName"),
    LAST_NAME("lastName"),
    POSITION("position"),
    PHONE("phone"),
    IS_ADMIN("isAdmin"),
    IS_BLOCKED("isBlocked"),

    LOGIN_DTO("loginDto"),
    USER_DTO("userDto"),
    USERS("users"),

    SIGN_UP("Sign up"),
    REGISTER("registration"),

    SAVE("Save"),
    USER_EDIT("user-edit"),

    URL_TO_POST("urlToPost"),
    ON_SUBMIT("onSubmit");

    private String constant;

    private UserConstants(String constant) {
        this.constant = constant;
    }

    @Override
    public String toString() {
        return constant;
    }
}
