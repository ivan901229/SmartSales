package com.lcpan.bean;

public class AnalysisBean implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String date;
	private String male;
	private String female;
	private String isMember;
	private String productSum;
	private String productNo;
	
	
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getProductSum() {
		return productSum;
	}
	public void setProductSum(String productSum) {
		this.productSum = productSum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDate() {return date;}
	public String getMale() {return male;}
	public String getFemale() {return female;}
	public String getIsMember() {return isMember;}
	
	public void setDate(String date) {this.date = date;}
	public void setMale(String male) {this.male = male;}
	public void setFemale(String female) {this.female = female;}
	public void setIsMember(String isMember) {this.isMember = isMember;}
}
