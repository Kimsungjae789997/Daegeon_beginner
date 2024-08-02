package vo;

public class PayListVo2 {

	private int pay_no;
	private String pay_date;
	private String buyer_name;
	private String prod_name;
	private int cart_qty;
	private int cart_amt;
	private int mem_no;
	public int getPay_no() {
		return pay_no;
	}
	public void setPay_no(int pay_no) {
		this.pay_no = pay_no;
	}
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	public String getBuyer_name() {
		return buyer_name;
	}
	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public int getCart_qty() {
		return cart_qty;
	}
	public void setCart_qty(int cart_qty) {
		this.cart_qty = cart_qty;
	}
	public int getCart_amt() {
		return cart_amt;
	}
	public void setCart_amt(int cart_amt) {
		this.cart_amt = cart_amt;
	}
	public int getMem_no() {
		return mem_no;
	}
	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	
	@Override
	public String toString() {
		return mem_no + "\t" + pay_no + "\t" + pay_date + "\t" + buyer_name + "\t" + 
			   prod_name + "\t" +  cart_qty + "\t" + cart_amt + "\t";
	}
	
	
	
	
}
