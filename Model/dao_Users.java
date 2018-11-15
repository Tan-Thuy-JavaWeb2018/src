package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Objects.Category;
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

	public static void main(String[] args) {
		Pattern pattern; 
		final String PASSWORD_PATTERN = ".{3,30}";  
		//	Kiểm tra tên tài khoản
		pattern = Pattern.compile(PASSWORD_PATTERN);
		if(pattern.matcher("H").matches()) {
			System.out.println("Được");
		}
		else {
			 System.out.println("Không");
		}
	}

}
