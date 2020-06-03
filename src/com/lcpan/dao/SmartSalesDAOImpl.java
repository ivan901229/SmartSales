package com.lcpan.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.lcpan.bean.InventoryBean;
import com.lcpan.bean.MemberBean;

public class SmartSalesDAOImpl implements SmartSalesDAO {
	
//	private static final String GET_ALL = "SELECT number,memberLevel,name,birthday,age,gender,preferences,phone,email,site,photoURL FROM member_overview LIMIT 1 , 5";
	private static final String GET_MAX_NUM = "SELECT MAX(number) max FROM member_overview";
	private static final String INSERT_MEMBER = "{call insert_member(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
	private static final String CAL_AGE = "{call cal_age}";
	private static final String DEL_MEMBER = "{call delete_member(?)}";
	private static final String Update_Get_MEMBER = "SELECT * FROM member_overview WHERE number = ?";
	private static final String Update_MEMBER = "{call upd_member_all(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
	private static final String Get_Mems_By_Key = "SELECT number,memberLevel,name,birthday,age,gender,preferences,phone,email,site,photoURL FROM member_overview WHERE name Like ? or phone Like ? or memberLevel Like ? or birthday Like ? or age Like ? or gender Like ? or email Like ?";
	private static final String Get_ALL_Inv = "select i.productNo, p.productName, i.shelves, i.InWarehouse, i.totalAmount from inventory_list as i inner join product_information as p on i.productNo = p.productNo";
	private static final String Get_ONSITE_MEMBERS = "SELECT number,memberLevel,name,birthday,age,gender,preferences,phone,email,site,photoURL FROM member_overview WHERE site = 'T'";
	private static final String GET_PHOTO_URL = "SELECT photoURL FROM member_overview WHERE number = ?";
//	private static final String Add_Image = "insert into p(id, photo) values(?,?)";
//	private static final String Add_Image = "UPDATE member_overview SET photo = ? WHERE number = ?";
	private static final String Update_Get_INVENTORY = "SELECT * FROM inventory_list WHERE productNo = ?";
	private static final String Update_INVENTORY = "{call upd_inventory_all(?, ?, ?, ?)}"; //更新產品
	private static final String NEW_PRODUCT = "insert into product_information values(?, ?, ?)"; //新增商品
	private static final String PRODUCT_LIST = "SELECT * FROM product_information";
	private static final String INSERT_PRODUCTTOSTORAGE = "insert into inventory_list values(?, 0, 0, 0)"; //將新增商品加入庫存，庫存=0
	private static final String DEL_PRODUCT = "delete from product_information where productNo= ?";
	private static final String DEL_PRODUCTINVEN = "delete from inventory_list where productNo= ?";
	private static final String Update_Get_PRODUCT = "SELECT * FROM product_information WHERE productNo = ?";
	private static final String Update_PRODUCT = "{call upd_product_information(?, ?, ?)}";
	private static int pagesize = 11;
	Connection conn;

