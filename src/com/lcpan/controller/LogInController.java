package com.lcpan.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcpan.bean.UserBean;
import com.lcpan.dao.LoginDAO;
import com.lcpan.dao.LoginDAOImpl;


@WebServlet("/LogIn/*")
public class LogInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String path = request.getRequestURI();
		System.out.println("LogInController:"+path);
		// getAllEmps(request, response);
		switch (path) {
		case "/SmartSales/LogIn/LogIn":
			logIn(request, response);
			break; // �n�J
		case "/SmartSales/LogIn/LogOut":
			logOut(request, response);
			break; // �n�X
		
		default:
			 HttpSession session=request.getSession();
//				System.out.println(session.getAttribute("user"));
				if(session.getAttribute("user")==null){
					System.out.println("LogIn�����`");
					request.setAttribute("checkLogIn", "false");
				}
				else {
					 System.out.println("LogIn���`");
					request.setAttribute("checkLogIn", "true");
				}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void logIn(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // �n�J
		String pathLogInController = request.getRequestURI();
		System.out.println(pathLogInController);
		LoginDAO dao = new LoginDAOImpl();
		String userId = request.getParameter("userID");
		System.out.println("userId: "+userId);
		String password = request.getParameter("password");
		System.out.println("password: "+password);
		
		UserBean user = dao.getUserId(userId,password);
//		System.out.println("user.getUserId(): "+user.getUserId());
		HttpSession session = request.getSession();
		if(user.getUserId()==null){
			session.setAttribute("error", "��J���b���K�X���~�A�Э��s��J");
			response.sendRedirect("../relogin.jsp");
		}
		else {
		session.setAttribute("user", user);
		response.sendRedirect("/SmartSales/member/GetOnsiteMembers");
		}
	}
	
	private void logOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // �n�X
		System.out.println("LogOutController");
		HttpSession session=request.getSession();
		
		System.out.println(session.getAttribute("user"));
		session.removeAttribute("user");    // �M��session
		session.removeAttribute("error");    // �M��session
		
		System.out.println(session.getAttribute("user"));
		response.sendRedirect("../relogin.jsp");
	}
	
}
