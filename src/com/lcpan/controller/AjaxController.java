package com.lcpan.controller;

import java.io.*;
import java.util.List;

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
		case "/SmartSales/Ajax/SalesRecordTotalPrice":  //�����~�B
			salesRecordTotalPrice(request, response);
			break;
		case "/SmartSales/Ajax/CameraOn_1":  //�����~�B
			cameraOn_1(request, response);
			break;
		case "/SmartSales/Ajax/CameraOff_1":  //�����~�B
			cameraOff_1(request, response);
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
	
	private void salesRecordTotalPrice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 

		PrintWriter out = response.getWriter();
		AjaxDAO dao = new AjaxDAOImpl();
		List<String> salesRecordTotalPrice = dao.getSalesRecordTotalPrice();
		out.println(salesRecordTotalPrice);

	}
	
	private void onSiteMemberList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
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
	
	private void cameraOn_1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Runtime.getRuntime().exec("bash /home/ivan901229/Desktop/camera_on/ngrok_login_camera_on.sh");
		
//		Process process = null;
//       List<String> processList = new ArrayList<String>();
//        Runtime.getRuntime().exec("D:\\Java\\workspace\\test.bat");
//        process = Runtime.getRuntime().exec("D:\\Java\\workspace\\test.bat");
//        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        StringBuilder result = new StringBuilder();
//        String line = "";
//        while ((line = input.readLine()) != null) {
//            result.append(line).append('\n');
//        }
//        System.out.println(result);
        
//		PrintWriter out = response.getWriter();
//		AjaxDAO dao = new AjaxDAOImpl();
//		String rsString = dao.pay();
//		System.out.println(rsString)
//		out.println(rsString);
	}
	
	private void cameraOff_1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Runtime.getRuntime().exec("bash /home/ivan901229/Desktop/camera_off/ngrok_login_camera_off.sh");
//		PrintWriter out = response.getWriter();
//		AjaxDAO dao = new AjaxDAOImpl();
//		String rsString = dao.pay();
//		System.out.println(rsString)
//		out.println(rsString);
	}

}