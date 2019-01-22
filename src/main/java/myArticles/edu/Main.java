package myArticles.edu;


import myArticles.edu.container.IocContainer;
import myArticles.edu.dto.UserDto;

public class Main {

    public static void main(String[] args) {
        UserDto userDto = new UserDto("Admin", "", "", true, false);
        IocContainer.get().getUserService().changeAdminStatus(userDto);
    }
}
