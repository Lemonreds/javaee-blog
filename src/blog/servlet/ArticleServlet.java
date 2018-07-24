package blog.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.model.Article;
import blog.service.ArticleService;
import blog.service.CommentService;
import blog.service.TagService;

@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 想要获取的文章 id
		String id = request.getParameter("id");
		ArticleService as = ArticleService.getInstance();
		// 文章
		Article a = as.getArticle("id", id).get(0);
		request.setAttribute("article", a);

		// 文章的所有标签
		TagService ts = TagService.getInstance();
		request.setAttribute("article_tags", ts.getTagById(id));
		// 获取上一篇文章
		request.setAttribute("article_pre", as.getPreviousArticle(a.getTime()));
		// 获取下一篇文章
		request.setAttribute("article_next", as.getNextArticle(a.getTime()));
		// 加载文章评论
		CommentService cs = CommentService.getInstance();
		request.setAttribute("comment", cs.loadComment(a.getId()));

		request.getRequestDispatcher("/page/article.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
