package ua.cjhrxS.Controllers;

public enum ViewUrls {
	SIGNIN_JSP("/WEB-INF/views/LogIn.jsp"),
	SIGNUP_JSP("/WEB-INF/views/SignUp.jsp"),
	USER_COURSE_JSP("/WEB-INF/views/Course.jsp"),
	TEAHER_COURSE_JSP("/WEB-INF/views/teachers/TeacherCourse.jsp"),
	ADMIN_PANEL_JSP("/WEB-INF/views/AdminPanel.jsp"),
	ADMIN_USER_JSP("/WEB-INF/views/admins/AdminUser.jsp"),
	ADMIN_TEACHER_JSP("/WEB-INF/views/admins/AdminTeacher.jsp"),
	ADMIN_COURSE_JSP("/WEB-INF/views/AdminCourse.jsp"),
	ERROR_JSP("/WEB-INF/views/commons/Error.jsp");
	//
	private String url;

	private ViewUrls(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return url;
	}
}
