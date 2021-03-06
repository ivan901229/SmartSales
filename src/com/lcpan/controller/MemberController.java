package com.lcpan.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.lcpan.bean.MemberBean;
import com.lcpan.dao.SmartSalesDAO;
import com.lcpan.dao.SmartSalesDAOImpl;

@WebServlet("/member/*")
@MultipartConfig
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getRequestURI();
		System.out.println(path);
		// getAllEmps(request, response);
		switch (path) {
		case "/SmartSales/member/GetAllMembers":
			getAllMembers(request, response);
			break; // 會員總覽OK
		case "/SmartSales/member/InsertGetMember":
			insertGetMember(request, response);
			break; // 讀取最大會員編號OK
		case "/SmartSales/member/InsertMember":
			insertMember(request, response);
			break; // 新增會員OK
		case "/SmartSales/member/DeleteMember":
			deleteMember(request, response);
			break; // 會員刪除OK
		case "/SmartSales/member/UpdateGetMemberNo":
			updateGetMemberNo(request, response);
			break; // 取得會員OK
		case "/SmartSales/member/UpdateMember":
			updateMember(request, response);
			break; // 會員更新OK
		case "/SmartSales/member/SearchMember":
			searchMember(request, response);
			break; // 搜尋會員OK
		case "/SmartSales/member/GetOnsiteMembers":
			getOnsiteMembers(request, response);
			break; // 首頁-現場會員OK
		case "/SmartSales/member/SearchMemberPhone":
			searchMemberPhone(request, response);
			break; // 搜尋會員OK
		case "/SmartSales/member/SearchMemberFace":
			searchMemberFace(request, response);
			break; // 結帳搜尋人臉辨識
		case "/SmartSales/member/GetAllMembersDiamond":
			getAllMembersDiamond(request, response);
			break;
		case "/SmartSales/member/GetAllMembersGold":
			getAllMembersGold(request, response);
			break;
		case "/SmartSales/member/GetAllMembersSilver":
			getAllMembersSilver(request, response);
			break;
//		case "/SmartSales/member/AddImage": addImage(request, response); break;   				  // 上傳會員照片
		default :
			getOnsiteMembers(request, response);
			break; // 錯誤網址-返回首頁
		}

	} // end of doGet()

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	} // end of doPost()

	private void getAllMembers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // 會員總覽
		String checkLogIn = ""; // 判斷是否有log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			String currentPageNo = request.getParameter("currentpageno");
			int pageNo = 1;
			if(currentPageNo!=null){
				pageNo = Integer.parseInt(currentPageNo);
			}
			SmartSalesDAO dao = new SmartSalesDAOImpl();
			List<MemberBean> members = dao.getAllMembers(pageNo);
			// list --> json
			SmartSalesDAO dao1 = new SmartSalesDAOImpl();
			int totalPage = dao1.getTotalPage();
			request.setAttribute("members", members); 
			request.setAttribute("currentpageno", pageNo);  
			request.setAttribute("totalPage", totalPage);
			request.getRequestDispatcher("/member_list.jsp").forward(request, response);
		}
		else
			response.sendRedirect("../relogin.jsp");
	}

	private void insertGetMember(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkLogIn = ""; // 判斷是否有log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			SmartSalesDAO dao = new SmartSalesDAOImpl();
			int max = dao.insertGetMember();
			request.setAttribute("max", max);
			request.getRequestDispatcher("../insert_member.jsp").forward(request, response);
		}
		else
			response.sendRedirect("../relogin.jsp");
	}

	private void insertMember(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkLogIn = ""; // 判斷是否有log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String memberNo = request.getParameter("memberNo");
			String memberLevel = request.getParameter("memberLevel");
			Part memberImage = request.getPart("memberImage");
			InputStream memberImageIn = null;

			String header = memberImage.getHeader("Content-Disposition");
			System.out.println(header);
			// form-data; name="photo"; filename="pandas.jpg"
			// form-data; name="photo"; filename="D:\images\pandas.jpg"
			int slashIdx = header.lastIndexOf("\\");
			int dotIdx = header.lastIndexOf(".");
//		System.out.println("slashIdx:" + slashIdx);
//		System.out.println("dotIdx:" + dotIdx);
			String filename;
			String extension = "";
			if (dotIdx != -1) {
				extension = header.substring(dotIdx, header.length() - 1);
				if (slashIdx != -1)
					filename = header.substring(slashIdx + 1, header.length() - 1);
				else {
					int idx = header.indexOf("filename");
					filename = header.substring(idx + 10, header.length() - 1);
				}

//		System.out.println(filename);
//		System.out.println(extension);
				if (!"".equals(filename)) { // has input
					InputStream in = memberImage.getInputStream();
//					OutputStream out = new FileOutputStream(
//							"D:/Java/workspace/SmartSales/WebContent/assets/images/member_photo/" + memberNo
//									+ extension);                                //Windows
					OutputStream out = new FileOutputStream(
							"/opt/tomcat/webapps/member_photo/assets/images/member_photo/" + memberNo
									+ extension);                                //Ubuntu
					System.out.println(extension);
//					extension = ".jpg";
					byte[] buf = new byte[512];
					int length;
					while ((length = in.read(buf)) != -1) {
//				System.out.println(length);
						out.write(buf, 0, length);
					}
					in.close();
					out.close();
					memberImageIn = memberImage.getInputStream();
				}
			} else {
//				memberImageIn = new FileInputStream(
//						"D:\\Java\\workspace\\SmartSales\\WebContent\\assets\\images\\member_photo\\default_head.jpg"); //Windows
				memberImageIn = new FileInputStream(
						"/opt/tomcat/webapps/member_photo/assets/images/member_photo/default_head.jpg");             //Ubuntu
				extension = ".jpg";
//				OutputStream out = new FileOutputStream(
//						"D:/Java/workspace/SmartSales/WebContent/assets/images/member_photo/" + memberNo + extension); //Windows
				OutputStream out = new FileOutputStream(
						"/opt/tomcat/webapps/member_photo/assets/images/member_photo/" + memberNo + extension);        //Ubuntu
				System.out.println(memberNo);
				System.out.println(extension);
				byte[] buf = new byte[512];
				int length;
				while ((length = memberImageIn.read(buf)) != -1) {
					out.write(buf, 0, length);
				}

				out.close();
				System.out.println(memberImageIn);
//				memberImageIn = new FileInputStream(
//						"D:\\Java\\workspace\\SmartSales\\WebContent\\assets\\images\\member_photo\\default_head.jpg"); //Windows
				memberImageIn = new FileInputStream(
						"/opt/tomcat/webapps/member_photo/assets/images/member_photo/default_head.jpg");                //Ubuntu
			}
			String memberName = request.getParameter("memberName");
			String memberBirth = request.getParameter("memberBirth");
			String memberGender = request.getParameter("memberGender");
			String memberPreferences = request.getParameter("memberPreferences");
			String memberPhone = request.getParameter("memberPhone");
			String memberEmail = request.getParameter("memberEmail");
			String memberPhotoURL = "/member_photo/" + memberNo + extension;
			System.out.println(memberPhotoURL);
			SmartSalesDAO dao = new SmartSalesDAOImpl();
			dao.insertMember(memberNo, memberLevel, memberImageIn, memberName, memberBirth, memberGender,
					memberPreferences, memberPhone, memberEmail, memberPhotoURL);
			response.sendRedirect("../member/GetAllMembers?" + getRandomWord());
		}
		else
			response.sendRedirect("../relogin.jsp");
	}

	private void deleteMember(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkLogIn = ""; // 判斷是否有log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			String memberNo = request.getParameter("memberNo");
			SmartSalesDAO dao = new SmartSalesDAOImpl();
			String photoURL = dao.getPhotoURL(memberNo);
			File memberImage = new File("/opt/tomcat/webapps/member_photo/assets/images"+photoURL); //ubuntu
//			System.out.println(memberImage);
//			System.out.println(memberImage.exists());
			if (memberImage.exists()) {
				memberImage.delete();
			}

			SmartSalesDAO dao1 = new SmartSalesDAOImpl();
			dao1.deleteMember(memberNo);
			response.sendRedirect("../member/GetAllMembers?" + getRandomWord());
		}
		else
			response.sendRedirect("../relogin.jsp");
	}

	private void updateGetMemberNo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkLogIn = ""; // 判斷是否有log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			String memberNo = request.getParameter("memberNo");
			System.out.print(memberNo);
			SmartSalesDAO dao = new SmartSalesDAOImpl();
			MemberBean member = dao.updateGetMemberNo(memberNo);
			request.setAttribute("member", member);
			request.getRequestDispatcher("/update_member.jsp").forward(request, response);
		}
		else
			response.sendRedirect("../relogin.jsp");
	}

	private void updateMember(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkLogIn = ""; // 判斷是否有log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String memberNo = request.getParameter("memberNo");
			String memberLevel = request.getParameter("memberLevel");
			Part memberImage = request.getPart("memberImage");
			InputStream memberImageIn = null;

			String header = memberImage.getHeader("Content-Disposition");
			System.out.println(header);
			// form-data; name="photo"; filename="pandas.jpg"
			// form-data; name="photo"; filename="D:\images\pandas.jpg"
			int slashIdx = header.lastIndexOf("\\");
			int dotIdx = header.lastIndexOf(".");
//		System.out.println("slashIdx:" + slashIdx);
//		System.out.println("dotIdx:" + dotIdx);
			String filename;
			String extension = "";
			String memberPhotoURL="";  // 上傳路徑用
			
			SmartSalesDAO dao = new SmartSalesDAOImpl();    // 確認資料庫的照片路徑
			String photoURL = dao.getPhotoURL(memberNo);
			File memberImageURL = new File("/opt/tomcat/webapps/member_photo/assets/images"+photoURL); //ubuntu
//			File memberImageURL = new File(photoURL); //windows
//			System.out.println("memberImageURL:"+memberImageURL);
//			System.out.println(memberImage.exists());
			if (dotIdx != -1) { // 判斷是否有上傳檔案
				extension = header.substring(dotIdx, header.length() - 1);
				if (slashIdx != -1)
					filename = header.substring(slashIdx + 1, header.length() - 1);
				else {
					int idx = header.indexOf("filename");
					filename = header.substring(idx + 10, header.length() - 1);
				}

//		System.out.println(filename);
//		System.out.println(extension);
				
				
				if (!"".equals(filename)) { // has input
					if (memberImageURL.exists()) { //如果有上傳檔案，將原本的刪除
						memberImageURL.delete();
					}
					InputStream in = memberImage.getInputStream();
//					OutputStream out = new FileOutputStream(
//							"D:/Java/workspace/SmartSales/WebContent/assets/images/member_photo/" + memberNo
//									+ extension);                              //windows
					OutputStream out = new FileOutputStream(
							"/opt/tomcat/webapps/member_photo/assets/images/member_photo/" + memberNo
									+ extension);   
					byte[] buf = new byte[512];
					int length;
					while ((length = in.read(buf)) != -1) {
//					System.out.println(length);
						out.write(buf, 0, length);
					}
					in.close();
					out.close();
					memberImageIn = memberImage.getInputStream();
					System.out.println(memberImageIn);
					memberPhotoURL = "/member_photo/" + memberNo + extension;
				}
			} else {
				System.out.println(memberImageURL);
//				memberImageIn = new FileInputStream(
//						"D:\\Java\\workspace\\SmartSales\\WebContent\\assets\\images\\member_photo\\" + memberNo
//								+ ".jpg");									   //windows
				memberImageIn = new FileInputStream(memberImageURL);    
				memberPhotoURL = photoURL;
			}
			String memberName = request.getParameter("memberName");
			String memberBirth = request.getParameter("memberBirth");
			String memberGender = request.getParameter("memberGender");
			String memberPreferences = request.getParameter("memberPreferences");
			String memberPhone = request.getParameter("memberPhone");
			String memberEmail = request.getParameter("memberEmail");

			SmartSalesDAO dao1 = new SmartSalesDAOImpl();
			dao1.updateMember(memberNo, memberLevel, memberImageIn, memberName, memberBirth, memberGender,
					memberPreferences, memberPhone, memberEmail, memberPhotoURL);
			response.sendRedirect("../member/GetAllMembers?" + getRandomWord());
		}
		else
			response.sendRedirect("../relogin.jsp");
	}

	private void getOnsiteMembers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // 首頁-現場會員
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String checkLogIn = ""; // 判斷是否有log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		System.out.println(checkLogIn);
		if (checkLogIn.equals("true")) {
			SmartSalesDAO dao = new SmartSalesDAOImpl();
			List<MemberBean> members = dao.getOnsiteMembers();
			request.setAttribute("members", members);
			HttpSession session=request.getSession();
			session.removeAttribute("success");
			request.getRequestDispatcher("/home.jsp").forward(request, response);
		}
		else
			response.sendRedirect("../relogin.jsp");
	}

	private void searchMember(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkLogIn = ""; // 判斷是否有log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			String keyword = request.getParameter("keyword");
			SmartSalesDAO dao = new SmartSalesDAOImpl();
			List<MemberBean> members = dao.searchMember(keyword);
			request.setAttribute("members", members);
			System.out.println(keyword);
			request.getRequestDispatcher("/search_list.jsp").forward(request, response);
		}
		else
			response.sendRedirect("../relogin.jsp");
	}

