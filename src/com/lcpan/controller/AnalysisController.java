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
import com.lcpan.bean.AnalysisBean;
import com.lcpan.dao.AjaxDAO;
import com.lcpan.dao.AjaxDAOImpl;
import com.lcpan.dao.AnalysisDAO;
import com.lcpan.dao.AnalysisDAOImpl;
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
		case "/SmartSales/analysis/GetPurchaseRatio":getPurchaseRatio(request, response);   //���o�ʶR���
			break;
		case "/SmartSales/analysis/GetFootfall":getFootfall(request, response);   //���o�Ȭy�q
		    break;
		case "/SmartSales/analysis/live":
			request.getRequestDispatcher("../live_streaming.jsp").forward(request, response);
			break; // �v����y
		default :
			request.getRequestDispatcher("../member/GetOnsiteMembers").forward(request, response);
			break; // ���~���}-��^����
		case "/SmartSales/analysis/FlowOfCustomer":flowOfcustomer(request, response);   //���o�ʶR���
		    break; // �H�y����	
		
		}
	}
	
	private void getPurchaseRatio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // �ʶR���
		String checkLogIn = ""; // �P�_�O�_��log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			AnalysisDAO dao = new AnalysisDAOImpl();
			List<AnalysisBean> sum = dao.calProductSum();
			request.setAttribute("sum", sum);
			request.getRequestDispatcher("/sales_analysis.jsp").forward(request, response);
		}
		else
			response.sendRedirect("../relogin.jsp");
	}
	
	private void getFootfall(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // �ʶR���
		String checkLogIn = ""; // �P�_�O�_��log in
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
	
	private void flowOfcustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // �H�y����
		String checkLogIn = ""; // �P�_�O�_��log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			AnalysisDAO dao = new AnalysisDAOImpl();
			List<AnalysisBean> flow = dao.flowOfCustomer();
			request.setAttribute("flow", flow);
			request.getRequestDispatcher("/flow.jsp").forward(request, response);
		}
		else
			response.sendRedirect("../relogin.jsp");
	}
	
}