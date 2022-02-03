package com.aanass.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aanass.dao.UserDao;
import com.aanass.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/User-Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		try (PrintWriter out = response.getWriter()){
			String email = request.getParameter("login-email");
			String password = request.getParameter("login-password");
//			out.println(email+password);
			User user =UserDao.userLogin(email, password);
			if(user==null) {
				response.sendRedirect("login.jsp");
			}else {
				request.getSession().setAttribute("auth", user);
				response.sendRedirect("index.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
