package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList; 
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Objects.Category;

public class dao_Category { 
	ConnectToDB conndb;
	//	Phương thức lấy dữ liệu về
	public ArrayList<Category> SelectDB(String sql) { 
		conndb = new ConnectToDB();
		// Mở kết nối nhận biến con ở dạng com.mysql.jdbc.JDBC4Connection@5c072e3f
		Connection con = (Connection) conndb.OpenConnnect();  
		//Khởi tạo mảng lưu giá trị
		ArrayList<Category> list = new ArrayList<>(); 
		Statement stmt; 
		try {
			stmt = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);  
			while (rs.next()) {
				//Tạo một đối tượng để thêm vào danh sách
				Category category = new Category();
				category.setId(rs.getLong("id"));
				category.setTenloai(rs.getString("tenloai"));
				list.add(category);  
			}
			// Đóng kết nối
			conndb.CloseConnect();
			return list;
		} 
		catch (SQLException e) { 
			// Đóng kết nối
			conndb.CloseConnect();
			e.printStackTrace();
		} 
		return null;
	}
	//	Phương thức thêm
	public boolean AddData() {
		return false;
	}
	//	Phương thức sửa
	public boolean EditData() {
		return false;
	}
	//	Phương thức xóa
	public boolean DelData() {
		return false;
	}

	public static void main(String[] args) {
		dao_Category sl = new  dao_Category(); 
		System.out.println(sl.SelectDB("Select * From loaisanpham").get(0).getId());
		System.out.println("ok");
	}

}
