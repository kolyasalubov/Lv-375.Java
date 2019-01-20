package myArticles.edu.controllers;

public enum ControllersConstant {
    USERNAME("username"),
    PASSWORD("password"),
    EMAIL("email"),
    IS_ADMIN("isAdmin"),
    IS_BLOCK("isBlock"),
    SESSION_ID("id_session"),
    ERROR("error"),
    LOGIN_ERROR("Bad Username or Password!"),
    REGISTER_ERROR("Bad Username or Email!"),
    PASSWORD_ERROR("Passwords do not match!"),
    UPDATE_ERROR("Please select another Email!"),
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

