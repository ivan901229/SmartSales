package com.lcpan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class AjaxDAOImpl implements AjaxDAO {
	private static final String Get_ONSITE_COUNT = "SELECT COUNT(site) FROM member_overview WHERE site='T'";
	private static final String Get_ONSITE_MEMBERS = "SELECT number,memberLevel,name,birthday,age,gender,preferences,phone,email,site,photoURL FROM member_overview WHERE site = 'T'";

	Connection conn;
	
	public AjaxDAOImpl() {                                   
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			conn = ds.getConnection();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getOnSiteMemberCount() {						// 現場會員人數
		String onSiteMemberCount="";
		try {
			PreparedStatement stmt = conn.prepareStatement(Get_ONSITE_COUNT);
			ResultSet rs= stmt.executeQuery();
			while (rs.next()) {
				onSiteMemberCount = rs.getString("COUNT(site)");
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
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
		return onSiteMemberCount;
	}
	
	public String getOnSiteMembers() {                            // 現場會員清單
		String rsString = "";
		try {
			PreparedStatement stmt = conn.prepareStatement(Get_ONSITE_MEMBERS);
			ResultSet rs = stmt.executeQuery();
			
			rsString = JSONTools.resultSetToJSON(rs).toString();
			System.out.println("JSON file create successful!");
			
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
		return rsString;
	}
}