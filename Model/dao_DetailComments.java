package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Objects.DetailComments;

public class dao_DetailComments {
	ConnectToDB conndb;

	public ArrayList<DetailComments> SelectDB(String sql) {
		conndb = new ConnectToDB();

		Connection con = (Connection) conndb.OpenConnnect();
		// Khởi tạo mảng lưu giá trị
		ArrayList<DetailComments> list = new ArrayList<>();
		Statement stmt;
		try {
			stmt = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				DetailComments detailComment = new DetailComments();
				detailComment.setId(rs.getLong("id"));
				detailComment.setNoidung(rs.getString("noidung"));
				detailComment.setId_binhluan(rs.getLong("id_binhluan"));
				detailComment.setId_taikhoan(rs.getLong("id_taikhoan"));
				detailComment.setCreated_at(rs.getString("created_at"));

				list.add(detailComment);
			}
			conndb.CloseConnect();
			return list;
		} catch (SQLException e) {
			conndb.CloseConnect();
			e.printStackTrace();
		}
		return null;
	}

	public DetailComments FindWithId(String sql) {
		conndb = new ConnectToDB();
		Connection con = (Connection) conndb.OpenConnnect();

		DetailComments detailComment = new DetailComments();
		Statement stmt;
		try {
			stmt = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				detailComment.setId(rs.getLong("id"));
				detailComment.setNoidung(rs.getString("noidung"));
				detailComment.setId_binhluan(rs.getLong("id_binhluan"));
				detailComment.setId_taikhoan(rs.getLong("id_taikhoan"));
				detailComment.setCreated_at(rs.getString("created_at"));
			}
			conndb.CloseConnect();
			return detailComment;

		} catch (Exception e) {
			conndb.CloseConnect();
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		ArrayList<DetailComments> list = new dao_DetailComments().SelectDB("SELECT * FROM chitietbinhluan");
		for (DetailComments detailComments : list) {
			System.out.println(detailComments.getCreated_at());
		}
	}
}
