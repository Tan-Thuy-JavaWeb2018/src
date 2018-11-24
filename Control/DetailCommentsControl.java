package Control;

import java.util.ArrayList;

import Model.dao_DetailComments;
import Objects.DetailComments;

public class DetailCommentsControl {
	dao_DetailComments dao = new dao_DetailComments();

	public ArrayList<DetailComments> getListDetailComment() {
		String sql = "SELECT * FROM chitietbinhluan";
		return dao.SelectDB(sql);
	}

	public DetailComments getFindWithId(long id) {
		String sql = "SELECT * FROM chitietbinhluan WHERE id = " + id;
		return dao.FindWithId(sql);
	}
	
	//Lấy toàn bộ DetailComment của Comment id_binhluan
	public ArrayList<DetailComments> getListDetailCommentWithID_BinhLuan(long id_binhluan) {
		String sql = "SELECT * FROM chitietbinhluan WHERE id_binhluan = " + id_binhluan;
		return dao.SelectDB(sql);
	}
}
