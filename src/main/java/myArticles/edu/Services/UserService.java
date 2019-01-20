package myArticles.edu.Services;


import myArticles.edu.DataBase.DAO.UserDao;
import myArticles.edu.dto.LoginDto;
import myArticles.edu.dto.UserDto;
import myArticles.edu.entity.User;

/**
 * Use this class to connect with DAO layer and process info from controllers
 * Work only with Users
 */
public class UserService {
    private static final String USERNAME_FIELD = "Username";
    private static final String EMAIL_FIELD = "Email";
    private static final String BLOCK_FIELD = "isBlock";
    private static final String ADMIN_FIELD = "isAdmin";

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Check is users registration process valid or no
     * @param userDto - Info after registration
     * @return true - valid, false - no
     */
    private boolean checkValidRegistration(UserDto userDto) {
        boolean result = true;
        try {
            userDao.getByTwoField(USERNAME_FIELD, userDto.getUserName(), EMAIL_FIELD, userDto.getEmail()).get(0);
            result = false;
        } catch (Exception e) {
            //User with this Username and Email is Unique
        }
        return result;
    }

    private boolean checkValidUpdate(UserDto userDto){
        boolean result = true;
        try {
            User user = userDao.getByField(EMAIL_FIELD, userDto.getEmail()).get(0);
            if(!user.getUserName().equals(userDto.getUserName())){
                result = false;
            }
        } catch (Exception e){
            //can update user
        }
        return result;
    }

    public Long getIdUserByLogin(LoginDto loginDto) {
        return userDao.getUserEntityByUsername(loginDto.getUserName()).getID();
    }

    public Long getIdUserByLogin(UserDto userDto) {
        return userDao.getUserEntityByUsername(userDto.getUserName()).getID();
    }

    /**
     * Try to register user
     * @param userDto - Info about user
     * @return true - registered, false - error
     */
    public boolean registerUser(UserDto userDto) {
        boolean result = true;
        User user;
        if (!checkValidRegistration(userDto)) {
            result = false;
        } else {
            user = new User(userDto.getUserName(), userDto.getPassword(), userDto.getEmail(), userDto.isAdmin(), userDto.isBlock());
            userDao.insert(user);
        }
        return result;
    }

    /**
     * Update info about user in Database
     * @param userDto - new info about user
     * @return true - updated, false - error
     */
    public boolean updateUser(UserDto userDto) {
        Long id = getIdUserByLogin(userDto);
        boolean result = true;
        if(checkValidUpdate(userDto)) {
            User user = new User(id, userDto.getUserName(),
                    userDto.getPassword(),
                    userDto.getEmail(),
                    userDto.isAdmin(),
                    userDto.isBlock());
            userDao.updateAllByEntity(user);
        }
        else {
            result = false;
        }
        return result;
    }

    public UserDto getUserDto(LoginDto loginDto) {
        User user = userDao.getUserEntityByUsername(loginDto.getUserName());
        return new UserDto(user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                user.isAdmin(),
                user.isBlocked());
    }

    /**
     * Check is Login process valid or no
     * @param loginDto - info about login process
     * @return true - info is correct, false - error
     */
    public boolean checkValidLogin(LoginDto loginDto) {
        boolean result = true;
        User users = null;
        try {
            users = userDao.getUserEntityByUsername(loginDto.getUserName());
        } catch (Exception e) {
            // Logging Exception
            System.out.println("RuntimeException, message: " + e.getMessage());
            result = false;
        }
        result = result && (users.getPassword().equals(loginDto.getPassword())) && !users.isBlocked();
        return result;
    }

    /**
     * Block or unblock user
     * @param userDto user's info
     *
     */
    public boolean changeBlockStatus(UserDto userDto){
        User user = userDao.getUserEntityByUsername(userDto.getUserName());
        user.setBlock(!user.isBlocked());
        return userDao.updateAllByEntity(user);
    }
    /**
     * make user admin or take the right
     * @param userDto user's info
     *
     */
    public boolean changeAdminStatus(UserDto userDto){
        User user = userDao.getUserEntityByUsername(userDto.getUserName());
        user.setAdmin(!user.isAdmin());
        return userDao.updateAllByEntity(user);
    }
}
