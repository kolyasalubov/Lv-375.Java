package myArticles.edu;


import myArticles.edu.Services.UserArticlesService;
import myArticles.edu.container.IocContainer;
import myArticles.edu.dto.*;

public class Main {

    public static void main(String[] args) {
        UserDto userDto = new UserDto("Syava", "1", "", false, false);
        PageInfoDto pageInfoDto = new PageInfoDto(2, 5);
        UsersArticleDto usersArticleDto = IocContainer.get().getUserArticlesService().getPageUsers(userDto, pageInfoDto);
        System.out.println(usersArticleDto.getArticles());
    }
}