	public SmartSalesDAOImpl() {
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

	public List<MemberBean> getAllMembers(int pageNo) { // 會員總覽
		List<MemberBean> members = null;
		int begin = (pageNo-1)*pagesize;
		int end = pagesize;
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT number,memberLevel,name,birthday,age,gender,preferences,phone,email,site,photoURL FROM member_overview LIMIT "+ begin+","+end);
			ResultSet rs = stmt.executeQuery();
			members = new ArrayList<>();
			MemberBean member = null;
			while (rs.next()) {
//				System.out.println(rs.getString("number"));
				if(!rs.getString("number").equals("-1")) {
					member = new MemberBean();
					member.setMemberNo(rs.getString("number"));
					member.setMemberLevel(rs.getString("memberLevel"));
//					member.setImagePath(rs.getString("photo"));
					member.setMemberName(rs.getString("name"));
					member.setMemberBirth(rs.getString("birthday"));
					member.setMemberAge(rs.getString("age"));
					member.setMemberGender(rs.getString("gender"));
					member.setMemberPreferences(rs.getString("preferences"));
					member.setMemberPhone(rs.getString("phone"));
					member.setMemberEmail(rs.getString("email"));
					member.setMemberOnsite(rs.getString("site"));
					member.setMemberPhotoURL(rs.getString("photoURL"));
					members.add(member);
				}
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
		return members;
	}
	
	public int getTotalPage() {
		// TODO Auto-generated method stub
		int totalCount = 0;
		int totalPage = 0;
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(number) number FROM member_overview");
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				totalCount = Integer.valueOf(rs.getString("number"));
				totalPage = (totalCount-1)/pagesize+1;
			}
			stmt.close();
		}  catch (SQLException e) {
			System.out.println("NOOOOOOOOOOOOOOOOOOO");
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


	public int insertGetMember() {
		int max = 0;
		try {
			PreparedStatement stmt = conn.prepareStatement(GET_MAX_NUM);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				max = Integer.valueOf(rs.getString("max")) + 1;
//				System.out.println(max+1);
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

	public void insertMember(String memberNo, String memberLevel, InputStream memberImageIn, String memberName,
			String memberBirth, String memberGender, String memberPreferences, String memberPhone, String memberEmail,
			String memberPhotoURL) {
		try {
			CallableStatement cstmt = conn.prepareCall(INSERT_MEMBER);
			cstmt.setString(1, memberNo);
			cstmt.setString(2, memberLevel);
			cstmt.setBinaryStream(3, memberImageIn, memberImageIn.available());
			cstmt.setString(4, memberName);
			cstmt.setString(5, memberBirth);
			cstmt.setString(6, memberGender);
			cstmt.setString(7, memberPreferences);
			cstmt.setString(8, memberPhone);
			cstmt.setString(9, memberEmail);
			cstmt.setString(10, memberPhotoURL);
			cstmt.execute();
			cstmt = conn.prepareCall(CAL_AGE);
			cstmt.execute();
			cstmt.close();
			System.out.println("Insert Stored Procedure successful!");
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			System.out.println("Insert Stored Procedure fail!");
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public void deleteMember(String memberNo) {
		try {
			CallableStatement cstmt = conn.prepareCall(DEL_MEMBER);
			cstmt.setString(1, memberNo);
			cstmt.execute();
			System.out.println("Delete Stored Procedure successful!");
			cstmt.close();
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

	public MemberBean updateGetMemberNo(String memberNo) {
		MemberBean member = new MemberBean();
		try {
			PreparedStatement stmt = conn.prepareStatement(Update_Get_MEMBER);
			stmt.setString(1, memberNo);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				member.setMemberNo(rs.getString("number"));
				member.setMemberLevel(rs.getString("memberLevel"));
//				member.setImagePath(rs.getString("photo"));
				member.setMemberName(rs.getString("name"));
				member.setMemberBirth(rs.getString("birthday"));
				member.setMemberAge(rs.getString("age"));
				member.setMemberGender(rs.getString("gender"));
				member.setMemberPreferences(rs.getString("preferences"));
				member.setMemberPhone(rs.getString("phone"));
				member.setMemberEmail(rs.getString("email"));
				member.setMemberOnsite(rs.getString("site"));
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
		return member;
	}

	public void updateMember(String memberNo, String memberLevel, InputStream memberImageIn, String memberName,
			String memberBirth, String memberGender, String memberPreferences, String memberPhone, String memberEmail,
			String memberPhotoURL) {
		try {
			CallableStatement cstmt = conn.prepareCall(Update_MEMBER);
			cstmt.setString(1, memberNo);
			cstmt.setString(2, memberLevel);
			cstmt.setBinaryStream(3, memberImageIn, memberImageIn.available());
			cstmt.setString(4, memberName);
			cstmt.setString(5, memberBirth);
			cstmt.setString(6, memberGender);
			cstmt.setString(7, memberPreferences);
			cstmt.setString(8, memberPhone);
			cstmt.setString(9, memberEmail);
			cstmt.setString(10, memberPhotoURL);
			cstmt.execute();
			cstmt = conn.prepareCall(CAL_AGE);
			cstmt.execute();
			System.out.println("Update Stored Procedure successful!");
			cstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Update Stored Procedure fail!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public List<InventoryBean> getAllInventory() {
		List<InventoryBean> inventories = null;
		try {
			PreparedStatement stmt = conn.prepareStatement(Get_ALL_Inv);
			ResultSet rs = stmt.executeQuery();
			inventories = new ArrayList<>();
			InventoryBean inventory = null;
			while (rs.next()) {
				inventory = new InventoryBean();
				inventory.setProductNo(rs.getString("ProductNo"));
				inventory.setProductName(rs.getString("ProductName"));
				inventory.setShelves(rs.getString("Shelves"));
				inventory.setInWarehouse(rs.getString("InWarehouse"));
				inventory.setTotalAmount(rs.getString("TotalAmount"));
				inventories.add(inventory);
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
		return inventories;
	}

	public List<MemberBean> getOnsiteMembers() { // 首頁-現場會員
		List<MemberBean> members = null;
		try {
			PreparedStatement stmt = conn.prepareStatement(Get_ONSITE_MEMBERS);
			ResultSet rs = stmt.executeQuery();
			members = new ArrayList<>();
			MemberBean member = null;
			while (rs.next()) {
				member = new MemberBean();
				member.setMemberNo(rs.getString("number"));
				member.setMemberLevel(rs.getString("memberLevel"));
//				member.setImagePath(rs.getBlob("photo"));
				member.setMemberName(rs.getString("name"));
				member.setMemberBirth(rs.getString("birthday"));
				member.setMemberAge(rs.getString("age"));
				member.setMemberGender(rs.getString("gender"));
				member.setMemberPreferences(rs.getString("preferences"));
				member.setMemberPhone(rs.getString("phone"));
				member.setMemberEmail(rs.getString("email"));
				member.setMemberOnsite(rs.getString("site"));
				members.add(member);
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
		return members;
	}

	public List<MemberBean> searchMember(String keyword) {
		List<MemberBean> members = new ArrayList<>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(Get_Mems_By_Key);
			for (int i = 1; i < 8; i++) {
				pstmt.setString(i, "%" + keyword + "%");
			}
			ResultSet rs = pstmt.executeQuery();
			MemberBean member = null;
			while (rs.next()) {
				member = new MemberBean();
				member.setMemberNo(rs.getString("number"));
				member.setMemberLevel(rs.getString("memberLevel"));
//				member.setImagePath(rs.getString("photo"));
				member.setMemberName(rs.getString("name"));
				member.setMemberBirth(rs.getString("birthday"));
				member.setMemberAge(rs.getString("age"));
				member.setMemberGender(rs.getString("gender"));
				member.setMemberPreferences(rs.getString("preferences"));
				member.setMemberPhone(rs.getString("phone"));
				member.setMemberEmail(rs.getString("email"));
				member.setMemberOnsite(rs.getString("site"));
				members.add(member);
			}
			pstmt.close();
			System.out.println("Query Stored Procedure successful!");
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
		return members;
	}
	
	public String getPhotoURL(String memberNo) {
		String photoURL = null;
		try {
			PreparedStatement stmt = conn.prepareStatement(GET_PHOTO_URL);
			stmt.setString(1, memberNo);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				photoURL = rs.getString("photoURL");
				System.out.println(photoURL);
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
		return photoURL;
	}
	
	public InventoryBean updateGetProductNo(String productNo) {
		InventoryBean inventory = new InventoryBean();
		try {
			PreparedStatement stmt = conn.prepareStatement(Update_Get_INVENTORY);
			stmt.setString(1, productNo);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				inventory.setProductNo(rs.getString("productNo"));
				inventory.setShelves(rs.getString("shelves"));
				inventory.setInWarehouse(rs.getString("inwareHouse"));
				inventory.setTotalAmount(rs.getString("totalAmount"));
			}
			stmt.close();
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
		return inventory;
	}
	
	public void updateInventory(String productNo, String shelves, String inwareHouse, String totalAmount) {
		try {
			CallableStatement cstmt = conn.prepareCall(Update_INVENTORY);
			cstmt.setString(1, productNo);
			System.out.println(productNo);
			System.out.println(shelves);
			System.out.println(inwareHouse);
			System.out.println(totalAmount);
			cstmt.setString(2, shelves);
			cstmt.setString(3, inwareHouse);
			cstmt.setString(4, totalAmount);
			cstmt.execute();
			System.out.println("Update Stored Procedure successful!");
			cstmt.close();
		}  catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Update Stored Procedure fail!");
		}  finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public void newProduct(String productNo, String productName, String category) {
		try {
			PreparedStatement stmt = conn.prepareStatement(NEW_PRODUCT);
			stmt.setString(1, productNo);
			stmt.setString(2, productName);
			stmt.setString(3, category);
			stmt.execute();
			stmt = conn.prepareStatement(INSERT_PRODUCTTOSTORAGE);
			stmt.setString(1, productNo);
			stmt.execute();
			stmt.close();
			System.out.println("Insert successful!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Insert fail!");
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public List<InventoryBean> productList() {
		List<InventoryBean> products = null;
		try {
			PreparedStatement stmt = conn.prepareStatement(PRODUCT_LIST);
			ResultSet rs = stmt.executeQuery();
			products = new ArrayList<>();
			InventoryBean product = null;
			while (rs.next()) {
				product = new InventoryBean();
				product.setProductNo(rs.getString("productNo"));
				product.setProductName(rs.getString("productName"));
				product.setCategory(rs.getString("category"));
				products.add(product);
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
		return products;
	}
	
	public void deleteProduct(String productNo) {
		try {
			
			PreparedStatement stmt = conn.prepareStatement(DEL_PRODUCTINVEN);
			stmt.setString(1, productNo);
			stmt.execute();
			stmt = conn.prepareStatement(DEL_PRODUCT);
			stmt.setString(1, productNo);
			stmt.execute();
			System.out.println("Delete Product successful!");
			stmt.close();
		}  catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Delete Product fail!");
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public InventoryBean updateGetProductNo1(String productNo) {
		InventoryBean inventory = new InventoryBean();
		try {
			PreparedStatement stmt = conn.prepareStatement(Update_Get_PRODUCT);
			stmt.setString(1, productNo);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				inventory.setProductNo(rs.getString("productNo"));
				inventory.setProductName(rs.getString("productName"));
				inventory.setCategory(rs.getString("category"));
				
			}
			stmt.close();
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
		return inventory;
	}
	
	public void updateProduct(String productNo, String productName, String category) {
		try {
			CallableStatement cstmt = conn.prepareCall(Update_PRODUCT);
			cstmt.setString(1, productNo);
			System.out.println(productNo);
			cstmt.setString(2, productName);
			System.out.println(productName);
			cstmt.setString(3, category);
			System.out.println(category);
			cstmt.execute();
			System.out.println("updateProduct Stored Procedure successful!");
			cstmt.close();
		}  catch (SQLException e) {
			e.printStackTrace();
			System.out.println("updateProduct Stored Procedure fail!");
		}  finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	
	
	
	

//	public void addImage() {
//		try
//		{
//			System.out.println("test1");
//			PreparedStatement stmt = conn.prepareStatement(Add_Image);
//			
//			System.out.println("test2");
//			InputStream is = null;
//			System.out.println("test3");
//			is = new FileInputStream("D:/servlet/upload/3.jpg");
//			stmt.setBinaryStream(1, is, is.available());
//			stmt.setString(2,"1");
//			System.out.println("test4");
//			// 方法說明：PreparedStatement.setBinaryStream(int parameterIndex, InputStream x, int
//			// length)
//			stmt.executeUpdate();
//			System.out.println("圖片新增成功！");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}

}
