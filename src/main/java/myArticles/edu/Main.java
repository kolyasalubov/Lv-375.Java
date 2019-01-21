package myArticles.edu;


import myArticles.edu.Services.UserArticlesService;
import myArticles.edu.container.IocContainer;
import myArticles.edu.dto.*;

public class Main {

    public static void main(String[] args) {
        System.out.println((String)System.getenv().get("MY_SQL_LOGIN"));
        System.out.println((String)System.getenv().get("MY_SQL_PASSWORD"));
    }
}
