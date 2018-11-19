package Control;

import java.util.ArrayList;

import Model.dao_Users;
import Objects.DetailProducts;
import Objects.Users;

public class UserControl {
	dao_Users dao = new dao_Users();
	// Lấy thông tin một tài khoản
	public Users getUserWithUserName(String user_name) {
		String sql = "SELECT * FROM taikhoan WHERE tentaikhoan = '"+user_name+"' ";
		return dao.CheckLogin(sql);
	} 
}
