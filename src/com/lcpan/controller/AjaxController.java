package com.lcpan.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcpan.dao.AjaxDAO;
import com.lcpan.dao.AjaxDAOImpl;

@WebServlet("/Ajax/*")
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getRequestURI();
		System.out.println(path);
		switch (path) {
		case "/SmartSales/Ajax/OnSiteMemberCount":  // �{���|���H��
			onSiteMemberCount(request, response);
			break;
		case "/SmartSales/Ajax/OnSiteMemberList":  // �{���|���M��
			onSiteMemberList(request, response);
			break; 
		case "/SmartSales/Ajax/scanNewRFID":  // �{���|���M��
			scanNewRFID(request, response);
			break;
		case "/SmartSales/Ajax/pay":  // ���b����
			pay(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	private void onSiteMemberCount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		AjaxDAO dao = new AjaxDAOImpl();
		String onSiteMemberCount = dao.getOnSiteMemberCount();
		out.println(onSiteMemberCount);

	}
	
	private void onSiteMemberList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		AjaxDAO dao = new AjaxDAOImpl();
		String rsString = dao.getOnSiteMembers();
//		System.out.println(rsString);
		out.println(rsString);
		
	}
	
	private void scanNewRFID(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		AjaxDAO dao = new AjaxDAOImpl();
		String newProductRFID = dao.scanNewRFID();
//		System.out.println(rsString);
		out.println(newProductRFID);
		
	}
	
	private void pay(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		AjaxDAO dao = new AjaxDAOImpl();
		String rsString = dao.pay();
//		System.out.println(rsString)
		out.println(rsString);
	}

}