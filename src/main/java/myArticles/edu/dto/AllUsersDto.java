package myArticles.edu.dto;

import java.util.ArrayList;
import java.util.List;

public class AllUsersDto {
    private List<UserDto> users;

    public AllUsersDto() {
        this.users = new ArrayList<>();
        //TODO page size
    }

    public void addUsers(UserDto userDto){
        users.add(userDto);
    }


}
