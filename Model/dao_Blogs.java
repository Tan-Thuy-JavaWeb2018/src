package Model;

import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList; 

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Objects.Blogs; 

public class dao_Blogs {
	ConnectToDB conndb;
	public ArrayList<Blogs> SelectDB(String sql) { 
		conndb = new ConnectToDB();
		// Mở kết nối nhận biến con ở dạng com.mysql.jdbc.JDBC4Connection@5c072e3f
		Connection con = (Connection) conndb.OpenConnnect();  
		//Khởi tạo mảng lưu giá trị
		ArrayList<Blogs> list = new ArrayList<>(); 
		Statement stmt; 
		try {
			stmt = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);  
			while (rs.next()) {
				//Tạo một đối tượng gán giá trị
				Blogs blogs = new Blogs(); 
				blogs.setId(rs.getLong("id"));
				blogs.setId_taikhoan(rs.getLong("id_taikhoan"));
				blogs.setHinhanh(rs.getString("hinhanh"));
				blogs.setMota(rs.getString("mota"));
				blogs.setNoidung(rs.getString("noidung"));
				blogs.setTieude(rs.getString("tieude"));
				// Thêm vào danh sách
				list.add(blogs);  
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
	public static void main(String[] args) {
		
	}

}
