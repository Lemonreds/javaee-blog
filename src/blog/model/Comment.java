package blog.model;

import java.text.ParseException;
import java.util.Date;

import blog.utils.DateUtils;

public class Comment {

	public final static int STAR = 0;
	public final static int DISS = 1;

	private int id;
	private int article_id;
	private String nickname;
	private String content;
	private String time;
	private int star;
	private int diss;

	@Override
	public String toString() {
		return "Comment [id=" + id + ", parent_id=" + article_id + ", nickname=" + nickname + ", content=" + content
				+ ", time=" + time + ", star=" + star + ", diss=" + diss + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArticle_id() {
		return article_id;
	}

	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public int getDiss() {
		return diss;
	}

	public void setDiss(int diss) {
		this.diss = diss;
	}

}
