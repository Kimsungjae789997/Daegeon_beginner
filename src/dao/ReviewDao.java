package dao;

import java.util.List;

import util.JDBCUtil;
import vo.ReviewVo;

public class ReviewDao {
	private static ReviewDao instance = null;

	private ReviewDao() {

	}

	public static ReviewDao getInstance() {
		if (instance == null) {
			instance = new ReviewDao();
		}
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public List<ReviewVo> reviewListGetBuyer(List<Object> param) {
		String sql = "SELECT B.BUYER_NO, R.REV_NO, R.REV_GRADE, TO_CHAR(R.REV_DATE,'YYYY.MM.DD') REV_DATE, R.REV_TITLE, R.REV_CONTENT\r\n" + 
				" FROM PROD P\r\n" + 
				" JOIN BUYER B ON P.BUYER_NO = B.BUYER_NO\r\n" + 
				" JOIN CART_PROD CP ON P.PROD_ID = CP.PROD_ID\r\n" + 
				" JOIN PAY PA ON CP.CART_NO = PA.CART_NO\r\n" + 
				" JOIN REVIEW R ON PA.PAY_NO = R.PAY_NO\r\n" + 
				" WHERE B.BUYER_NO = ?";
		return jdbc.selectList(sql, param, ReviewVo.class);
	}
	
	public List<ReviewVo> reviewList(List<Object> param) {
		
		String sql = " SELECT * FROM (\r\n" + 
				" SELECT ROWNUM RN, D.*\r\n" + 
				" FROM (\r\n" + 
				" SELECT REV_NO, REV_GRADE, TO_CHAR(REV_DATE,'YYYY.MM.DD HH24:MI:SS') REV_DATE, REV_TITLE, REV_CONTENT, PAY_NO, MEM_NO\r\n" + 
				" FROM REVIEW A\r\n" + 
				" WHERE REV_DEL IS NULL  \r\n" + 
				" ORDER BY REV_NO \r\n" + 
				" ) D\r\n" + 
				" ) WHERE RN BETWEEN ? AND ? ";
		return jdbc.selectList(sql, param, ReviewVo.class);
	}

	public void reviewDelete(int reviewNo) {
		
		String sql = "  UPDATE REVIEW  \r\n" + 
				"  SET REV_DEL = 'Y'  \r\n" + 
				"  WHERE REV_NO ="+reviewNo;
		jdbc.update(sql);
		
	}

	public void reviewInsert(List<Object> param) {
		
		
		String sql ="INSERT INTO REVIEW(REV_NO, REV_GRADE, REV_DATE, REV_TITLE, REV_CONTENT, PAY_NO, REV_DEL)\r\n" + 
				"  VALUES ((SELECT NVL(MAX(REV_NO),0)+1 FROM REVIEW),?,SYSDATE,?,?,?,'')";
		jdbc.update(sql, param);		
		
		}
	

	
	public ReviewVo check(List<Object> param) {
		String sql = "SELECT C.PAY_NO\r\n" + 
				" FROM CART A INNER JOIN MEMBER B ON (A.MEM_NO = B.MEM_NO)\r\n" + 
				" INNER JOIN PAY C ON (A.CART_NO = C.CART_NO)\r\n" + 
				" WHERE B.MEM_NO = ?";
		return jdbc.selectOne(sql, param, ReviewVo.class);
	}

	public ReviewVo check2(List<Object> chkList) {
		String sql = "  SELECT REV_NO\r\n" + 
				" FROM REVIEW\r\n" + 
				" WHERE MEM_NO = ?\r\n" + 
				" AND REV_NO = ? ";
		return jdbc.selectOne(sql, chkList, ReviewVo.class);
	}
	
	public void reviewUpdate(List<Object> param, int sel) {
		String sql = "UPDATE REVIEW "
				+ "  SET ";
	
		if(sel ==1||sel==4) {
			sql+=" REV_GRADE = ?";
			if(sel==4) sql+=",";
		}
		if(sel ==2||sel==4) {
			sql+=" REV_TITLE = ?";
			if(sel==4) sql+=",";
		}
		if(sel ==3||sel==4) {
			sql+=" REV_CONTENT = ?";
		}
		sql+=" WHERE REV_NO = ?";
		
		jdbc.update(sql, param);
		
	}

	public List<ReviewVo> reviewDetail(List<Object> param) {
		String sql ="SELECT REV_NO, REV_GRADE, TO_CHAR(REV_DATE,'YYYY.MM.DD HH24:MI:SS') REV_DATE, REV_TITLE, REV_CONTENT, PAY_NO, MEM_NO\r\n" + 
				" FROM REVIEW A\r\n" + 
				" WHERE REV_DEL IS NULL  \r\n" + 
				" AND MEM_NO =?\r\n" + 
				" ORDER BY REV_NO";
		return jdbc.selectList(sql, param, ReviewVo.class);
	}
	
}
