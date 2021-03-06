package com.lcpan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class AjaxDAOImpl implements AjaxDAO {
	private static final String Get_ONSITE_COUNT = "SELECT COUNT(site) FROM member_overview WHERE site='T'";
	private static final String Get_ONSITE_MEMBERS = "SELECT number,memberLevel,name,birthday,age,gender,preferences,phone,email,site,photoURL FROM member_overview WHERE site = 'T'";
	private static final String Get_RFID_TMP = "SELECT rfid FROM rfid_tmp";
	private static final String Get_PAY_INFO = "SELECT * FROM product_information where picked != 0";

//	private static final String CLEAN_PAY_INFO = "UPDATE product_information SET picked = 0 WHERE product_information.picked != 0";
	private static final String Get_TOTALPRICE_NOW = "SELECT SUM(totalPrice),photoURL FROM sales_record AS s, member_overview AS m where s.date >= date(now()) and s.date < DATE_ADD(date(now()),INTERVAL 1 DAY) and m.number = '-1'";
//	private static final String CLEAN_INVENTORY= "UPDATE inventory_list, product_information SET inventory_list.shelves = inventory_list.shelves - product_information.picked WHERE inventory_list.productNo = product_information.productNo AND inventory_list.shelves > 0";

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
	
	
	public ArrayList<String> getSalesRecordTotalPrice() {						
		String salesRecordTotalPrice1="";
		ArrayList<String> salesRecordTotalPrice=new ArrayList<String>();
		try {
			PreparedStatement stmt = conn.prepareStatement(Get_TOTALPRICE_NOW);
			ResultSet rs= stmt.executeQuery();
			while (rs.next()) {
				salesRecordTotalPrice1 = rs.getString("SUM(totalPrice)");
				salesRecordTotalPrice.add(salesRecordTotalPrice1);
				salesRecordTotalPrice1 = rs.getString("photoURL");
				salesRecordTotalPrice.add(salesRecordTotalPrice1);
				//System.out.print(salesRecordTotalPrice);
//				System.out.println(salesRecordTotalPrice);
				if(salesRecordTotalPrice.get(0) == null) {
					salesRecordTotalPrice.set(0, "0");
				}
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