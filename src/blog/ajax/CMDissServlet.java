package blog.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.model.Comment;
import blog.service.CommentService;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class CMDissServlet
 */
@WebServlet("/CMDissServlet")
public class CMDissServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 业务操作 获取评论id
		String id = request.getParameter("id");
		// 返回的数据
		JSONObject jo = new JSONObject();
		boolean repeat = false;
		// 写一个cookie防止重复提交
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("diss_cm" + id)) {
				// 重复提交了数据
				jo.put("msg", "failed");
				repeat = true;
				break;
			}
		}
		if (!repeat) {

			CommentService cs = CommentService.getInstance();
			int new_diss = cs.star_diss(Integer.parseInt(id), Comment.DISS);
			jo.put("msg", "success");
			jo.put("new_diss", new_diss);

			// 发送新的cookie
			Cookie cookie = new Cookie("diss_cm" + id, System.currentTimeMillis() + "");
			// 设置有效期 15分钟
			cookie.setMaxAge(15 * 60);
			// 设置有效目录
			cookie.setPath("/Blog");
			// 写会浏览器
			response.addCookie(cookie);
		}
		// 写回ajax
		response.getWriter().println(jo);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
