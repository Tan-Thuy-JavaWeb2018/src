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

	public static void main(String[] args) { 

		CategoryControl c = new CategoryControl();  
		if(c.getListCategory().size() != 0) {
//			Lấy toàn bộ trong danh sách
			for(Category ls : c.getListCategory()) { 
				System.out.println(ls.getId()+" "+ls.getTenloai());
			}
//			Lấy theo số lượng
//			int quantity = 3;
//			for(int index = 0; index < quantity; index++) {
//				long id = c.getListCategory().get(index).getId();
//				String tenloai = c.getListCategory().get(index).getTenloai();
//				System.out.println(id+" "+tenloai);
//			}
		}
		else
			System.out.println("Không có dữ liệu để in");
	}

}
