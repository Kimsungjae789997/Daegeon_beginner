package vo;

public class ReviewVo {
	private int rev_no;
	private int rev_grade;
	private String rev_date;
	private String rev_title;
	private String rev_content;
	private int pay_no;
	private int mem_no;
	private String rev_del;
	
	public int getRev_no() {
		return rev_no;
	}
	public void setRev_no(int rev_no) {
		this.rev_no = rev_no;
	}
	public int getRev_grade() {
		return rev_grade;
	}
	public void setRev_grade(int rev_grade) {
		this.rev_grade = rev_grade;
	}
	public String getRev_date() {
		return rev_date;
	}
	public void setRev_date(String rev_date) {
		this.rev_date = rev_date;
	}
	public String getRev_title() {
		return rev_title;
	}
	public void setRev_title(String rev_title) {
		this.rev_title = rev_title;
	}
	public String getRev_content() {
		return rev_content;
	}
	public void setRev_content(String rev_content) {
		this.rev_content = rev_content;
	}
	public int getPay_no() {
		return pay_no;
	}
	public void setPay_no(int pay_no) {
		this.pay_no = pay_no;
	}
	public int getMem_no() {
		return mem_no;
	}
	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	public String getRev_del() {
		return rev_del;
	}
	public void setRev_del(String rev_del) {
		this.rev_del = rev_del;
	}
	@Override
	public String toString() {
		return "리뷰 번호 : " +rev_no+ " 회원번호 : " + mem_no + " 리뷰 제목 : "+rev_title+" 내용 : "+rev_content+" 별점 : "+rev_grade + " 작성일 : "+rev_date;
	}
	
	
}
