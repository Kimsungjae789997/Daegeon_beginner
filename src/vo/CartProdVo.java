package vo;

public class CartProdVo {
	private String prod_id;
	private int cart_no;
	private int cart_qty;
	
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	public int getCart_no() {
		return cart_no;
	}
	public void setCart_no(int cart_no) {
		this.cart_no = cart_no;
	}
	public int getCart_qty() {
		return cart_qty;
	}
	public void setCart_qty(int cart_qty) {
		this.cart_qty = cart_qty;
	}
	
	
	@Override
	public String toString() {
		return prod_id + "\t" + cart_qty;
	}
	
	
}
