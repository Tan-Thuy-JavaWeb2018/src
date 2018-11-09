package Control;

import java.util.ArrayList;

import Model.dao_Blogs;
import Objects.Blogs;
 

	public class BlogsControl {
		
	public ArrayList<Blogs> getListBlogs(){ 
		dao_Blogs dao = new dao_Blogs(); 
		String sql = "Select * From baiviet";
		return dao.SelectDB(sql);
	}

	public static void main(String[] args) {
		dao_Blogs dao = new  dao_Blogs(); 
		for (Blogs ls : dao.SelectDB("Select * From baiviet")) {  
			System.out.println(ls.getId());
		}  
	}

}
