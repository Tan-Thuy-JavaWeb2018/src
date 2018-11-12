package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Objects.Category;

public class dao_Category {
	ConnectToDB conndb;

	// Phương thức lấy dữ liệu về
	public ArrayList<Category> SelectDB(String sql) {
		conndb = new ConnectToDB();
		// Mở kết nối nhận biến con ở dạng com.mysql.jdbc.JDBC4Connection@5c072e3f
		Connection con = (Connection) conndb.OpenConnnect();
		// Khởi tạo mảng lưu giá trị
		ArrayList<Category> list = new ArrayList<>();
		Statement stmt;
		try {
			stmt = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				// Tạo một đối tượng để thêm vào danh sách
				Category category = new Category();
				category.setId(rs.getLong("id"));
				category.setTenloai(rs.getString("tenloai"));
				list.add(category);
			}
			// Đóng kết nối
			conndb.CloseConnect();
			return list;
		} catch (SQLException e) {
			// Đóng kết nối
			conndb.CloseConnect();
			e.printStackTrace();
		}
		return null;
	}

	// Phương thức thêm
	public boolean AddData() {
		return false;
	}

	// Phương thức sửa
	public boolean EditData(Category category, String sql) {
		conndb = new ConnectToDB();
		Connection con = (Connection) conndb.OpenConnnect();
		System.out.println("MOdel");
		System.out.println(category.getTenloai());
		System.out.println(sql);
		// Chuẩn bị 1 prepared statement
		// Cho phép dùng câu lệnh SQL có truyền tham số vào
		PreparedStatement pst = null;
		try {
			
			pst = con.prepareStatement(sql);
		
			pst.setString(1, category.getTenloai());
			pst.setLong(2, category.getId());
			pst.executeUpdate();
			conndb.CloseConnect();
			//setError("Cập nhật thành công!");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//setError("Lỗi cập nhật");
			e.printStackTrace();
		}
		conndb.CloseConnect();
		return false;
	}

	// Phương thức xóa
	public boolean DelData() {
		return false;
	}

	public Category FindWithId(String sql) {
		conndb = new ConnectToDB();
		// Mở kết nối nhận biến con ở dạng com.mysql.jdbc.JDBC4Connection@5c072e3f
		Connection con = (Connection) conndb.OpenConnnect();
		// Khởi tạo đối tượng lưu giá trị
		Category category = new Category();
		Statement stmt;
		try {
			stmt = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				category.setId(rs.getLong("id"));
				category.setTenloai(rs.getString("tenloai"));
			}
			// Đóng kết nối
			conndb.CloseConnect();
			return category;
		} catch (SQLException e) {
			// Đóng kết nối
			conndb.CloseConnect();
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		dao_Category sl = new dao_Category();
		System.out.println(sl.SelectDB("Select * From loaisanpham").get(0).getId());
		System.out.println("ok");
		System.out.println(sl.FindWithId("Select * From loaisanpham where id = 2").getTenloai());
		System.out.println("ok find");
	}

}
