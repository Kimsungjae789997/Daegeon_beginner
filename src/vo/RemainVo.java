package vo;

public class RemainVo {
	private String remain_year;
	private String prod_id;
	private int remain_j_00;
	private int remain_i;
	private int remain_o;
	private int remain_j_99;
	private String remain_date;
	private String remain_del;
	
	
	public String getRemain_year() {
		return remain_year;
	}
	public void setRemain_year(String remain_year) {
		this.remain_year = remain_year;
	}
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	public int getRemain_j_00() {
		return remain_j_00;
	}
	public void setRemain_j_00(int remain_j_00) {
		this.remain_j_00 = remain_j_00;
	}
	public int getRemain_i() {
		return remain_i;
	}
	public void setRemain_i(int remain_i) {
		this.remain_i = remain_i;
	}
	public int getRemain_o() {
		return remain_o;
	}
	public void setRemain_o(int remain_o) {
		this.remain_o = remain_o;
	}
	public int getRemain_j_99() {
		return remain_j_99;
	}
	public void setRemain_j_99(int remain_j_99) {
		this.remain_j_99 = remain_j_99;
	}
	public String getRemain_date() {
		return remain_date;
	}
	public void setRemain_date(String remain_date) {
		this.remain_date = remain_date;
	}
	public String getRemain_del() {
		return remain_del;
	}
	public void setRemain_del(String remain_del) {
		this.remain_del = remain_del;
	}
	
	
	@Override
	public String toString() {
		return "년도 : "+remain_year+" 상품번호 : "+prod_id+" 기초수량 : "+remain_j_00+" 매입수량 "+remain_i+" 매출수량 : "+remain_o+" 현재고 : "+remain_j_99+ " 변경일자 : "+ remain_date;
	}
	
	
}
