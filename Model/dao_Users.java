package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Objects.Category;
import Objects.Users;

public class dao_Users {
	ConnectToDB conndb;
	Connection con;
	//Kiểm tra Email và tài khoản đã tồn tại chưa 
	public boolean checkEmail(String sql) {
		conndb = new ConnectToDB();
		con = (Connection) conndb.OpenConnnect(); 
		Statement stmt; 
		try {
			stmt = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
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
		Connection con = (Connection) conndb.OpenConnnect();
		PreparedStatement pst = null;
		String sql = "INSERT INTO taikhoan VALUES(?,?,?,?,?,?) ";
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
			// TODO Auto-generated catch block
			//setError("Lỗi cập nhật");
			e.printStackTrace();
		}
		conndb.CloseConnect();
		return false;
	}

	public static void main(String[] args) {
		dao_Users dao = new dao_Users();
		String sql = "SELECT * FROM taikhoan WHERE email = 'thuy' ";
		System.out.println(dao.checkEmail(sql));
	}

}
