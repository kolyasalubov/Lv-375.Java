package myArticles.edu.controllers;

public enum ControllersConstant {
    USERNAME("username"),
    PASSWORD("password"),
    EMAIL("email"),
    IS_ADMIN("isAdmin"),
    IS_BLOCK("isBlock"),
    SESSION_ID("id_session"),
    ERROR("error"),
    LOGIN_ERROR("Bad Login or Password"),
    REGISTER_ERROR("Bad Login or Email"),
    WRITE_ALL_FIELD_ERROR("Please fill in all fields"),
    ADD_ARTICLE_ERROR("Please select another name"),
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