//	private void addImage(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		System.out.println("test");
//		SmartSalesDAO dao = new SmartSalesDAOImpl();
//		dao.addImage();
//	}

	private String getRandomWord() {
		int z;
		StringBuilder sb = new StringBuilder();
		int i;
		for (i = 0; i < 6; i++) {
			z = (int) ((Math.random() * 7) % 3);

			if (z == 1) { // 放數字
				sb.append((int) ((Math.random() * 10) + 48));
			} else if (z == 2) { // 放大寫英文
				sb.append((char) (((Math.random() * 26) + 65)));
			} else {// 放小寫英文
				sb.append(((char) ((Math.random() * 26) + 97)));
			}
		}
		return sb.toString();
	}
	
	private void searchMemberPhone(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkLogIn = ""; // 判斷是否有log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			String memberPhone = request.getParameter("memberPhone");
			System.out.println(memberPhone);
			SmartSalesDAO dao = new SmartSalesDAOImpl();
			MemberBean member = dao.searchMemberPhone(memberPhone);
			if(member!=null) {
				request.setAttribute("check", "ok");
				request.setAttribute("member", member);
			}
			else request.setAttribute("check", "null");
			request.getRequestDispatcher("../pay.jsp").forward(request, response);
		}
		else
			response.sendRedirect("../relogin.jsp");
	}
	private void searchMemberFace(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  	PrintWriter out = response.getWriter();
			SmartSalesDAO dao = new SmartSalesDAOImpl();
			MemberBean member = dao.searchMemberFace();
			if(member!=null) {
				request.setAttribute("check", "ok");
				request.setAttribute("member", member);
			    out.println("1");
			}
//			else request.setAttribute("check", "null");
//			request.getRequestDispatcher("../pay.jsp").forward(request, response);
			else {
				out.println("0");
			}
	}


