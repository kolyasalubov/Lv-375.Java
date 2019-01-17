package myArticles.edu.controllers;

public enum ControllersConstant {
    USERNAME("userName"),
    PASSWORD("password"),
    EMAIL("email"),
    IS_ADMIN("isAdmin"),
    IS_BLOCK("isBlock"),
    SESSION_ID("id_session"),
    ERROR("error"),
    LOGIN_ERROR("Bad Login or Password"),
    REGISTER_ERROR("Bad Login or Email"),
    LOGIN_URL("loginUrl"),
    LOGIN_DTO("loginDto"),
    USER_DTO("userDto");



    private String field;

    ControllersConstant(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return field;
    }
}

