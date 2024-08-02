package vo;

public class BuyerListPVo {
	private String buyer_name;
	private String buyer_add1;
	private String prod_name;
	private int prod_price;
	private int buyer_no;
	private int prod_id;
	
	




	public String getBuyer_name() {
		return buyer_name;
	}






	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}






	public String getBuyer_add1() {
		return buyer_add1;
	}






	public void setBuyer_add1(String buyer_add1) {
		this.buyer_add1 = buyer_add1;
	}






	public String getProd_name() {
		return prod_name;
	}






	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}






	public int getProd_price() {
		return prod_price;
	}






	public void setProd_price(int prod_price) {
		this.prod_price = prod_price;
	}






	public int getBuyer_no() {
		return buyer_no;
	}






	public void setBuyer_no(int buyer_no) {
		this.buyer_no = buyer_no;
	}






	public int getProd_id() {
		return prod_id;
	}






	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}






	@Override
	public String toString() {
		return buyer_no + "\t" + buyer_name  
				+ "\t" + prod_name + "\t" + prod_price + "\t" + buyer_add1;
	}
	

	
	
}
