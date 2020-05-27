package com.lcpan.controller;

import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.lcpan.bean.InventoryBean;
import com.lcpan.dao.SmartSalesDAO;
import com.lcpan.dao.SmartSalesDAOImpl;

@WebServlet("/inventory/*")
@MultipartConfig
public class InventoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getRequestURI();
		System.out.println(path);
		switch (path) {
		case "/SmartSales/inventory/GetAllInventory":
			getAllInventory(request, response);
			break; // �w�s�`��
		case "/SmartSales/inventory/UpdateGetProductNo":
			updateGetProductNo(request, response);
			break; // ���o�ӫ~(�i�f)
		case "/SmartSales/inventory/UpdateInventory":
			updateInventory(request, response);
			break; // �w�s��s
		case "/SmartSales/inventory/NewProduct":
			newProduct(request, response);
			break; // �s�W�ӫ~
		case "/SmartSales/inventory/ProductList":
			productList(request, response);
			break;
		case "/SmartSales/inventory/UpdateGetProductNo1":
			updateGetProductNo1(request, response);
			break; // ���o�ӫ~(�ק�ӫ~��)
		case "/SmartSales/inventory/UpdateProduct":
			updateProduct(request, response);
			break; // �w�s��s
		case "/SmartSales/inventory/DeleteProduct":
			deleteProduct(request, response);
			break; // �R���ӫ~

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void getAllInventory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkLogIn = ""; // �P�_�O�_��log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			SmartSalesDAO dao = new SmartSalesDAOImpl();
			List<InventoryBean> inventories = dao.getAllInventory();
			// list --> json
			request.setAttribute("inventories", inventories);
			request.getRequestDispatcher("../storage.jsp").forward(request, response);
		}
		else
			response.sendRedirect("../relogin.jsp");
	}

	private void updateGetProductNo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkLogIn = ""; // �P�_�O�_��log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			String productNo = request.getParameter("productNo");
			System.out.println(productNo);
			SmartSalesDAO dao = new SmartSalesDAOImpl();
			InventoryBean inventory = dao.updateGetProductNo(productNo);
			request.setAttribute("inventory", inventory);
			request.getRequestDispatcher("../update_inventory.jsp").forward(request, response);
		}
		else
			response.sendRedirect("../relogin.jsp");
	}

	private void updateInventory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkLogIn = ""; // �P�_�O�_��log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String productNo = request.getParameter("productNo");
			String shelves = request.getParameter("shelves");
			String inwareHouse = request.getParameter("inwareHouse");
			String totalAmount = request.getParameter("totalAmount");
//		String memberOnsite= request.getParameter("memberOnsite");
			SmartSalesDAO dao = new SmartSalesDAOImpl();
			dao.updateInventory(productNo, shelves, inwareHouse, totalAmount);
			response.sendRedirect("../inventory/GetAllInventory");
		}
		else
			response.sendRedirect("../relogin.jsp");
	}

	private void newProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkLogIn = ""; // �P�_�O�_��log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String productNo = request.getParameter("productNo");
			String productName = request.getParameter("productName");
			String category = request.getParameter("category");
			SmartSalesDAO dao = new SmartSalesDAOImpl();
			dao.newProduct(productNo, productName, category);
			response.sendRedirect("../inventory/GetAllInventory");
		}
		else
			response.sendRedirect("../relogin.jsp");
	}

	private void productList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkLogIn = ""; // �P�_�O�_��log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			SmartSalesDAO dao = new SmartSalesDAOImpl();
			List<InventoryBean> products = dao.productList();
			// list --> json
			request.setAttribute("products", products);
			request.getRequestDispatcher("../product_list.jsp").forward(request, response);
		}
		else
			response.sendRedirect("../relogin.jsp");
	}

	private void updateGetProductNo1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkLogIn = ""; // �P�_�O�_��log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			String productNo = request.getParameter("productNo");
			System.out.println(productNo);
			SmartSalesDAO dao = new SmartSalesDAOImpl();
			InventoryBean inventory = dao.updateGetProductNo1(productNo);
			request.setAttribute("inventory", inventory);
			request.getRequestDispatcher("../update_product.jsp").forward(request, response);
		}
		else
			response.sendRedirect("../relogin.jsp");
	}

	private void updateProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkLogIn = ""; // �P�_�O�_��log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String productNo = request.getParameter("productNo");
			System.out.println(productNo);
			String productName = request.getParameter("productName");
			System.out.println(productName);
			String category = request.getParameter("category");
			System.out.println(category);
			SmartSalesDAO dao = new SmartSalesDAOImpl();
			dao.updateProduct(productNo, productName, category);
			response.sendRedirect("../inventory/ProductList");
		}
		else
			response.sendRedirect("../relogin.jsp");
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkLogIn = ""; // �P�_�O�_��log in
		request.getRequestDispatcher("/LogIn/CheckLogIn").include(request, response);
		checkLogIn = (String) request.getAttribute("checkLogIn");
		if (checkLogIn.equals("true")) {
			String productNo = request.getParameter("productNo");
			SmartSalesDAO dao = new SmartSalesDAOImpl();
			dao.deleteProduct(productNo);
			response.sendRedirect("../inventory/ProductList");
		}
		else
			response.sendRedirect("../relogin.jsp");
	}
}
