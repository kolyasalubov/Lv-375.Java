package myArticles.edu.entity;

import java.util.Objects;
/**
 * Class , which contains all information about Users and Method how work with it
 *
 */
public class User implements IEntity {
    private long userID;
    private String userName;
    private String password;
    private String email;
    private boolean isBlock;
    private boolean isAdmin;


    public User(Long id, String userName, String password, String email, boolean isBlock, boolean isAdmin) {
        this.userID = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.isBlock = isBlock;
        this.isAdmin = isAdmin;
    }

    public User( String userName, String password, String email, boolean isBlock, boolean isAdmin) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.isBlock = isBlock;
        this.isAdmin = isAdmin;
    }

    public Long getID() {
        return this.userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isBlocked() {
        return isBlock;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBlock(boolean block) {
        isBlock = block;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public enum UserEntityQueries {
        INSERT(SqlQueries.INSERT, "INSERT INTO USERS(Username, Password, Email, isBlock, isAdmin) VALUE('%s', '%s', '%s', %s, %s);"),
        GET_ALL(SqlQueries.GET_ALL, "SELECT * FROM USERS ;"),
        GET_BY_ID(SqlQueries.GET_BY_ID, "SELECT * FROM USERS WHERE Id = %s ;"),
        GET_BY_FIELD(SqlQueries.GET_BY_FIELD, "SELECT * FROM USERS WHERE %s = '%s' ;"),
        GET_BY_USERNAME(SqlQueries.GET_BY_USERNAME, "SELECT * FROM USERS WHERE Username = '%s' ;"),
        GET_BY_TWO_FIELD(SqlQueries.GET_BY_TWO_FIELD, "SELECT * FROM USERS WHERE %s = '%s' OR %s = '%s' ;"),
        DELETE_BY_ID(SqlQueries.DELETE_BY_ID, "DELETE FROM USERS WHERE Id = %s ;"),
        UPDATE_FIELD_BY_ID(SqlQueries.UPDATE_FIELD_BY_ID, "UPDATE USERS SET %s = '%s' WHERE Id = %s ;"),
        UPDATE_BY_ID(SqlQueries.UPDATE_BY_ID, "UPDATE USERS SET Password = '%s', Email = '%s', isAdmin = %s, isBlock = %s WHERE Id = %s ;");

        private SqlQueries sqlQuery;
        private String query;

        UserEntityQueries(SqlQueries sqlQuery, String query) {
            this.sqlQuery = sqlQuery;
            this.query = query;
        }

        public SqlQueries getSqlQuery() {
            return this.sqlQuery;
        }

        public String toString() {
            return this.query;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, userName, password, email, isAdmin, isBlock);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return Objects.equals(userName, user.userName) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(isAdmin, user.isAdmin) &&
                Objects.equals(isBlock, user.isBlock);
    }

    @Override
    public String toString() {
        return Long.toString(userID) + " " + userName + " " + password + " " + email + " ";
    }


}
