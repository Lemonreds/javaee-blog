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
 * Servlet implementation class CMDeleServlet
 */
@WebServlet("/CMDeleServlet")
public class CMDeleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 业务操作 获取评论id
		String id = request.getParameter("id");
		// 返回的数据
		JSONObject jo = new JSONObject();
		CommentService cs = CommentService.getInstance();
		boolean b = cs.deleteComment(Integer.parseInt(id));
		if (b) {
			jo.put("msg", "success");
		} else {
			jo.put("msg", "fail");
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