private void getAllMembersDiamond(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException { // 會員總覽
	String checkLogIn = ""; // 判斷是否有log in
	request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
	checkLogIn = (String) request.getAttribute("checkLogIn");
	if (checkLogIn.equals("true")) {
		String currentPageNo = request.getParameter("currentpageno");
		int pageNo = 1;
		if(currentPageNo!=null){
			pageNo = Integer.parseInt(currentPageNo);
		}
		SmartSalesDAO dao = new SmartSalesDAOImpl();
		List<MemberBean> members = dao.getAllMembersDiamond(pageNo);
		// list --> json
		SmartSalesDAO dao1 = new SmartSalesDAOImpl();
		int totalPage = dao1.getTotalPageDiamond();
		request.setAttribute("members", members); 
		request.setAttribute("currentpageno", pageNo);  
		request.setAttribute("totalPage", totalPage);
		request.getRequestDispatcher("/member_list_diamond.jsp").forward(request, response);
	}
	else
		response.sendRedirect("../relogin.jsp");
}

private void getAllMembersGold(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException { // 會員總覽
	String checkLogIn = ""; // 判斷是否有log in
	request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
	checkLogIn = (String) request.getAttribute("checkLogIn");
	if (checkLogIn.equals("true")) {
		String currentPageNo = request.getParameter("currentpageno");
		int pageNo = 1;
		if(currentPageNo!=null){
			pageNo = Integer.parseInt(currentPageNo);
		}
		SmartSalesDAO dao = new SmartSalesDAOImpl();
		List<MemberBean> members = dao.getAllMembersGold(pageNo);
		// list --> json
		SmartSalesDAO dao1 = new SmartSalesDAOImpl();
		int totalPage = dao1.getTotalPageGold();
		request.setAttribute("members", members); 
		request.setAttribute("currentpageno", pageNo);  
		request.setAttribute("totalPage", totalPage);
		request.getRequestDispatcher("/member_list_gold.jsp").forward(request, response);
	}
	else
		response.sendRedirect("../relogin.jsp");
}

private void getAllMembersSilver(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException { // 會員總覽
	String checkLogIn = ""; // 判斷是否有log in
	request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
	checkLogIn = (String) request.getAttribute("checkLogIn");
	if (checkLogIn.equals("true")) {
		String currentPageNo = request.getParameter("currentpageno");
		int pageNo = 1;
		if(currentPageNo!=null){
			pageNo = Integer.parseInt(currentPageNo);
		}
		SmartSalesDAO dao = new SmartSalesDAOImpl();
		List<MemberBean> members = dao.getAllMembersSilver(pageNo);
		// list --> json
		SmartSalesDAO dao1 = new SmartSalesDAOImpl();
		int totalPage = dao1.getTotalPageSilver();
		request.setAttribute("members", members); 
		request.setAttribute("currentpageno", pageNo);  
		request.setAttribute("totalPage", totalPage);
		request.getRequestDispatcher("/member_list_silver.jsp").forward(request, response);
	}
	else
		response.sendRedirect("../relogin.jsp");
}
}
