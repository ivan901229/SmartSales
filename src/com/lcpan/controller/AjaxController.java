package com.lcpan.controller;

import java.io.*;
import java.util.*;
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
		case "/SmartSales/Ajax/OnSiteMemberCount":  // 現場會員人數
			onSiteMemberCount(request, response);
			break;
		case "/SmartSales/Ajax/OnSiteMemberList":  // 現場會員清單
			onSiteMemberList(request, response);
			break; 
		case "/SmartSales/Ajax/scanNewRFID":  // 現場會員清單
			scanNewRFID(request, response);
			break;
		case "/SmartSales/Ajax/pay":  // 結帳測試
			pay(request, response);
			break;
		case "/SmartSales/Ajax/SalesRecordTotalPrice":  //當日營業額
			salesRecordTotalPrice(request, response);
			break;
		case "/SmartSales/Ajax/CameraOn_1":  //當日營業額
			cameraOn_1(request, response);
			break;
		case "/SmartSales/Ajax/CameraOff_1":  //當日營業額
			cameraOff_1(request, response);
			break;
		case "/SmartSales/Ajax/FacialScan":  //當日營業額
			facialScan(request, response);
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
	
	private void facialScan(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String memberNo = request.getParameter("memberNo");
		
//		Process process = null;
		String command = "bash /home/ivan901229/Desktop/facial_detection/facial_scan_train.sh "+memberNo;
		System.out.println(command);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		String path = "/home/ivan901229/Desktop/facial_detection/face_recognition_opencv_training/dataset/"+memberNo;   
////		System.out.println(path);
//		File dir = null;
//	      boolean bool = false;
//	      
//	      try {
//	         // returns pathnames for files and directory
//	    	  dir = new File(path);
//	         // create
//	         bool = dir.mkdir();
//	         // print
//	         System.out.println("Directory created? "+bool);
//	      } catch(Exception e) {                 
//	    	  // if any error occurs
//	         e.printStackTrace();
//	      }
//		try {
//			process = Runtime.getRuntime().exec(command);
//			BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
////			StringBuilder result = new StringBuilder();
//			String line = "";
//			while ((line = input.readLine()) != null) {
////		        result.append(line).append('\n');
//		        System.out.println(line);
//			}
////			System.out.println(result);
//			input.close();
//			process.waitFor();
//		} catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
	}
}