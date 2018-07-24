package blog.junit;

import org.junit.Test;

import net.sf.json.JSONObject;

public class JsonTest {

	@Test
	public void jsonTest1() {

		JSONObject jo = new JSONObject();
		jo.put("anme", "1233");
		jo.put("yee", 123);
		System.out.println(jo.toString());

	}

}
