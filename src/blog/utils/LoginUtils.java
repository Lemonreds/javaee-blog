package blog.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import blog.dao.UserDao;
import blog.daoImpl.UserDaoImpl;
import blog.model.User;

public class LoginUtils {

	public static void login(HttpServletRequest request) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
			return;

		UserDao dao = UserDaoImpl.getInstance();
		User user = dao.login(username, password);
		if (user == null)
			return;

		// 写入session
		HttpSession session = request.getSession();
		session.setAttribute("user", user);

	}

}
