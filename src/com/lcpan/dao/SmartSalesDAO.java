package com.lcpan.dao;

import java.io.InputStream;
import java.util.List;

import com.lcpan.bean.InventoryBean;
import com.lcpan.bean.MemberBean;

public interface SmartSalesDAO {
	public List<MemberBean> getAllMembers(int pageNo);    // �|���`��
	public int insertGetMember();
	public int getTotalPage();
	
	public void insertMember(String memberNo, String memberLevel, InputStream memberImageIn, String memberName, String memberBirth,
			 String memberGender, String memberPreferences, String memberPhone, String memberEmail, String memberPhotoURL);
	public void deleteMember(String memberNo);
	public MemberBean updateGetMemberNo(String memberNo);
	public void updateMember(String memberNo, String memberLevel, InputStream memberImageIn, String memberName, String memberBirth,
			 String memberGender, String memberPreferences, String memberPhone, String memberEmail, String memberPhotoURL);
	public List<MemberBean> searchMember(String keyword);
	public List<InventoryBean> getAllInventory();
	public List<MemberBean> getOnsiteMembers(); // ����-�{���|��
	public String getPhotoURL(String memberNo);
	public InventoryBean updateGetProductNo(String productNo);
	public void updateInventory(String productNo, String shelves, String inwareHouse, String totalAmount);
	public void newProduct(String productNo, String productName, String category, String price, String rfid);
	public InventoryBean updateGetProductNo1(String productNo);
	public void updateProduct(String productNo, String productName, String category);
	public void deleteProduct(String productNo);
	public List<InventoryBean> productList(); //�ӫ~�C��
//	public void addImage();

}
