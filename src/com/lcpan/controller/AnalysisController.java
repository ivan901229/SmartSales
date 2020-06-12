package com.lcpan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcpan.bean.InventoryBean;
import com.lcpan.bean.SalesRecordBean;
import com.lcpan.dao.AjaxDAO;
import com.lcpan.dao.AjaxDAOImpl;
import com.lcpan.dao.SalesRecordDAO;
import com.lcpan.dao.SalesRecordDAOImpl;
import com.lcpan.dao.SmartSalesDAO;
import com.lcpan.dao.SmartSalesDAOImpl;

@WebServlet("/analysis/*")
public class AnalysisController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getRequestURI();
		System.out.println(path);
		switch (path) {
		case "/SmartSales/analysis/GetPurchaseRatio":getPurchaseRatio(request, response);   //取得購買比例
			break;
		case "/SmartSales/analysis/GetFootfall":getFootfall(request, response);   //取得客流量
		    break;
		default :
			request.getRequestDispatcher("../member/GetOnsiteMembers").forward(request, response);
			break; // 錯誤網址-返回首頁
		}
	}
	
	private void getPurchaseRatio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // 購買比例
		String checkLogIn = ""; // 判斷是否有log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			SalesRecordDAO dao = new SalesRecordDAOImpl();
			List<SalesRecordBean> salesrecords = dao.getAllSalesRecord(999999999);
			request.setAttribute("salesrecords", salesrecords);
			SmartSalesDAO dao1 = new SmartSalesDAOImpl();
			List<InventoryBean> products = dao1.productList();
			request.setAttribute("products", products);
			request.getRequestDispatcher("/sales_analysis.jsp").forward(request, response);
		}
		else
			response.sendRedirect("../relogin.jsp");
	}
	
	private void getFootfall(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // 購買比例
		String checkLogIn = ""; // 判斷是否有log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			SalesRecordDAO dao = new SalesRecordDAOImpl();
			List<SalesRecordBean> salesrecords = dao.getAllSalesRecord(999999999);
			request.setAttribute("salesrecords", salesrecords);
			request.getRequestDispatcher("/flow.jsp").forward(request, response);
		}
		else
			response.sendRedirect("../relogin.jsp");
	}
	
}