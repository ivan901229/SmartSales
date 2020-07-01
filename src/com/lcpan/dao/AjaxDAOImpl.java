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
	private static final String Get_RFID_TMP = "SELECT rfid FROM rfid_tmp";
	private static final String Get_PAY_INFO = "SELECT * FROM product_information where picked != 0";
	
	private static final String CLEAN_PAY_INFO = "UPDATE product_information SET picked = 0 WHERE product_information.picked != 0";
	private static final String Get_TOTALPRICE_NOW = "SELECT SUM(totalPrice) FROM sales_record where date >= date(now()) and date < DATE_ADD(date(now()),INTERVAL 1 DAY)";
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
	
	
	public String getSalesRecordTotalPrice() {						
		String salesRecordTotalPrice="";
		try {
			PreparedStatement stmt = conn.prepareStatement(Get_TOTALPRICE_NOW);
			ResultSet rs= stmt.executeQuery();
			while (rs.next()) {
				salesRecordTotalPrice = rs.getString("SUM(totalPrice)");
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
		return salesRecordTotalPrice;
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
	
	public String scanNewRFID() {                            // 掃描新產品的RFID
		String newProductRFID = "";
		try {
			PreparedStatement stmt = conn.prepareStatement(Get_RFID_TMP);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				newProductRFID = rs.getString("rfid");
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
		return newProductRFID;
	}
	
	
	public String pay() {                            // 結帳
		String rsString = "";
		try {
			PreparedStatement stmt = conn.prepareStatement(Get_PAY_INFO);
			ResultSet rs = stmt.executeQuery();
			rsString = JSONTools.resultSetToJSON(rs).toString();
			//System.out.print(rsString);
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