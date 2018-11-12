package Control;

import java.util.ArrayList;

import Model.dao_Category;
import Objects.Category;

public class CategoryControl {

	public ArrayList<Category> getListCategory(){ 
		dao_Category dao = new dao_Category(); 
		String sql = "Select * From loaisanpham";
		return dao.SelectDB(sql);
	}
	
	public Category getFindWithId(int id) {
		dao_Category dao = new dao_Category();
		String sql = "SELECT * FROM loaisanpham WHERE id = " + id;
		return dao.FindWithId(sql);
	}
	
	public boolean getEditData(Category category) {
		dao_Category dao = new dao_Category();
		String sql = "UPDATE loaisanpham SET tenloai = ? WHERE id = ?";
		System.out.println("Control");
		System.out.println(category.getTenloai());
		System.out.println(sql);
		return dao.EditData(category, sql);
	}
	
	public static void main(String[] args) { 

//		CategoryControl c = new CategoryControl();  
//		Category category = new Category();
//		category.setId(2);
//		category.setTenloai("Banh 1");
//		c.getEditData(category);
	}

}
