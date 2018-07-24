package blog.utils;

import java.io.UnsupportedEncodingException;

public class StringUtils {

	public static boolean isEmpty(String str) {

		if (str == null || str.trim().equals(""))
			return true;
		return false;
	}

	public static String cutString(String str, int begin, int end) {

		if (str.length() < end || str.length() < begin)
			return str;

		return str.substring(begin, end);
	}

	// 解码 解决在URL传中文值出现的乱码问题
	public static String pareCode(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("ISO-8859-1"), "UTF-8");
	}

}
