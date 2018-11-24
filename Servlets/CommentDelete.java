package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
import Control.UserControl;
import Objects.Comments;
import Objects.DetailComments;
import Objects.Users;

/**
 * Servlet implementation class CommentDelete
 */
@WebServlet(description = "Delete", urlPatterns = { "/admin/pages/comment/delete" })
public class CommentDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentDelete() {
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

		String idComment = request.getParameter("idComment");
		CommentsControl commentControl = new CommentsControl();
		JSONObject data = new JSONObject();
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("uslogin");
		long id_user = user.getId();

		String idBaiViet = request.getParameter("idBaiViet");

		if (commentControl.getDelData(Long.valueOf(idComment))) {
			JSONArray array = new JSONArray();

			// Lấy toàn bộ dữ liệu Comments của bài viết id_baiviet
			ArrayList<Comments> commentList = commentControl.getListCommnetsWithID_BaiViet(Long.valueOf(idBaiViet));
			for (Comments comments : commentList) {
				// put toàn bộ dữ liệu commentList vào mapComment
				HashMap<String, Object> mapComment = new HashMap<String, Object>();
				mapComment.put("id", comments.getId());
				String name = new UserControl().getFindById(comments.getId_taikhoan()).getTenhienthi();
				mapComment.put("tenhienthi", name);
				mapComment.put("noidung", comments.getNoidung());
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
					String detailName = new UserControl().getFindById(detailComments.getId_taikhoan()).getTenhienthi();
					mapDetailComment.put("tenhienthict", detailName);
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

				mapComment.put("chitiet", arrayChiTiet);
				JSONObject objComment = new JSONObject(mapComment);

				array.add(objComment);
			}
			data.put("data", array);
		} else
			System.out.println("Xóa thất bại");

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
