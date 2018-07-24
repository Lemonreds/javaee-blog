package blog.dao;

import java.util.List;

import blog.model.Comment;

public interface CommentDao {

	/**
	 * 删除评论
	 * 
	 * @param comment_id
	 * @return
	 */
	boolean deleteComment(int comment_id);

	/**
	 * 新的评论
	 * 
	 * @param comment
	 * @return
	 */
	boolean addComment(Comment comment);

	/**
	 * 获取某个评论
	 * 
	 * @param article_id
	 * @return
	 */
	List getComment(int article_id);

	/**
	 * 点赞或者鄙视
	 * 
	 * @param id
	 * @param star_or_diss
	 * @return
	 */
	int star_diss(int id, int star_or_diss);

}