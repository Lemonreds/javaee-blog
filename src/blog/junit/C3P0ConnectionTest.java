package blog.junit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import blog.db.C3P0Connection;

public class C3P0ConnectionTest {

	@Test
	public void testGetConnection() throws SQLException {
		Connection conn = C3P0Connection.getInstance().getConnection();
		System.out.print(conn);
	}

	@Test
	public void testControlDB() throws SQLException {
		Connection conn = C3P0Connection.getInstance().getConnection();
		String sql = "select * from t_user ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getInt("user_id"));
			System.out.println(rs.getString("user_name"));
			System.out.println(rs.getString("user_password"));
		}
	}

}
