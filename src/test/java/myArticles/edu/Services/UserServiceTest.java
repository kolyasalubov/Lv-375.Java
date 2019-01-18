package myArticles.edu.Services;

import myArticles.edu.container.IocContainer;
import myArticles.edu.dto.LoginDto;
import myArticles.edu.dto.UserDto;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    @org.junit.Test
    public void checkValidLogin() {
        LoginDto loginDto = new LoginDto("Syavas", "11122");
        UserService userService =  IocContainer.get().getUserService();
        boolean want = userService.checkValidLogin(loginDto);
        Assert.assertTrue(want);
    }
    @Test
    public void updateUser(){
        UserDto userDto = new UserDto("Syavas", "1444","dgfdfs", false, true);
        UserService userService = IocContainer.get().getUserService();
        boolean want = userService.updateUser(userDto);
        Assert.assertTrue(want);
    }

}