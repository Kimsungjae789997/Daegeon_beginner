package vo;

public class ProdVo {
	private String prod_id;
	private int prod_cost;
	private int prod_price;
	private int buyer_no;
	private String prod_lgu;
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	public int getProd_cost() {
		return prod_cost;
	}
	public void setProd_cost(int prod_cost) {
		this.prod_cost = prod_cost;
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
	public String getProd_lgu() {
		return prod_lgu;
	}
	public void setProd_lgu(String prod_lgu) {
		this.prod_lgu = prod_lgu;
	}
	
	@Override
	public String toString() {
		return "ProdVo [prod_id=" + prod_id + ", prod_cost=" + prod_cost + ", prod_price=" + prod_price + ", buyer_no="
				+ buyer_no + ", prod_lgu=" + prod_lgu + "]";
	}
	
	
	
}
