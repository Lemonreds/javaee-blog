package blog.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.db.VisitorDB;
import blog.utils.DateUtils;

//仅统计首页jsp的访问量
@WebFilter(filterName = "VisitFilter", urlPatterns = { "*.jsp" })
public class VisitFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest rq = (HttpServletRequest) request;
		HttpServletResponse rp = (HttpServletResponse) response;

		if (rq.getRequestURL().indexOf("index.jsp") != -1) {

			synchronized (this) {
				// System.out.println(rq.getRequestURI());
				Cookie[] cookies = rq.getCookies();
				boolean visited = false;
				if (cookies != null) {
					for (Cookie cookie : cookies) {
						if (cookie.getName().equals("myblog_visitor")) {
							visited = true;
							break;
						}
					}
				}
				if (!visited) {

					Thread t = new Thread(new Runnable() {
						public void run() {
							// 向数据库写入信息
							VisitorDB.visit(rq);

							// 发送新的cookie
							Cookie c = new Cookie("myblog_visitor", DateUtils.getFormatDate(new Date()));
							// cookie生命周60分钟
							c.setMaxAge(60 * 60);
							c.setPath("/Blog");
							rp.addCookie(c);
						}
					});
					t.start();
				}
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
