package com.it.academy.entity;


public class User implements IEntity {

    public static enum UserQueries {

        CREATE_TABLE(QueryNames.CREATE_TABLE, "CREATE TABLE IF NOT EXISTS users(" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "email VARCHAR(30) NOT NULL," +
                "password VARCHAR(20) NOT NULL," +
                "first_name VARCHAR(20) NOT NULL," +
                "last_name VARCHAR(40) NOT NULL," +
                "position VARCHAR(20)," +
                "phone VARCHAR(12)," +
                "is_admin BOOL DEFAULT FALSE ," +
                "is_blocked BOOL DEFAULT FALSE ," +
                "UNIQUE(email)" +
                ")"),

        INSERT(QueryNames.INSERT, "INSERT INTO users(email, password, first_name, last_name, position, phone) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s');"),

        GET_BY_ID(QueryNames.GET_BY_ID, "SELECT * FROM users WHERE id = %s;"),
        GET_BY_FIELD(QueryNames.GET_BY_FIELD, "SELECT * FROM users WHERE %s = '%s';"),
        GET_ALL(QueryNames.GET_ALL, "SELECT * FROM users;"),

        UPDATE_ROW_BY_ID(QueryNames.UPDATE_ROW_BY_ID, "UPDATE users " +
                "SET email = '%s', password = '%s', first_name = '%s', last_name = '%s', position = '%s', " +
                "phone = '%s' WHERE id = %s;"),
        UPDATE_ROW_BY_FIELD(QueryNames.UPDATE_ROW_BY_FIELD, "UPDATE users " +
                "SET email = '%s', password = '%s', first_name = '%s', last_name = '%s', position = '%s', " +
                "phone = '%s' WHERE %s = '%s';"),
        UPDATE_FIELD_BY_ID(QueryNames.UPDATE_FIELD_BY_ID, "UPDATE users SET %s = %s WHERE id = %s;"),
        UPDATE_FIELD_BY_FIELD(QueryNames.UPDATE_FIELD_BY_FIELD, "UPDATE users SET %s = %s WHERE %s = '%s';"),

        IS_EXIST(QueryNames.IS_EXIST, "SELECT id FROM users WHERE email = '%s' AND password = '%s';");


        private QueryNames queryName;
        private String query;

        private UserQueries(QueryNames queryName, String query) {
            this.queryName = queryName;
            this.query = query;
        }

        public QueryNames getQueryName() {
            return queryName;
        }

        public String getQuery() {
            return query;
        }
    }

    private long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String position;
    private String phone;
    private boolean isAdmin;
    private boolean isBlocked;

    public User(){}

    public User(long id, String email, String password, String firstName, String lastName, String position, String phone, boolean isAdmin, boolean isBlocked) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.phone = phone;
        this.isAdmin = isAdmin;
        this.isBlocked = isBlocked;
    }

    public User(long id, String email, String password, String firstName, String lastName, String position, String phone) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.phone = phone;
    }

    public User(String email, String password, String firstName, String lastName, String position, String phone) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.phone = phone;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", phone='" + phone + '\'' +
                ", isAdmin=" + isAdmin +
                ", isBlocked=" + isBlocked +
                '}';
    }
}
