package Control;

import java.text.DecimalFormat;
import java.util.ArrayList; 
import Model.dao_Products; 
import Objects.Products;

public class ProductsControl {
	public ArrayList<Products> getListCategory(){ 
		dao_Products dao = new dao_Products(); 
		String sql = "Select * From sanpham";
		return dao.SelectDB(sql);
	}
	public static void main(String[] args) {  
		dao_Products dao = new  dao_Products();  
		for (Products ls : dao.SelectDB("Select * From sanpham")) { 
			DecimalFormat numformat = new DecimalFormat("#,###,###"); 
			String number = numformat.format(ls.getGiagoc()*900000000); 
			System.out.println("3. DecimalFormat with ,: " + number);
		} 
	}

}
