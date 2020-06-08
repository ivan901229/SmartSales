package com.lcpan.bean;



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
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getShelves() {
		return shelves;
	}
	public void setShelves(String shelves) {
		this.shelves = shelves;
	}
	public String getInWarehouse() {
		return inWarehouse;
	}
	public void setInWarehouse(String inWarehouse) {
		this.inWarehouse = inWarehouse;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getRfid() {
		return rfid;
	}
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
	
}
