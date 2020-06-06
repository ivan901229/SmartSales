package com.lcpan.bean;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

public class InventoryBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String productNo;
	private String productName;
	private String shelves;
	private String inWarehouse;
	private String totalAmount;
	private String category;
	private String price;
	private String rfid;
	
	
	public String getProductNo() { return productNo; }
	public String getProductName() {return productName;}
	public String getShelves() {return shelves;}
	public String getInWarehouse() {return inWarehouse;}
	public String getTotalAmount() {return totalAmount;}
	public String getCategory() {return category;}
	public String getPrice() {return price;}
	public String getRfid() {return rfid;}
	
	
	public void setProductNo(String productNo) { this.productNo = productNo; }
	public void setProductName(String productName) {this.productName = productName;}
	public void setShelves(String shelves) {this.shelves = shelves;}
	public void setInWarehouse(String inWarehouse) {this.inWarehouse = inWarehouse;}
	public void setTotalAmount(String totalAmount) {this.totalAmount = totalAmount;}
	public void setCategory(String category) {this.category = category;}
	public void setPrice(String price) {this.price = price;}
	public void setRfid(String rfid) {this.rfid = rfid;}
	
	
	
	
}
