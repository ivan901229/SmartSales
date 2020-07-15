package com.lcpan.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lcpan.bean.AnalysisBean;

public class AnalysisDAOImpl implements AnalysisDAO {
	private static final String FLOW = "select Date, male, female, ismember from flow_of_customer";
	private static final String SUM = "select productNo, SUM(amount) from sales_record GROUP by productNo";
	Connection conn;

	public AnalysisDAOImpl() {
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			conn = ds.getConnection();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}

	public List<AnalysisBean> flowOfCustomer() {
		List<AnalysisBean> flow = null;
		try {
			PreparedStatement stmt = conn.prepareStatement(FLOW);
			ResultSet rs = stmt.executeQuery();
			flow = new ArrayList<>();
			AnalysisBean flows = null;
			while (rs.next()) {
				flows = new AnalysisBean();
				flows.setDate(rs.getString("date"));
				System.out.print("1");
				flows.setMale(rs.getString("male"));
				flows.setFemale(rs.getString("female"));
				flows.setIsMember(rs.getString("ismember"));
				flow.add(flows);
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
		return flow;
	}
	
	public List<AnalysisBean> calProductSum() {
		List<AnalysisBean> sum = null;
		try {
			PreparedStatement stmt = conn.prepareStatement(SUM);
			ResultSet rs = stmt.executeQuery();
			sum = new ArrayList<>();
			AnalysisBean sumP = null;
			while (rs.next()) {
				sumP = new AnalysisBean();
				sumP.setProductNo(rs.getString("productNo"));
				System.out.print("1");
				sumP.setProductSum(rs.getString("SUM(amount)"));
				sum.add(sumP);
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
		return sum;
	}

}