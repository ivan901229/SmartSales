package com.lcpan.dao;



import java.sql.*;
import java.util.*;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.lcpan.bean.SalesRecordBean;

public class SalesRecordDAOImpl implements SalesRecordDAO {
//	private static final String GET_ALL = "SELECT * FROM member_overview";
	private static final String GET_MAX_ONUM = "SELECT MAX(orderNumber) max FROM sales_record";
	private static final String INSERT_SALES = "{call insert_salesrecord(?, ?, ?, ?, ?, ?, ?, ?)}";
	private static final String DEL_SALES = "DELETE FROM sales_record WHERE orderNumber= ?";
	private static final String Update_Get_SALES = "SELECT * FROM sales_record WHERE OrderNumber = ?";
	private static final String Update_SALES = "{call upd_salesrecord_all(?, ?, ?, ?, ?, ?, ?, ?)}";
	private static final String GET_ALL_SALES = "SELECT * FROM sales_record";  //取得全部資料

	Connection conn;

	public SalesRecordDAOImpl() {
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			conn = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<SalesRecordBean> getAllSalesRecord() { // 
		List<SalesRecordBean> salesrecords = null;
		try {
			PreparedStatement stmt = conn.prepareStatement(GET_ALL_SALES);
			ResultSet rs = stmt.executeQuery();
			salesrecords = new ArrayList<>();
			SalesRecordBean salesrecord = null;
			while (rs.next()) {
				salesrecord = new SalesRecordBean();
				salesrecord.setDate(rs.getString("date"));
				salesrecord.setOrderNumber(rs.getString("orderNumber"));
				salesrecord.setProductNo(rs.getString("productNo"));
				salesrecord.setAmount(rs.getString("amount"));
				salesrecord.setPrice(rs.getString("price"));
				salesrecord.setTotalPrice(rs.getString("totalPrice"));
				salesrecord.setGender(rs.getString("gender"));
				salesrecord.setNumber(rs.getString("number"));
				salesrecords.add(salesrecord);
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
		return salesrecords;
	}

	public SalesRecordBean updateGetSalesRecordNo(String orderNumber) {
		SalesRecordBean salesrecord = new SalesRecordBean();
		try {	
			PreparedStatement stmt = conn.prepareStatement(Update_Get_SALES);
			stmt.setString(1, orderNumber);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				salesrecord.setDate(rs.getString("date"));
				salesrecord.setOrderNumber(rs.getString("orderNumber"));
				salesrecord.setProductNo(rs.getString("productNo"));
				salesrecord.setAmount(rs.getString("amount"));
				salesrecord.setPrice(rs.getString("price"));
				salesrecord.setTotalPrice(rs.getString("totalPrice"));
				salesrecord.setGender(rs.getString("gender"));
				salesrecord.setNumber(rs.getString("number"));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return salesrecord;
	}

	public void updateSalesRecord(String date, String orderNumber, String productNo, String amount, String price,
			 String totalPrice, String gender, String number) {
		try {
			CallableStatement cstmt = conn.prepareCall(Update_SALES);
			cstmt.setString(1, orderNumber);
			cstmt.setString(2, date);
			cstmt.setString(3, productNo);
			cstmt.setString(4, amount);
			cstmt.setString(5, price);
			cstmt.setString(6, totalPrice);
			cstmt.setString(7, gender);
			cstmt.setString(8, number);
			cstmt.execute();
			System.out.println("Update Stored Procedure successful!");
			cstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Update Stored Procedure fail!");
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public void deleteSalesRecord(String orderNumber) {
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			conn = ds.getConnection();
			CallableStatement cstmt = conn.prepareCall(DEL_SALES);
			cstmt.setString(1, orderNumber);
			cstmt.execute();
			System.out.println("Delete Stored Procedure successful!");
			cstmt.close();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Delete Stored Procedure fail!");
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public int insertGetSalesRecord() {
		int max = 0;
		try {
			PreparedStatement stmt = conn.prepareStatement(GET_MAX_ONUM);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				max = Integer.valueOf(rs.getString("max")) + 1;
				System.out.println(max+1);
			}
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
		return max;
	}

	public void insertSalesRecord(String date, String orderNumber, String productNo, String amount, String price,
			 String totalPrice, String gender, String number) {
		try {
			CallableStatement cstmt = conn.prepareCall(INSERT_SALES);
			cstmt.setString(1, orderNumber);
			System.out.println(orderNumber);
			cstmt.setString(2, date);
			cstmt.setString(3, productNo);
			cstmt.setString(4, amount);
			cstmt.setString(5, price);
			cstmt.setString(6, totalPrice);
			cstmt.setString(7, gender);
			cstmt.setString(8, number);
			cstmt.execute();
			System.out.println("insert Stored Procedure successful!");
			cstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("insert Stored Procedure fail!");
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
