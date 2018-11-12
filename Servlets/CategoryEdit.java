package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Control.CategoryControl;
import Objects.Category;

/**
 * Servlet implementation class CategoryEdit
 */
@WebServlet(description = "Edit Category", urlPatterns = { "/admin/pages/category/edit" })
public class CategoryEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryEdit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		CategoryControl categoryControl = new CategoryControl();
		Category category = new Category();
		category = categoryControl.getFindWithId(Integer.parseInt(id));
		request.setAttribute("category", category);
		RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
		dispatcher.forward(request, response);
//		PrintWriter writer = response.getWriter();
//		writer.println(category.getTenloai());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		String id = request.getParameter("id");
		String tenloai = request.getParameter("tenloai");
		Category category = new Category();
		category.setId(Long.parseLong(id));
		category.setTenloai(tenloai);
		CategoryControl categoryControl = new CategoryControl();
		boolean check = categoryControl.getEditData(category);
		if (check) {
			writer.println("Thanhf coong");
		} else {
			writer.println("TB");
		}
		writer.println(id);
		writer.println(tenloai);
	}

}
