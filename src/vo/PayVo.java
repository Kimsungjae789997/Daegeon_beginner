package vo;

public class PayVo {
	
	private int pay_no;
	private String pay_date;
	private int cart_no;
	
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
	public int getCart_no() {
		return cart_no;
	}
	public void setCart_no(int cart_no) {
		this.cart_no = cart_no;
	}
	
	@Override
	public String toString() {
		return pay_no +"\t" +pay_date+"\t" +cart_no;
	}
	
	
}
