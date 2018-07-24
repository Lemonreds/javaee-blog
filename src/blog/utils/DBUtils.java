package blog.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

	/**
	 * 关闭数据库连接
	 */

	public static void Close(Statement st) throws SQLException {

		if (st != null)
			st.close();
	}

	public static void Close(Statement st, ResultSet rs) throws SQLException {

		if (st != null)
			st.close();
		if (rs != null)
			rs.close();
	}

	public static void Close(Connection conn, Statement st, ResultSet rs) throws SQLException {
		if (conn != null)
			conn.close();
		if (st != null)
			st.close();
		if (rs != null)
			rs.close();
	}
}
