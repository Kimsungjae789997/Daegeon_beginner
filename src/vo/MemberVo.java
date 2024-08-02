package vo;

public class MemberVo {
	private int mem_no;
	private String mem_name;
	private String mem_id;
	private String mem_pass;
	private String mem_regno1;
	private String mem_regno2;
	private String mem_hp;
	private String mem_cardno;
	private String acdate;
	private String addate;
	
	public int getMem_no() {
		return mem_no;
	}
	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_pass() {
		return mem_pass;
	}
	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}
	public String getMem_regno1() {
		return mem_regno1;
	}
	public void setMem_regno1(String mem_regno1) {
		this.mem_regno1 = mem_regno1;
	}
	public String getMem_regno2() {
		return mem_regno2;
	}
	public void setMem_regno2(String mem_regno2) {
		this.mem_regno2 = mem_regno2;
	}
	public String getMem_hp() {
		return mem_hp;
	}
	public void setMem_hp(String mem_hp) {
		this.mem_hp = mem_hp;
	}
	public String getMem_cardno() {
		return mem_cardno;
	}
	public void setMem_cardno(String mem_cardno) {
		this.mem_cardno = mem_cardno;
	}
	public String getAcdate() {
		return acdate;
	}
	public void setAcdate(String acdate) {
		this.acdate = acdate;
	}
	public String getAddate() {
		return addate;
	}
	public void setAddate(String addate) {
		this.addate = addate;
	}
	@Override
	public String toString() {
		return "회원번호 : "+mem_no+"  회원명 : "+mem_name+"  아이디 : "+mem_id+"  비밀번호 : "+mem_pass+"  주민번호 : "+mem_regno1+"-"+mem_regno2+"  전화번호 : "+mem_hp+"  카드번호 : "+mem_cardno+"  가입일 : "+acdate;      
	}
	
	
	

}
