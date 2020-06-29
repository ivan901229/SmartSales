package com.lcpan.bean;

public class SalesRecordBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String date;
	private String orderNumber;
	private String productNo;
	private String amount;
	private String price;
	private String discount;
	private String totalPrice;
	private String gender;
	private String number;

	public String getDate() { return date; }
	public String getOrderNumber() { return orderNumber; }
	public String getProductNo() { return productNo; }
	public String getAmount() { return amount; }
	public String getPrice() { return price; }
	public String getDiscount() { return discount; }
	public String getTotalPrice() { return totalPrice; }
	public String getGender() { return gender; }
	public String getNumber() { return number; }
	
	public void setDate(String date) { this.date = date; }
	public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
	public void setProductNo(String productNo) { this.productNo = productNo; }
	public void setAmount(String amount) { this.amount = amount; }
	public void setPrice(String price) { this.price = price; }
	public void setDiscount(String discount) { this.discount = discount; }
	public void setTotalPrice(String totalPrice) { this.totalPrice = totalPrice; }
	public void setGender(String gender){ this.gender = gender; }
	public void setNumber(String number) { this.number = number; }
}