package blog.model;

import java.text.ParseException;
import java.util.Date;

import blog.utils.DateUtils;
import blog.utils.StringUtils;

public class Article implements Comparable {

	private int id;
	private String title;
	private String author;
	private String sort;
	private String time;
	private int star;
	private int comment;
	private int visit;
	private String content;

	public Article() {

	}

	public Article(int id, String title, String author, String sort, String time, int star, int comment, int visit,
			String content) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.sort = sort;
		this.time = time;
		this.star = star;
		this.comment = comment;
		this.visit = visit;
		this.content = content;
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Article) {
			Article article = (Article) o;

			Date this_date = null;
			Date other_date = null;
			try {
				this_date = DateUtils.getDate(this.time);
				other_date = DateUtils.getDate(article.getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return -this_date.compareTo(other_date);
		}
		return 0;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", author=" + author + ", sort=" + sort + ", time=" + time
				+ ", star=" + star + ", comment=" + comment + ", visit=" + visit + ", content=" + content + "]";
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
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

	public int getComment() {
		return comment;
	}

	public void setComment(int comment) {
		this.comment = comment;
	}

	public int getVisit() {
		return visit;
	}

	public void setVisit(int visit) {
		this.visit = visit;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
