package blog.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import blog.dao.ArticleDao;
import blog.daoImpl.ArticleDaoImpl;
import blog.model.Article;
import blog.model.AxisArticle;
import blog.utils.ArticleUtils;
import blog.utils.StringUtils;

public class ArticleService {

	private ArticleDao dao;

	private static ArticleService instance;

	private ArticleService() {
		dao = ArticleDaoImpl.getInstance();
	}

	/**
	 * 获取实例
	 * 
	 * @return
	 */
	public static final ArticleService getInstance() {
		if (instance == null) {
			try {
				instance = new ArticleService();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	/**
	 * 获取上一篇文章
	 * 
	 * @param time
	 * @return
	 */
	public Article getPreviousArticle(String time) {
		return dao.getANearArticle(time, dao.LESS);
	}

	/**
	 * 获取下一篇
	 * 
	 * @param time
	 * @return
	 */
	public Article getNextArticle(String time) {
		return dao.getANearArticle(time, dao.MORE);
	}

	/**
	 * 获取文章的数量 或 分类的数量
	 * 
	 * @param search_key
	 * @return
	 */
	public int getCount(String search_key) {
		return dao.getCount(search_key);
	}

	/**
	 * 获取时间轴显示的文章 Todo: 重写这段垃圾代码 算法不行
	 * 
	 * @return
	 */
	public List getAxisList() {
		// 获取数据库中的所有文章
		List<Article> articles = dao.getAllArticle();
		// 用来存 时间轴文章 (一种比Article类更简单适用的对象)
		List<AxisArticle> axis_list = new ArrayList();
		// Article->AxisArticle
		for (Article a : articles) {
			AxisArticle at = ArticleUtils.getAxisArticle(a);
			axis_list.add(at);
		}
		// 这里开始处理数据 文章排序是 2018-2017-2016 时间降序
		// 因为要实现 文章+文章+year 文章+文章+year的效果 这里把year封装成一个特殊的AxisArticle对象 id=0 year =
		// 文章截至日期
		// 然后全部存入 result 中
		// 在jsp判断id==0
		// true: year输出
		// false: 输出AxisArticle对象的
		AxisArticle tmp = null;
		List result = new LinkedList();
		// 塞进去最新的一个年份 并且塞入第一个AxisArticle
		if (!axis_list.isEmpty()) {
			tmp = new AxisArticle();
			tmp.setId(0);
			tmp.setYear(axis_list.get(0).getYear());
			result.add(tmp);
			result.add(axis_list.get(0));
		}
		// 判断文章年份是不是不一样 不一样则塞一个year
		for (int i = 1; i < axis_list.size(); i++) {
			int present_year = axis_list.get(i).getYear();
			int past_year = axis_list.get(i - 1).getYear();

			if (present_year < past_year) {
				tmp = new AxisArticle();
				tmp.setId(0);
				tmp.setYear(present_year);
				result.add(tmp);
			}
			result.add(axis_list.get(i));
		}
		// 注意: 在list遍历里面动态修改了数组长度会出现内存溢出的情况
		return result;
	}

	/**
	 * 通过列属性获取文章
	 * 
	 * @param column
	 * @param value
	 * @return
	 */
	public List<Article> getArticle(String column, String value) {
		return dao.getArticleByColumn(column, value);
	}

	/**
	 * 获取分类及该分类的文章数量
	 * 
	 * @return
	 */
	public Map getSortAndCount() {
		// TO DO
		// 需要重写这个方法
		return dao.getColumAndCount(dao.SEARCH_SORT);
	}

	/**
	 * 获取 所有文章 截取文章长度 截取一下时间 去掉时 分钟 秒
	 * 
	 * @return
	 */
	public List getArticle() {
		List<Article> list = dao.getAllArticle();
		for (Article a : list) {
			ArticleUtils.cutContent(list);
			ArticleUtils.cutTime(list);
		}
		return list;
	}

	/**
	 * 获取分类和它的文章
	 * 
	 * @return
	 */
	public Map getSortAndAirticle(String sort_name) {

		Map map = new HashMap();
		List<Article> articleBySort = null;

		// 获取所有分类
		if (sort_name.equals("all") || StringUtils.isEmpty(sort_name)) {
			List<String> sorts = dao.getAllSort();

			for (int i = 0; i < sorts.size(); i++) {
				String sort = sorts.get(i);
				articleBySort = dao.getArticleByColumn("sort", sort);
				ArticleUtils.cutTime(articleBySort);
				map.put(sort, articleBySort);
			}
		} else {
			// 获取单个分类
			articleBySort = dao.getArticleByColumn("sort", sort_name);
			ArticleUtils.cutTime(articleBySort);
			map.put(sort_name, articleBySort);
		}
		return map;
	}

	public List getVisitRank() {
		List list = dao.getVisitRank();
		if (list.size() > 10) {
			for (int i = 10; i < list.size(); i++) {
				list.remove(i);
			}
		}
		for (int y = 0; y < list.size(); y++) {

			Article a = (Article) list.get(y);
			if (a.getTitle().length() > 20) {
				a.setTitle(StringUtils.cutString(a.getTitle(), 0, 19) + "...");
			}

		}

		return list;

	}

	public List getAllSort() {
		return dao.getAllSort();
	}

	public int starArticle(int id) {
		return dao.star_article(id);
	}

	public void addVisit(int article_id) {
		dao.addVisit(article_id);
	}

}
// 2017年9月20日10:18:38 怎么还没搞定
// 2017年9月20日21:30:18