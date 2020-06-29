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
	private static final String GET_MAX_ONUM = "SELECT MAX(orderNumber) max FROM sales_record"; //取得最大筆
	private static final String INSERT_SALES = "{call insert_salesrecord(?, ?, ?, ?, ?, ?, ?, ?, ?)}"; 
	private static final String DEL_SALES = "DELETE FROM sales_record WHERE orderNumber= ?";
	private static final String Update_Get_SALES = "SELECT * FROM sales_record WHERE OrderNumber = ?"; //取得最大筆數
	private static final String Update_SALES = "{call upd_salesrecord_all(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
//	private static final String GET_ALL_SALES = "SELECT * FROM sales_record";  //取得全部資料
	private static final String GET_GENDER ="SELECT gender FROM member_overview"; //取得性別欄位
	private static final String DEL_PAY_INFO = "UPDATE product_information SET picked = 0 WHERE product_information.productNo = ?";
	
	private static int pagesize = 15;  //一頁顯示15筆

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

	public List<SalesRecordBean> getAllSalesRecord(int pageNo) { // 銷售紀錄總攬
		List<SalesRecordBean> salesrecords = null;
		int begin = (pageNo-1)*pagesize;  
		int end = pagesize; 
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT orderNumber,date,productNo,amount,price,discount,totalPrice,gender,number FROM sales_record LIMIT "+ begin+","+end);
			ResultSet rs = stmt.executeQuery();
			salesrecords = new ArrayList<>();
			SalesRecordBean salesrecord = null;
			while (rs.next()) {
				salesrecord = new SalesRecordBean();
				salesrecord.setOrderNumber(rs.getString("orderNumber"));
				salesrecord.setDate(rs.getString("date"));
				salesrecord.setProductNo(rs.getString("productNo"));
				salesrecord.setAmount(rs.getString("amount"));
				salesrecord.setPrice(rs.getString("price")); 
				salesrecord.setDiscount(rs.getString("discount")); 
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

	public int getTotalSalesPage() {
		// TODO Auto-generated method stub
		int totalCount = 0;
		int totalPage = 0;
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(orderNumber) orderNumber FROM sales_record");
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				totalCount = Integer.valueOf(rs.getString("orderNumber"));
                totalPage = (totalCount-1)/pagesize+1;
			}
			stmt.close();
		}  catch (SQLException e) {
			System.out.println("getTotalSalesPage fail!");
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
		
		return totalPage;
	}
	
	
	
	
	public SalesRecordBean updateGetSalesRecordNo(String orderNumber) { //更新銷售紀錄 -取值
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
				salesrecord.setDiscount(rs.getString("discount")); 
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
			 String discount, String totalPrice, String gender, String number) {  //更新銷售紀錄
		try {
			CallableStatement cstmt = conn.prepareCall(Update_SALES);
			cstmt.setString(1, orderNumber);
			cstmt.setString(2, date);
			cstmt.setString(3, productNo);
			cstmt.setString(4, amount);
			cstmt.setString(5, price);
			cstmt.setString(6, discount);
			cstmt.setString(7, totalPrice);
			cstmt.setString(8, gender);
			cstmt.setString(9, number);
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

	public void deleteSalesRecord(String orderNumber) { //刪除銷售紀錄
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

	public int insertGetSalesRecord() { //新增銷售紀錄 -取最大編號再+1
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
			String discount, String totalPrice, String gender, String number) { //新增銷售紀錄
		try {
			CallableStatement cstmt = conn.prepareCall(INSERT_SALES);
			cstmt.setString(1, orderNumber);
			System.out.println(orderNumber);
			cstmt.setString(2, date);
			cstmt.setString(3, productNo);
			cstmt.setString(4, amount);
			cstmt.setString(5, price);
			cstmt.setString(6, discount);
			cstmt.setString(7, totalPrice);
			cstmt.setString(8, gender);
			cstmt.setString(9, number);
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
	public List<SalesRecordBean> getPurchaseRatio() { //取得性別欄位
		List<SalesRecordBean> salesrecords = null;
		try {
			PreparedStatement stmt = conn.prepareStatement(GET_GENDER);
			ResultSet rs = stmt.executeQuery();
			salesrecords = new ArrayList<>();
			SalesRecordBean salesrecord = null;
			while (rs.next()) {
				salesrecord = new SalesRecordBean();
				salesrecord.setGender(rs.getString("gender"));
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
	
	
	public boolean delPay(String productNo) {
		boolean delStatus = false;
		try {
			PreparedStatement stmt = conn.prepareStatement(DEL_PAY_INFO);
			stmt.setString(1, productNo);
			stmt.execute();
			stmt.close();
			delStatus = true;
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return delStatus;
	}
	
}
