package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Control.CommentsControl;
import Control.DetailCommentsControl;
import Objects.Comments;
import Objects.DetailComments;
import Objects.Users;

/**
 * Servlet implementation class CommentLoadData
 */
@WebServlet(description = "CommentLoadData", urlPatterns = { "/admin/pages/comment/loaddata" })
public class CommentLoadData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentLoadData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("uslogin");

		String idBaiViet = request.getParameter("idBaiViet");
		// System.out.println(idBaiViet);

		JSONObject data = new JSONObject();
		JSONArray array = new JSONArray();

		CommentsControl commentControl = new CommentsControl();
		// Lấy toàn bộ dữ liệu Comments của bài viết id_baiviet
		ArrayList<Comments> commentList = commentControl.getListCommnetsWithID_BaiViet(Long.valueOf(idBaiViet));
		for (Comments comments : commentList) {
			// put toàn bộ dữ liệu commentList vào mapComment
			HashMap<String, Object> mapComment = new HashMap<String, Object>();
			mapComment.put("id", comments.getId());
			mapComment.put("tenhienthi", comments.getId_taikhoan());
			mapComment.put("noidung", comments.getNoidung());
			// System.out.println(comments.getCreated_at() + "a");
			mapComment.put("created_at", comments.getCreated_at().split(" ")[0]);
			String opition = "";
			String editComment = "<button class=\"btn btn-success\" onclick=\"EditComment(" + comments.getId() + ",'"
					+ comments.getNoidung() + "')\"><span><i class=\"fa fa-edit\"></i></span></button>";
			String deleteComment = "<button class=\"btn btn-secondary\" onclick=\"DeleteComment(" + comments.getId()
					+ ")\"><span><i class=\"fa fa-trash-o\"></i></span></button>&nbsp&nbsp&nbsp";

			if (comments.getId_taikhoan() == user.getId()) {
				opition += deleteComment + editComment;
			} else {
				opition += deleteComment;
			}
			mapComment.put("opition", opition);

			// Cách 2: cách này chạy ổn nhưng nên chuyển về HashMap
			// JSONObject objComment = new JSONObject();
			// objComment.put("id", comments.getId());
			// objComment.put("tenhienthi", comments.getId_taikhoan());
			// objComment.put("noidung", comments.getNoidung());
			// objComment.put("created_at", comments.getCreated_at().split(" ")[0]);

			// Lấy toàn bộ DetailComment của Comment id_binhluan
			JSONArray arrayChiTiet = new JSONArray();
			DetailCommentsControl detailComment = new DetailCommentsControl();
			long id_binhluan = Long.valueOf(comments.getId());
			ArrayList<DetailComments> detailCommentList = detailComment
					.getListDetailCommentWithID_BinhLuan(id_binhluan);

			for (DetailComments detailComments : detailCommentList) {
				// System.out.println(detailComments.getNoidung());
				HashMap<String, Object> mapDetailComment = new HashMap<String, Object>();
				mapDetailComment.put("id", detailComments.getId());
				mapDetailComment.put("tenhienthict", detailComments.getId_taikhoan());
				mapDetailComment.put("noidungchitiet", detailComments.getNoidung());
				mapDetailComment.put("created_at", detailComments.getCreated_at().split(" ")[0]);
				String opitionDetail = "";
				String editDetailComment = "<a class=\"btn btn-success btn-sm\" href=\"editDetailComment?idDetailComment="
						+ detailComments.getId() + "\"><span><i\r\n"
						+ "														class=\"fa fa-edit\"></i></span></a>";
				String deleteDetailComment = "<button class=\"btn btn-secondary btn-sm\" onclick=\"DeleteDetailComment("
						+ detailComments.getId()
						+ ")\"><span><i class=\"fa fa-trash-o\"></i></span></button>&nbsp&nbsp&nbsp";

				if (detailComments.getId_taikhoan() == user.getId()) {
					opitionDetail += deleteDetailComment + editDetailComment;
				} else {
					opitionDetail += deleteDetailComment;
				}
				mapDetailComment.put("opitionDetail", opitionDetail);

				JSONObject objDetailComment = new JSONObject(mapDetailComment);
				arrayChiTiet.add(objDetailComment);
			}

			// Cách 2: cách này chạy ổn nhưng nên chuyển về HashMap
			// JSONObject objDetailComment = new JSONObject();
			// objDetailComment.put("id", "1");
			// objDetailComment.put("tenhienthict", "tenchitiet1");
			// objDetailComment.put("noidungchitiet", "noidungchitiet1");
			// objDetailComment.put("created_at", "2018/04/25");

			// objComment.put("chitiet", arrayChiTiet);
			mapComment.put("chitiet", arrayChiTiet);
			JSONObject objComment = new JSONObject(mapComment);

			array.add(objComment);

		}
		data.put("data", array);

		Users us = (Users) session.getAttribute("uslogin");
		// System.out.println(us.getId());
		//
		// System.out.println(data);

		response.setContentType("application/json");
		response.getWriter().write(data.toJSONString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
