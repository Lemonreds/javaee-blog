package blog.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blog.dao.CommentDao;
import blog.db.C3P0Connection;
import blog.model.Comment;
import blog.utils.DBUtils;

public class CommentDaoImpl implements CommentDao {

	private Connection conn;

	private CommentDaoImpl() {
		conn = C3P0Connection.getInstance().getConnection();
	}

	private static CommentDao instance;

	public static CommentDao getInstance() {
		if (instance == null) {
			try {
				instance = new CommentDaoImpl();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see blog.daoImpl.CommentDao#deleteComment(int)
	 */
	@Override
	public boolean deleteComment(int comment_id) {

		String sql = "DELETE FROM t_comment WHERE id=" + comment_id;
		int result = 0;
		try {
			// 文章-1评论
			article_sub_comemnt(conn, comment_id);
			PreparedStatement ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
			DBUtils.Close(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result > 0;
	}

	/**
	 * 文章的评论-1
	 * 
	 * @param conn
	 * @param comment_id
	 */
	private void article_sub_comemnt(Connection conn, int comment_id) {

		String sql = "SELECT  article_id FROM t_comment WHERE id =" + comment_id;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int article_id = 0;
			if (rs.next()) {
				article_id = rs.getInt("article_id");
			}
			sql = "UPDATE t_article SET COMMENT=COMMENT - 1 WHERE id=" + article_id;
			conn.prepareStatement(sql).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see blog.daoImpl.CommentDao#addComment(blog.model.Comment)
	 */
	@Override
	public boolean addComment(Comment comment) {

		Connection conn = C3P0Connection.getInstance().getConnection();
		String sql = "INSERT  INTO t_comment VALUES(null,?,?,?,?,?,?)";
		int result = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, comment.getArticle_id());
			ps.setString(2, comment.getNickname());
			ps.setString(3, comment.getContent());
			ps.setString(4, comment.getTime());
			ps.setInt(5, comment.getStar());
			ps.setInt(6, comment.getDiss());
			result = ps.executeUpdate();

			// 文章加1评论
			sql = "UPDATE t_article SET COMMENT = COMMENT+1  WHERE id=" + comment.getArticle_id();
			PreparedStatement ps2 = conn.prepareStatement(sql);
			ps2.executeUpdate();

			DBUtils.Close(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see blog.daoImpl.CommentDao#getComment(int)
	 */
	@Override
	public List getComment(int article_id) {

		String sql = "SELECT * FROM t_comment WHERE article_id=? ORDER BY TIME";
		List list = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, article_id);
			ResultSet rs = ps.executeQuery();
			Comment cm;
			list = new ArrayList();
			while (rs.next()) {
				cm = new Comment();
				cm.setId(rs.getInt("id"));
				cm.setArticle_id(rs.getInt("article_id"));
				cm.setNickname(rs.getString("nickname"));
				cm.setTime(rs.getString("time"));
				cm.setStar(rs.getInt("star"));
				cm.setContent(rs.getString("content"));
				cm.setDiss(rs.getInt("diss"));
				list.add(cm);
			}
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see blog.daoImpl.CommentDao#star_diss(int, int)
	 */
	@Override
	public int star_diss(int id, int star_or_diss) {

		String sql;

		int result = -1;

		if (star_or_diss == Comment.STAR) {
			sql = "update t_comment set star=star+1 where id=" + id;
		} else if (star_or_diss == Comment.DISS) {
			sql = "update t_comment set diss=diss+1 where id=" + id;
		} else {
			return -1;
		}

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			// DBUtils.Close(conn, ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (star_or_diss == Comment.STAR) {
			sql = "SELECT star FROM t_comment WHERE id = " + id;
		} else if (star_or_diss == Comment.DISS) {
			sql = "SELECT diss FROM t_comment WHERE id = " + id;
		}

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
