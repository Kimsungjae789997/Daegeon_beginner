package vo;

public class ProdLguVo {
	private String prod_lgu;
	private String prod_name;
	
	public String getProd_lgu() {
		return prod_lgu;
	}
	public void setProd_lgu(String prod_lgu) {
		this.prod_lgu = prod_lgu;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	
	@Override
	public String toString() {
		return "PordLguVo [prod_lgu=" + prod_lgu + ", prod_name=" + prod_name + "]";
	}
	
}
