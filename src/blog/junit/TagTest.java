package blog.junit;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import blog.dao.TagDao;
import blog.daoImpl.TagDaoImpl;
import blog.model.Tag;

public class TagTest {

	// @Test
	public void testdeleteTag() {
		TagDao dao = TagDaoImpl.getInstance();
		boolean result = dao.deleteTag(1, dao.DEFAULT_TAG);
		System.out.println(result);
	}

	@Test
	public void testAddTag() {

		TagDao dao = TagDaoImpl.getInstance();
		boolean result = dao.addTag(1, "中文啊");
		System.out.println(result);
	}

	// @Test
	public void testGetAllTag() {
		TagDao dao = TagDaoImpl.getInstance();
		List list = dao.getAllTag();
		Iterator it = list.iterator();

		while (it.hasNext()) {
			Tag tag = (Tag) it.next();
			System.out.println(tag.toString());
		}
	}

}
