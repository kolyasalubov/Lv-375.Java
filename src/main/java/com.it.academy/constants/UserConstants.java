package com.it.academy.constants;

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

    LOGIN_DTO("loginDto");

    private String constant;

    private UserConstants(String constant) {
        this.constant = constant;
    }

    @Override
    public String toString() {
        return constant;
    }
}
