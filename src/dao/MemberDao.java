package dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;
import vo.MemberVo;

public class MemberDao {
	
	static public Map<String, Object> sessionStorage = new HashMap<>();
	
	private static MemberDao instance = null;

	private MemberDao() {

	}

	public static MemberDao getInstance() {
		if (instance == null) {
			instance = new MemberDao();
		}
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	/* 
	 * 로그인할 유저의 정보 조회
	 * (아이디 비밀번호로 해당 유저의 정보를 가져옴)
	 */
	public MemberVo memberLogin(List<Object> param) {
		
		String sql = " SELECT MEM_NO, MEM_NAME, MEM_ID, MEM_PASS, MEM_REGNO1, MEM_REGNO2, MEM_HP, MEM_CARDNO, \r\n"
				+ " TO_CHAR(ACDATE, 'YYYY/MM/DD') AS ACDATE, TO_CHAR(ADDATE, 'YYYY/MM/DD') AS ADDATE\r\n"
				+ " FROM MEMBER WHERE MEM_ID = ? AND MEM_PASS = ?\r\n";
		MemberVo memberVo = jdbc.selectOne(sql, param, MemberVo.class);
		return memberVo;
	}

	/* 
	 * 로그인후 로그인 한 유저의 장바구니 생성 메소드
	 * @Param memberNo {String} : 로그인한 유저의 번호
	 */
	public int createLoginUserCart(int memberNo) {

		String sql = "INSERT INTO CART (CART_NO, MEM_NO) \r\n"
				+ "       VALUES ((SELECT NVL(MAX(CART_NO), 0) + 1\r\n"
				+ "                 FROM CART), " + memberNo + ")";
		int result = jdbc.update(sql);

		return result;
	}
	
	public void memberInsert(List<Object> param) {
		String sql = " INSERT INTO MEMBER (MEM_NO, MEM_ID, MEM_PASS, MEM_NAME, MEM_REGNO1, MEM_REGNO2, MEM_HP, MEM_CARDNO)\r\n" + 
					 "     VALUES ((SELECT NVL(MAX(MEM_NO), 0)+1\r\n" + 
					 "                FROM MEMBER), ?, ?, ?, ?, ?, ?, ?)";
		
		jdbc.update(sql, param);
	}
	
	public int selectCartNo(int memNo){
		
		String sql = "SELECT MAX(CART_NO) AS CART_NO\r\n"
				+ "  FROM CART\r\n"
				+ " WHERE MEM_NO = ?";
		List<Object> param = new ArrayList<Object>();
		param.add(memNo);
		Map<String, Object> cartMap = jdbc.selectOne(sql, param);
		int cartNo = ((BigDecimal) cartMap.get("CART_NO")).intValue();
		
		return cartNo;
	}
	
	public int selectCount(String memberId) {
		return jdbc.selectCount(memberId);
	}
	
	/* 
	 * 로그인할 유저의 정보 조회
	 * (아이디 비밀번호로 해당 유저의 정보를 가져옴)
	 */
	public MemberVo findMemberId(List<Object> param) {
		
		String	sql =	 " SELECT MEM_ID FROM MEMBER WHERE MEM_NAME = ? AND MEM_HP = ?\r\n";
		
		 MemberVo memberVo = jdbc.selectOne(sql, param, MemberVo.class);
		
		return memberVo;
	}
	
	public MemberVo findMemberPass(List<Object> param) {
		
		String	sql =	 " SELECT MEM_PASS FROM MEMBER WHERE MEM_ID = ? AND MEM_NAME = ? AND MEM_HP = ? \r\n";
		
		MemberVo memberVo = jdbc.selectOne(sql, param, MemberVo.class);
		
		return memberVo;
	}
	
	// 회원 비밀번호 수정
	public void passUpdate(List<Object> param) {
		String sql = "UPDATE MEMBER SET MEM_PASS = ?\r\n" + 
				"  WHERE MEM_NO = ?";
		jdbc.update(sql, param);
		
	}

	public void nameUpdate(List<Object> param) {
		String sql = "UPDATE MEMBER SET MEM_NAME = ? \r\n" + 
				"  WHERE MEM_NO = ?";
		jdbc.update(sql,param);
		
	}
	
	public void hpUpdate(List<Object> param) {
		String sql = "UPDATE MEMBER SET MEM_HP = ? \r\n" + 
				"  WHERE MEM_NO = ?";
		jdbc.update(sql,param);
		
	}
	
	public void CardnoUpdate(List<Object> param) {
		String sql = "UPDATE MEMBER SET MEM_CARDNO = ? \r\n" + 
				"  WHERE MEM_NO = ?";
		jdbc.update(sql,param);
		
	}
	
	public void memberWith(List<Object> param) {
		String sql = "UPDATE MEMBER SET ADDATE =sysdate \r\n" + 
				"WHERE MEM_NO = ?";
		jdbc.update(sql, param);
		
	}

	
	public MemberVo memberDetail(List<Object> param) {
		String sql = " SELECT MEM_NO, MEM_NAME, MEM_ID, MEM_PASS, MEM_REGNO1, MEM_REGNO2,\r\n" + 
				" MEM_HP, MEM_CARDNO, TO_CHAR(ACDATE,'YYYY.MM.DD') ACDATE, ADDATE  FROM MEMBER \r\n" + 
				" WHERE ADDATE IS NULL\r\n" + 
				" AND MEM_NO = ?";
		return jdbc.selectOne(sql, param,MemberVo.class);
	}
	
	public void memberDelete(int memberNo) {
		
		String sql = " UPDATE MEMBER SET ADDATE =sysdate \r\n" + 
				"  WHERE MEM_NO ="+memberNo;
		 jdbc.update(sql);
	}
	
	
	public List<MemberVo> memberList(){
		
		String sql ="SELECT MEM_NO, MEM_NAME, MEM_ID, MEM_PASS, MEM_REGNO1, MEM_REGNO2,\r\n" + 
				" MEM_HP, MEM_CARDNO, TO_CHAR(ACDATE,'YYYY.MM.DD') ACDATE, ADDATE  FROM MEMBER\r\n" + 
				" WHERE ADDATE IS NULL\r\n" + 
				" ORDER BY MEM_NO";
		return jdbc.selectList(sql, MemberVo.class);
	}
	
	
}
