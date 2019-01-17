package myArticles.edu.dto;

public class UserDto {
    private String userName;
    private String password;
    private String email;
    private boolean isAdmin;
    private boolean isBlock;

    public UserDto(String userName, String password, String email, boolean isAdmin, boolean isBlock){
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
        this.isBlock = isBlock;
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

    public boolean isBlock() {
        return isBlock;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setBlock(boolean block) {
        isBlock = block;
    }
}
