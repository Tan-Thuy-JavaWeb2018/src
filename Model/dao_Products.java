package Model;

import java.sql.ResultSet;
import java.sql.SQLException; 
import java.text.NumberFormat;
import java.util.ArrayList; 

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Objects.Products;


public class dao_Products { 
	ConnectToDB conndb;
	Connection con;
	//	Phương thức lấy dữ liệu về
	public ArrayList<Products> SelectDB(String sql) { 
		conndb = new ConnectToDB();
		// Mở kết nối nhận biến con ở dạng com.mysql.jdbc.JDBC4Connection@5c072e3f
		Connection con = (Connection) conndb.OpenConnnect();  
		//Khởi tạo mảng lưu giá trị
		ArrayList<Products> list = new ArrayList<>(); 
		Statement stmt; 
		try {
			stmt = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);  
			while (rs.next()) {
				//Tạo một đối tượng gán giá trị
				Products products = new Products();
				products .setId(rs.getLong("id")); 
				products.setAnhchinh(rs.getString("anhchinh"));
				products.setGiagoc(rs.getDouble("giagoc"));
				products.setId_loaisanpham(rs.getLong("id_loaisp"));
				products.setKhuyenmai(rs.getInt("khuyenmai"));
				products.setLuotthich(rs.getInt("luotthich"));
				products.setMota(rs.getString("mota"));
				products.setTensanpham(rs.getString("tensanpham"));
				products.setTinhtrang(rs.getInt("tinhtrang"));
				// Thêm vào danh sách
				list.add(products);  
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
		dao_Products dao = new  dao_Products(); 
		for (Products ls : dao.SelectDB("Select * From sanpham")) { 
			NumberFormat fmt = NumberFormat.getCurrencyInstance(); 
			double price = ls.getGiagoc();
			int sale = ls.getKhuyenmai();
			float price_sale = (float) (price - ((price*sale)/100));
			System.out.println(fmt.format(price_sale));
		} 
		System.out.println("Check ok!");   

	}

}
