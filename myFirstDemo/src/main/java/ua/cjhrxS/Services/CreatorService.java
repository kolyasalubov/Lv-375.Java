package ua.cjhrxS.Services;

import ua.cjhrxS.DAO.CourseDao;
import ua.cjhrxS.DAO.RoleDao;
import ua.cjhrxS.DAO.TeacherDao;
import ua.cjhrxS.DAO.UsersDao;


public class CreatorService {

	UsersDao userDao;
	TeacherDao teacherDao;
	RoleDao roleDao;
	CourseDao courseDao;

	public CreatorService() {
		roleDao = new RoleDao();
		userDao = new UsersDao();
		teacherDao = new TeacherDao();
		courseDao = new CourseDao();
		
	}

	public boolean createTable() {
		boolean create = true;
		if (create) {

			roleDao.create(); 
			userDao.create(); 
			teacherDao.create(); 
			courseDao.create();

		} else {

			create = false;

		}

		return create;

	}

}
