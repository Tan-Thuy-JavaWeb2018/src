package Control;

import java.text.DecimalFormat;
import java.util.ArrayList; 
import Model.dao_Products; 
import Objects.Products;

public class ProductsControl {
//	Lấy toàn bộ sản phẩm
	public ArrayList<Products> getListProducts(){ 
		dao_Products dao = new dao_Products(); 
		String sql = "Select * From sanpham";
		return dao.SelectDB(sql);
	}
//	Lấy sản phẩm theo từng loại
	public ArrayList<Products> getListProductsType(int id){ 
		dao_Products dao = new dao_Products(); 
		String sql = "Select * From sanpham Where id_loaisp ="+id;
		return dao.SelectDB(sql);
	}
	public static void main(String[] args) {  
		ProductsControl p = new ProductsControl(); 
		for(Products ls : p.getListProductsType(4)) {
			System.out.println(ls.getId_loaisanpham()+" "+ls.getTensanpham()+" "+ls.getTinhtrang());
		}
	}

}
