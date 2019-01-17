package myArticles.edu.Services;


import myArticles.edu.DataBase.DAO.UserDao;
import myArticles.edu.dto.AllUsersDto;
import myArticles.edu.dto.UserDto;
import myArticles.edu.entity.User;

/**
 * Only Admin can use this Service to see all users and change their
 */
public class AdminService {
    private UserDao userDao;

    public AdminService(UserDao userDao){
        this.userDao = userDao;
    }

    /**
     * Get all users from database
     *
     */
    public AllUsersDto getAllUsers(){
        AllUsersDto allUsersDto = new AllUsersDto();
        for(User user : userDao.getAll()){
            UserDto userDto = new UserDto(user.getUserName(),
                    user.getPassword(),
                    user.getEmail(),
                    user.isAdmin(),
                    user.isBlocked());

            allUsersDto.addUsers(userDto);
        }
        return allUsersDto;
    }
}
