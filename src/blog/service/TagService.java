package blog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import blog.dao.TagDao;
import blog.daoImpl.TagDaoImpl;
import blog.model.Article;
import blog.model.Tag;
import blog.utils.ArticleUtils;
import blog.utils.StringUtils;

/**
 * TO web
 */
public class TagService {

	private TagDao dao;

	private static TagService instance;

	private TagService() {
		dao = TagDaoImpl.getInstance();
	}

	public static final TagService getInstance() {
		if (instance == null) {
			try {
				instance = new TagService();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public List getTagById(String id) {
		return dao.getTagByColumn("id", id);
	}

	public List getAllTag() {
		return dao.getAllTag();
	}

	public int getTagCount() {
		return dao.getAllTag().size();
	}

	/**
	 * 获取标签和它所标记的文章
	 * 
	 * @return
	 */
	public Map getTagAndArticle(String tag_name) {

		ArticleService as = ArticleService.getInstance();
		Map map = new HashMap();

		List<Tag> tag_list;
		if (tag_name.equals("all") || StringUtils.isEmpty(tag_name)) {
			// 获取所有不重复的标签
			tag_list = dao.getAllTag();
		} else {
			// 获取这个标签
			tag_list = dao.getTagByColumn("tag", tag_name);
		}

		// 有这个标签的文章
		List<Article> article_list = null;
		// 获取这个标签的所有文章 id
		for (Tag tag : tag_list) {
			List<Tag> list = dao.getTagByColumn("tag", tag.getTag());
			article_list = new ArrayList();
			// 查询所有文章 id 放在article_list
			for (Tag tag_all : list) {
				// 这个集合只有一个元素
				List<Article> result = as.getArticle("id", String.valueOf(tag_all.getId()));
				article_list.add(ArticleUtils.cutTime(result.get(0)));
			}
			// 返回标签的内容+标签标记的所有文章集合
			map.put(tag.getTag(), article_list);
		}
		return map;
	}
}
