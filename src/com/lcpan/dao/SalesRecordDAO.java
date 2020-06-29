package com.lcpan.dao;

import java.util.List;

import com.lcpan.bean.SalesRecordBean;
public interface SalesRecordDAO {
	public List<SalesRecordBean> getAllSalesRecord(int pageNo);
	
	public int getTotalSalesPage();
	
	public SalesRecordBean updateGetSalesRecordNo(String orderNumber);
	public void updateSalesRecord(String date, String orderNumber, String productNo, String amount, String price,
			String discount, String totalPrice, String gender, String number);
	
	public void deleteSalesRecord(String orderNumber);
	
	public int insertGetSalesRecord();
	public void insertSalesRecord(String date, String orderNumber, String productNo, String amount, String price,
			String discount, String totalPrice, String gender, String number);
	public boolean delPay(String productNo);
	public long getMaxOrderNumber();
	public void payPageInsertSalesRecord(String orderNumber,String currentTime,String productNo,String amount, 
			String price, String memberDiscount, String totalPrice, String memberGender, String memberNumber);
	
}

