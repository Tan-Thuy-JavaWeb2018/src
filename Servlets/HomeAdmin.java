package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.dao_Users;
import Objects.Users;

/**
 * Servlet implementation class HomeAdmin
 */
@WebServlet(description = "HomeAdmin", urlPatterns = { "/admin/pages/home/" })
public class HomeAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pass = new UsAddAccount().encryption("123");
		String sql = "SELECT * FROM taikhoan WHERE (tentaikhoan = 'admin' AND matkhau = '"+pass+"')";
		HttpSession session = request.getSession();
		if(new dao_Users().CheckLogin(sql) != null) {
			Users us = new dao_Users().CheckLogin(sql); 
			session.setAttribute("uslogin", us);
		}
		
		if(session.getAttribute("uslogin") != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("../../../pages/login.jsp");
		}
//		HttpSession session = request.getSession();
//		if(session.getAttribute("uslogin") != null) {
//			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
//			dispatcher.forward(request, response);
//		} else {
//			response.sendRedirect("../../../pages/login.jsp");
//		}
	}

}
