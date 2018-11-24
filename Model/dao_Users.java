package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Objects.Users; 

public class dao_Users {
	ConnectToDB conndb;
	Connection con;

	//Kiểm tra Email và tài khoản đã tồn tại chưa 
	public boolean checkAcc(String sql) {
		conndb = new ConnectToDB();
		con = (Connection) conndb.OpenConnnect(); 
		Statement stmt; 
		try {
			stmt = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				conndb.CloseConnect();
				return true;
			}

		} catch (SQLException e) { 
			e.printStackTrace();
		}
		conndb.CloseConnect();
		return false;  
	}

	//	Phương thức thêm một tài khoản mới
	public boolean AddAccount(Users user) {
		conndb = new ConnectToDB();
		con = (Connection) conndb.OpenConnnect(); 
		String sql = "INSERT INTO taikhoan VALUES (default,?,?,?,?,?,?) ";
		PreparedStatement pst = null;
		try { 
			pst = con.prepareCall(sql); 
			pst.setString(1, user.getTentaikhoan());
			pst.setString(2, user.getEmail());
			pst.setString(3, user.getMatkhau());
			pst.setString(4, user.getPhanquyen());
			pst.setString(5, user.getTenhienthi());
			pst.setString(6, user.getHinhanh()); 
			pst.executeUpdate();
			conndb.CloseConnect(); 
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conndb.CloseConnect();
		return false;
	}

	//	Phương thức cập nhật thông tin cho tài khoản
	public boolean EditAccount(Users user) {
		conndb = new ConnectToDB();
		con = (Connection) conndb.OpenConnnect();  
		String sql = "UPDATE taikhoan SET tenhienthi = ? , matkhau = ? WHERE tentaikhoan = ? ";
		PreparedStatement pst = null;
		try { 
			pst = con.prepareCall(sql); 
			pst.setString(1, user.getTenhienthi());
			pst.setString(2, user.getMatkhau());  
			pst.setString(3, user.getTentaikhoan());
			pst.executeUpdate();
			conndb.CloseConnect(); 
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conndb.CloseConnect();
		return false;
	}
	
//	Phương thức cập nhật ảnh cho tài khoản
	public boolean EditAccountAvt(Users user) {
		conndb = new ConnectToDB();
		con = (Connection) conndb.OpenConnnect();  
		String sql = "UPDATE taikhoan SET hinhanh = ? WHERE tentaikhoan = ? ";
		PreparedStatement pst = null;
		try { 
			pst = con.prepareCall(sql); 
			pst.setString(1, user.getHinhanh());
			pst.setString(2, user.getTentaikhoan());   
			pst.executeUpdate();
			conndb.CloseConnect(); 
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conndb.CloseConnect();
		return false;
	}

	//Phương thức kiểm tra đăng nhập
	public Users CheckLogin(String sql) {
		conndb = new ConnectToDB();
		con = (Connection) conndb.OpenConnnect(); 
		Statement stmt; 
		try {
			stmt = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				Users user = new Users();
				user.setId(rs.getLong("id"));
				user.setTentaikhoan(rs.getString("tentaikhoan"));
				user.setEmail(rs.getString("email"));
				user.setPhanquyen(rs.getString("phanquyen"));
				user.setTenhienthi(rs.getString("tenhienthi"));
				user.setHinhanh(rs.getString("hinhanh"));
				conndb.CloseConnect(); 
				return user;
			}

		} catch (SQLException e) { 
			e.printStackTrace();
		}
		conndb.CloseConnect();
		return null;  
	}

	public Users FindById(String sql) {
		conndb = new ConnectToDB();
		// Mở kết nối nhận biến con ở dạng com.mysql.jdbc.JDBC4Connection@5c072e3f
		Connection con = (Connection) conndb.OpenConnnect();
		
		Users user = new Users();
		Statement stmt;
		try {
			stmt = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				user.setId(rs.getLong("id"));
				user.setTentaikhoan(rs.getString("tentaikhoan"));
				user.setEmail(rs.getString("email"));
				user.setPhanquyen(rs.getString("phanquyen"));
				user.setTenhienthi(rs.getString("tenhienthi"));
				user.setHinhanh(rs.getString("hinhanh"));
			}
			conndb.CloseConnect(); 
			return user;
		} catch (SQLException e) {
			conndb.CloseConnect();
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {  

	}

}
