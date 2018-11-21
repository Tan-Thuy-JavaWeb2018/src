package Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import Objects.Items;

public class dao_Cart {

	private HashMap<Long, Items> cartItems;

	public HashMap<Long, Items> getCartItems() {
		return cartItems;
	}

	public void setCartItems(HashMap<Long, Items> cartItems) {
		this.cartItems = cartItems;
	}

	public dao_Cart(){
		cartItems = new HashMap<>();
	}

	public dao_Cart(HashMap<Long, Items> cartItems) { 
		this.cartItems = cartItems;
	}

	//	Phương thức thêm vào giỏ hàng
	public void insertToCart(Long key, Items item) {
		boolean check = cartItems.containsKey(key);
		//	Sản phẩm tồn tại thì số lượng +1, không thì thêm mới
		if(check) {
			int quantity_old = item.getQuantity();
			item.setQuantity(quantity_old+1);
			cartItems.put(key, item);
		}
		else {
			cartItems.put(key, item);
		}
	}
	//	Phương thức thay đổi giỏ hàng

	//	Phương thức xóa giỏ hàng
	public void removeToCart(Long key) {
		boolean check = cartItems.containsKey(key);
		// Nếu tồn tại theo id thì xóa bỏ
		if(check) {
			cartItems.remove(key);
		}
	}
	//	Phương thức đếm số lượng
	public int countItems() {
		// Trả về số lượng phẩm phẩm trong cart
		return cartItems.size();
	}

	// Tổng giá tiền
	public double totalCart() {
		int count = 0;
		for (Entry<Long, Items> list : cartItems.entrySet()) {
			count +=list.getValue().getProducts().getGiagoc() * list.getValue().getQuantity();
		}
		return count;
	} 
}
