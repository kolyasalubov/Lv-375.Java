package myArticles.edu.controllers;

/**
 * Class with contains all constant which used in controllers
 */
public enum ControllersConstant {
    //User Info
    USERNAME("username"),
    PASSWORD("password"),
    REPEAT_PASSWORD("repeat"),
    EMAIL("email"),
    IS_ADMIN("isAdmin"),
    IS_BLOCK("isBlock"),
    //Article info
    ARTICLE_NAME("name"),
    ARTICLE_DESCRIPTION("description"),
    ARTICLE_URL("url"),
    ARTICLES_USER_ID("userId"),

    //Request data
    SESSION_ID("id_session"),
    CANCEL("Cancel"),

    //Errors
    ERROR("error"),
    LOGIN_ERROR("Bad Username/Password or you are Blocked!"),
    REGISTER_ERROR("Bad Username or Email!"),
    PASSWORD_ERROR("Passwords do not match!"),
    UPDATE_ERROR("Please select another Email!"),
    EDIT_PROFILE_PASSWORD_ERROR("Password is null, or they do not match"),
    ADD_ARTICLE_ERROR("Please select another name"),

    //Dto
    LOGIN_DTO("loginDto"),
    USER_DTO("userDto"),
    USER_ARTICLE_DTO("usersArticleDto"),
    ALL_USER_DTO("allUsersDto"),
    //Page value
    DEFAULT_VISIBLE_VALUE("100000"),
    PAGE_NUMBER("pageNumber"),
    VISIBLE_ITEM_ARTICLE("visibleArticle"),
    VISIBLE_ITEM_USER("visibleUser"),
    NUMBER_OF_ARTICLES("countArticles"),
    NUMBER_OF_USERS("countUsers"),
    //CONSTANT
    TRUE("true"),
    FALSE("false"),
    //Default parameter
    ADMIN_USERNAME("admin"),
    ADMIN_PASSWORD("admin"),
    ADMIN_EMAIL("admin");


    private String field;

    ControllersConstant(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return field;
    }
}

