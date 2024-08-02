package vo;

public class CartVo {
	private int cart_no;
	private int mem_no;
	private int cart_amt;
	
	
	public int getCart_no() {
		return cart_no;
	}
	public void setCart_no(int cart_no) {
		this.cart_no = cart_no;
	}
	public int getMem_no() {
		return mem_no;
	}
	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	public int getCart_amt() {
		return cart_amt;
	}
	public void setCart_amt(int cart_amt) {
		this.cart_amt = cart_amt;
	}
	
	@Override
	public String toString() {
		return "결제 총금액 : " + cart_amt;
	}
	
	
}
