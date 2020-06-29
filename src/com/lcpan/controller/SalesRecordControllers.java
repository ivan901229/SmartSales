package com.lcpan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.lcpan.bean.SalesRecordBean;
import com.lcpan.dao.SalesRecordDAO;
import com.lcpan.dao.SalesRecordDAOImpl;

@WebServlet("/salesrecord/*")
@MultipartConfig
public class SalesRecordControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getRequestURI();
		System.out.println(path);
		switch (path) {
		case "/SmartSales/salesrecord/GetAllSalesRecord":
			getAllSalesRecord(request, response);
			break; // 取得銷售紀錄
		case "/SmartSales/salesrecord/InsertGetSalesRecord":
			insertGetSalesRecord(request, response);
			break; // 讀取最大銷售編號
		case "/SmartSales/salesrecord/InsertSalesRecord":
			insertSalesRecord(request, response);
			break; // 手動新增銷售紀錄
		case "/SmartSales/salesrecord/PayPageInsertSalesRecord":
			payPageInsertSalesRecord(request, response);
			break; // 結帳新增銷售紀錄
		case "/SmartSales/salesrecord/DleteSalesRecord":
			deleteSalesRecord(request, response);
			break; // 銷售紀錄刪除
		case "/SmartSales/salesrecord/UpdateGetSalesRecordNo":
			updateGetSalesRecordNo(request, response);
			break; // 取得銷售紀錄更新
		case "/SmartSales/salesrecord/UpdateSalesRecord":
			updateSalesRecord(request, response);
			break; // 銷售紀錄更新
		case "/SmartSales/salesrecord/payPage":
			pay(request, response);
			break; // pay 頁面進servlet
		case "/SmartSales/salesrecord/DelPay":
			delPay(request, response);
			break;
		default :
			request.getRequestDispatcher("../member/GetOnsiteMembers").forward(request, response);
			break; // 錯誤網址-返回首頁
		}

	} // end of doGet()
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	} // end of doPost()

	private void getAllSalesRecord(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // 銷售紀錄總覽
		String checkLogIn = ""; // 判斷是否有log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			String currentPageNo = request.getParameter("currentpageno");
			int pageNo = 1;
			if(currentPageNo!=null){
				pageNo = Integer.parseInt(currentPageNo);
			}
//			System.out.println("yesssssssssssssssss");
			SalesRecordDAO dao = new SalesRecordDAOImpl();
			List<SalesRecordBean> salesrecords = dao.getAllSalesRecord(pageNo);
			SalesRecordDAO dao1 = new SalesRecordDAOImpl();
			int totalSalesPage = dao1.getTotalSalesPage();
			request.setAttribute("salesrecords", salesrecords);
			request.setAttribute("currentpageno", pageNo);  
			request.setAttribute("totalSalesPage", totalSalesPage);
			request.getRequestDispatcher("/sales_records.jsp").forward(request, response);
		}
		else
			response.sendRedirect("../relogin.jsp");
	}

	private void updateGetSalesRecordNo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkLogIn = ""; // 判斷是否有log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			String orderNumber = request.getParameter("orderNumber");
			System.out.print(orderNumber);
			SalesRecordDAO dao = new SalesRecordDAOImpl();
			SalesRecordBean salesrecords = dao.updateGetSalesRecordNo(orderNumber);
			request.setAttribute("salesrecords", salesrecords);
			request.getRequestDispatcher("/update_salesrecord.jsp").forward(request, response);
		}
		else
			response.sendRedirect("../relogin.jsp");

	}

	private void updateSalesRecord(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkLogIn = ""; // 判斷是否有log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
//		System.out.print("有");
			String orderNumber = request.getParameter("orderNumber");
			String date = request.getParameter("date");
			String productNo = request.getParameter("productNo");
			String amount = request.getParameter("amount");
			String price = request.getParameter("price");
			String discount = request.getParameter("discount");
			String totalPrice = request.getParameter("totalPrice");
			String gender = request.getParameter("gender");
			String number = request.getParameter("number");
			SalesRecordDAO dao = new SalesRecordDAOImpl();
			dao.updateSalesRecord(date, orderNumber, productNo, amount, price, discount, totalPrice, gender, number);
			response.sendRedirect("../salesrecord/GetAllSalesRecord");
		}
		else
			response.sendRedirect("../relogin.jsp");
	}

	private void deleteSalesRecord(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkLogIn = ""; // 判斷是否有log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			String orderNumber = request.getParameter("orderNumber");
			SalesRecordDAO dao = new SalesRecordDAOImpl();
			dao.deleteSalesRecord(orderNumber);
			response.sendRedirect("../salesrecord/GetAllSalesRecord");
		}
		else
			response.sendRedirect("../relogin.jsp");
	}

	private void insertGetSalesRecord(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkLogIn = ""; // 判斷是否有log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			SalesRecordDAO dao = new SalesRecordDAOImpl();
			long max = dao.insertGetSalesRecord();
			request.setAttribute("max", max);
			request.getRequestDispatcher("../insert_salesrecord.jsp").forward(request, response);
		}
		else
			response.sendRedirect("../relogin.jsp");

	}

	private void insertSalesRecord(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkLogIn = ""; // 判斷是否有log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String orderNumber = request.getParameter("orderNumber");
			String date = request.getParameter("date");
			String productNo = request.getParameter("productNo");
			String amount = request.getParameter("amount");
			String price = request.getParameter("price");
			String discount = request.getParameter("discount");
			String totalPrice = request.getParameter("totalPrice");
			String gender = request.getParameter("gender");
			String number = request.getParameter("number");
			SalesRecordDAO dao = new SalesRecordDAOImpl();
			dao.insertSalesRecord(date, orderNumber, productNo, amount, price,  discount,totalPrice, gender, number);
			response.sendRedirect("../salesrecord/GetAllSalesRecord");
		}
		else
			response.sendRedirect("../relogin.jsp");
	}
	
	private void payPageInsertSalesRecord(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkLogIn = ""; // 判斷是否有log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String memberNumber = request.getParameter("memberNumber");
			String memberName = request.getParameter("memberName");
			String memberDiscount = request.getParameter("memberDiscount");
			String memberGender = request.getParameter("memberGender");
			String totalPrice = request.getParameter("totalPrice");
			String payListJSON = request.getParameter("payListJSON");
			String orderNumber = "";
			System.out.println(memberNumber);
			System.out.println(memberName);
			System.out.println(memberDiscount);
			System.out.println(memberGender);
			System.out.println(totalPrice);
			System.out.println(payListJSON);
			SalesRecordDAO dao = new SalesRecordDAOImpl();
			orderNumber=String.valueOf(dao.getMaxOrderNumber());
			System.out.println(orderNumber);
//			dao.insertSalesRecord(date, orderNumber, productNo, amount, price, totalPrice, gender, number);
//			response.sendRedirect("../salesrecord/GetAllSalesRecord");
		}
		else
			response.sendRedirect("../relogin.jsp");
	}
	
	private void pay(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkLogIn = ""; // 檢查是否有log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			request.getRequestDispatcher("../pay.jsp").forward(request, response);
		}
		else
			response.sendRedirect("../relogin.jsp");
	}
	
	private void delPay(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkLogIn = ""; // 檢查是否有log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			String productNo = request.getParameter("productNo");
			System.out.println(productNo);
			SalesRecordDAO dao = new SalesRecordDAOImpl();
			dao.delPay(productNo);
			response.sendRedirect("../salesrecord/payPage");
		}
		else
			response.sendRedirect("../relogin.jsp");
	}

}
