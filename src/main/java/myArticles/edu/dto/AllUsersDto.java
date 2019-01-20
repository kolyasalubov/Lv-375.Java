package myArticles.edu.dto;

import java.util.ArrayList;
import java.util.List;

public class AllUsersDto {
    private List<UserDto> users;

    public AllUsersDto() {
        this.users = new ArrayList<>();

    }

    public void addUsers(UserDto userDto){
        users.add(userDto);
    }

    public List <UserDto> getUsers() {
        return users;
    }

    public void setUsers(List <UserDto> users) {
        this.users = users;
    }
}
