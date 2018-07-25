package blog.junit;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import blog.dao.CommentDao;
import blog.daoImpl.CommentDaoImpl;
import blog.model.Comment;
import blog.utils.DateUtils;

public class CommentTest {

	@SuppressWarnings("rawtypes")
	@Test
	public void testGetComment() {

		CommentDao dao = CommentDaoImpl.getInstance();
		List cms = dao.getComment(1);

		Iterator it = cms.iterator();

		Comment cm;
		while (it.hasNext()) {
			cm = (Comment) it.next();
			System.out.println(cm.toString());
		}

	}

	// @Test
	public void testAddComment() {

		Comment cm = new Comment();
		cm.setId(0);
		cm.setArticle_id(1);
		cm.setContent("this is my frist create comment");
		cm.setNickname("Myblog");
		cm.setStar(5);
		cm.setTime(DateUtils.getFormatDate(new Date()));

		CommentDao dao = CommentDaoImpl.getInstance();
		// boolean b = dao.addComment(cm);
		// System.out.println(b);
	}
}
