package blog.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import blog.utils.DBUtils;
import blog.utils.DateUtils;

public class VisitorDB {

	private static final Connection conn = C3P0Connection.getInstance().getConnection();

	/*
	 * 浏览者信息
	 */
	public static void visit(HttpServletRequest request) {

		String remoteAddr = request.getRemoteAddr();// 得到来访者的IP地址
		String localAddr = request.getLocalAddr(); // 获取WEB服务器的IP地址
		String remoteHost = request.getRemoteHost();
		String time = DateUtils.getFormatDate(new Date());

		String sql = "insert into t_visitor values(null,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, remoteAddr);
			ps.setString(2, time);
			ps.setString(3, localAddr);
			ps.setString(4, remoteHost);
			ps.executeUpdate();
			DBUtils.Close(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 全部浏览者
	 * 
	 * @return
	 */
	public static int totalVisit() {
		Connection conn = C3P0Connection.getInstance().getConnection();
		int result = 0;
		String sql = "select count(id) from t_visitor";
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

	/**
	 * 第几个浏览者
	 * 
	 * @return
	 */
	public static int totalMember() {
		Connection conn = C3P0Connection.getInstance().getConnection();
		int result = 0;
		String sql = "SELECT COUNT(DISTINCT(ip)) FROM t_visitor";
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
