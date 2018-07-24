package blog.dao;

import java.util.List;
import java.util.Map;

import blog.model.Article;

public interface ArticleDao {

	static final String SEARCH_ARTICLE = "article";
	static final String SEARCH_SORT = "sort";

	static final int LESS = 1;
	static final int MORE = 2;

	/**
	 * 浏览了文章 增加文章的浏览次数
	 * 
	 * @param article_id
	 */
	void addVisit(int article_id);

	/**
	 * 获取上一文章 或 下一文章
	 * 
	 * @param time
	 * @param less_or_more
	 * @return
	 */
	Article getANearArticle(String time, int less_or_more);

	/**
	 * 分组某一列属性 计算每个组的大小 返回Map
	 * 
	 * @param search_column
	 * @return
	 */
	Map getColumAndCount(String search_column);

	/**
	 * 返回所有的类别
	 * 
	 * @return
	 */
	List getAllSort();

	/**
	 * 新的文章
	 * 
	 * @param a
	 * @return
	 */
	Article addArticle(Article a);

	/**
	 * 删除文章
	 * 
	 * @param id
	 * @return
	 */
	boolean deleteArticle(String id);

	/**
	 * 获取所有的文章
	 * 
	 * @return
	 */
	List getAllArticle();

	/**
	 * 获取阅读排行文章列表
	 * 
	 * @return
	 */
	List getVisitRank();

	/**
	 * 通过某一列查询文章
	 * 
	 * @param column
	 * @param value
	 * @return
	 */
	List<Article> getArticleByColumn(String column, String value);

	/**
	 * 获取文章的数量或者类别的数量
	 * 
	 * @param search_key
	 * @return
	 */
	int getCount(String search_key);

	/**
	 * 点赞了文章
	 * 
	 * @param id
	 * @return
	 */
	int star_article(int id);

	/**
	 * 更新了类别
	 * 
	 * @param old_sort
	 * @param new_sort
	 * @return
	 */
	boolean updateSort(String old_sort, String new_sort);

	/**
	 * 删除分类和文章
	 * 
	 * @param sort
	 * @return
	 */
	boolean delelteSort(String sort);

}