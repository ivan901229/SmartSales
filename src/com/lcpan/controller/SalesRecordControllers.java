package com.lcpan.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.lang.Math;

import org.json.*;

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
			break; // ���o�P�����
		case "/SmartSales/salesrecord/InsertGetSalesRecord":
			insertGetSalesRecord(request, response);
			break; // Ū���̤j�P��s��
		case "/SmartSales/salesrecord/InsertSalesRecord":
			insertSalesRecord(request, response);
			break; // ��ʷs�W�P�����
		case "/SmartSales/salesrecord/PayPageInsertSalesRecord":
			payPageInsertSalesRecord(request, response);
			break; // ���b�s�W�P�����
		case "/SmartSales/salesrecord/DleteSalesRecord":
			deleteSalesRecord(request, response);
			break; // �P������R��
		case "/SmartSales/salesrecord/UpdateGetSalesRecordNo":
			updateGetSalesRecordNo(request, response);
			break; // ���o�P�������s
		case "/SmartSales/salesrecord/UpdateSalesRecord":
			updateSalesRecord(request, response);
			break; // �P�������s
		case "/SmartSales/salesrecord/payPage":
			pay(request, response);
			break; // pay �����iservlet
		case "/SmartSales/salesrecord/DelPay":
			delPay(request, response);
			break; //�R�����b�涵
		case "/SmartSales/salesrecord/CleanPayAll":
			cleanPayAll(request, response);
			break; //�M���㵧���b
		default :
			request.getRequestDispatcher("../member/GetOnsiteMembers").forward(request, response);
			break; // ���~���}-��^����
		}

	} // end of doGet()
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	} // end of doPost()

	private void getAllSalesRecord(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // �P������`��
		String checkLogIn = ""; // �P�_�O�_��log in
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
		String checkLogIn = ""; // �P�_�O�_��log in
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
		String checkLogIn = ""; // �P�_�O�_��log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
//		System.out.print("��");
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
		String checkLogIn = ""; // �P�_�O�_��log in
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
		String checkLogIn = ""; // �P�_�O�_��log in
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
		String checkLogIn = ""; // �P�_�O�_��log in
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
		String checkLogIn = ""; // �P�_�O�_��log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String memberNumber = request.getParameter("memberNumber");
			String memberDiscount = request.getParameter("memberDiscount");
			String memberGender = request.getParameter("memberGender");
//			String totalPrice = request.getParameter("totalPrice");
//			totalPrice = totalPrice.substring(3,totalPrice.length());
			String payListJSON = request.getParameter("payListJSON");
			
			String orderNumber = "";
//			System.out.println(memberNumber);
//			System.out.println(memberName);
//			System.out.println(memberDiscount);
//			System.out.println(memberGender);
//			System.out.println(totalPrice);
//			System.out.println(payListJSON);
//			System.out.println("��Ƥ��:"+rows);             // ��Ƥ��
			
			SalesRecordDAO dao = new SalesRecordDAOImpl();
			orderNumber=String.valueOf(dao.getMaxOrderNumber());
//			System.out.println(orderNumber);  �ӵ���ƪ�orderNumber
			JSONArray array = new JSONArray(payListJSON);
			for (int i = 0; i < array.length(); i++) {
		        JSONObject jsonObject = array.getJSONObject(i);
		        String productNo = jsonObject.getString("productNo");
		        String amount = jsonObject.getString("amount");
		        String price = jsonObject.getString("price");
		        if(memberDiscount.equals("")) {
		        	memberDiscount="1";
		        }
		        String totalPrice = String.valueOf(Math.round(Integer.valueOf(amount)*Integer.valueOf(price)*Float.valueOf(memberDiscount)));
		        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				String currentTime=(sdFormat.format(date).replaceAll("/","-"));
				SalesRecordDAO dao1 = new SalesRecordDAOImpl();
				dao1.payPageInsertSalesRecord(orderNumber, currentTime, productNo, amount, price, memberDiscount, totalPrice, memberGender, memberNumber);
			}
//			response.sendRedirect("../salesrecord/GetAllSalesRecord");
		}
		else
			response.sendRedirect("../relogin.jsp");
	}
	
	private void pay(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkLogIn = ""; // �ˬd�O�_��log in
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
		String checkLogIn = ""; // �ˬd�O�_��log in
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
	
	private void cleanPayAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkLogIn = ""; // �ˬd�O�_��log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			SalesRecordDAO dao = new SalesRecordDAOImpl();
			dao.cleanPayAll();
			System.out.println("Clean");
			HttpSession session = request.getSession();
			session.setAttribute("success", "Clean successful");
			response.sendRedirect("../salesrecord/payPage");
		}
		else
			response.sendRedirect("../relogin.jsp");
	}

}
