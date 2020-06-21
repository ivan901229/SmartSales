package com.lcpan.bean;

import java.sql.Blob;

public class MemberBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String memberNo;
	private String memberLevel;
	private Blob imagePath;
	private String memberName;
	private String memberBirth;
	private String memberAge;
	private String memberGender;
	private String memberPreferences;
	private String memberPhone;
	private String memberEmail;
	private String memberOnsite;
	private String memberPhotoURL;
	private String memberDiscount;
	
	public String getMemberNo() { return memberNo; }
	public String getMemberLevel() { return memberLevel; }
	public Blob getImagePath() { return imagePath; }
	public String getMemberName() {	return memberName; }
	public String getMemberBirth() { return memberBirth; }
	public String getMemberAge() { return memberAge; }
	public String getMemberGender() { return memberGender; }
	public String getMemberPreferences() { return memberPreferences; }
	public String getMemberPhone() { return memberPhone; }
	public String getMemberEmail() { return memberEmail; }
	public String getMemberOnsite() { return memberOnsite; }
	public String getMemberPhotoURL() { return memberPhotoURL; }
	public String getMemberDiscount() { return memberDiscount; }
	
	public void setMemberNo(String memberNo) {this.memberNo = memberNo;}
	public void setMemberLevel(String memberLevel) {this.memberLevel = memberLevel;}
	public void setImagePath(Blob imagePath) {this.imagePath = imagePath;}
	public void setMemberName(String memberName) {this.memberName = memberName;}
	public void setMemberBirth(String memberBirth) {this.memberBirth = memberBirth;}
	public void setMemberAge(String memberAge) {this.memberAge = memberAge;}
	public void setMemberGender(String memberGender) {this.memberGender = memberGender;}
	public void setMemberPreferences(String memberPreferences) {this.memberPreferences = memberPreferences;}
	public void setMemberPhone(String memberPhone) {this.memberPhone = memberPhone;}
	public void setMemberEmail(String memberEmail) {this.memberEmail = memberEmail;}
	public void setMemberOnsite(String memberOnsite) {this.memberOnsite = memberOnsite;}
	public void setMemberPhotoURL(String memberPhotoURL) {this.memberPhotoURL = memberPhotoURL;}
	public void setMemberDiscount(String memberDiscount) {this.memberDiscount = memberDiscount;}
}