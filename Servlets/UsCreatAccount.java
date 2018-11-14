package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.dao_Users;
import Objects.Users; 


public class UsCreatAccount extends HttpServlet {

	dao_Users dao = new dao_Users();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Dùng để phân biệt giữa các biến
		String command = request.getParameter("command");
		String url = "";
		switch(command){
			case "insert":
				Users users = new Users(); 
				users.setTentaikhoan(request.getParameter("user-name"));
				users.setEmail(request.getParameter("user-email"));
				users.setHinhanh("");
				users.setMatkhau(request.getParameter("user-password"));
				users.setPhanquyen("user");
				users.setTenhienthi(request.getParameter("user-view")); 
				dao.AddAccount(users);
				HttpSession session = request.getSession();
				session.setAttribute("user", users);
				url = "/index.jsp";
				break;
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
