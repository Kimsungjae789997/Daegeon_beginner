package dao;

import java.util.List;

import util.JDBCUtil;
import vo.PayListVo2;

public class PayDao {
	private static PayDao instance = null;

	private PayDao() {

	}

	public static PayDao getInstance() {
		if (instance == null) {
			instance = new PayDao();
		}
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public void payInsert(List<Object> param) {
		String sql = " INSERT INTO PAY (PAY_NO, PAY_DATE, CART_NO)\r\n" + 
				"            VALUES ((SELECT NVL(MAX(PAY_NO), 0) +1\r\n" + 
				"                       FROM PAY)\r\n" + 
				"                    , SYSDATE, ?)";
		
		jdbc.update(sql,param);
	}
		
	public List<PayListVo2> payList(List<Object> param) {
		String sql = "SELECT * \r\n" + 
				"    FROM V_PAY_LIST\r\n" + 
				"    WHERE MEM_NO = ? \r\n" + 
				"      AND ROWNUM BETWEEN ? AND ?"; // VIEW 테이블을 조회하는 쿼리
	    return jdbc.selectList(sql, param, PayListVo2.class);
	}
}
