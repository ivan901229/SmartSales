package com.lcpan.dao;

import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lcpan.bean.UserBean;

public class LoginDAOImpl implements LoginDAO {
	private static final String SQL = "SELECT * FROM login_user WHERE userID = ? and password = ?";
	Connection conn;

	public LoginDAOImpl() {
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			conn = ds.getConnection();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}

	public UserBean getUserId(String userId, String password) {
		UserBean user = null;
		try {
			PreparedStatement stmt = conn.prepareStatement(SQL);
			stmt.setString(1, userId);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			user = new UserBean();
			if (rs.next()) {
				user.setUserId(rs.getString("userID"));
				System.out.println("LoginDAO:±b¸¹±K½X¥¿½T");
			} else {
				System.out.println("LoginDAO:±b¸¹±K½X¿ù»~");
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return user;
	}

}