package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;
import vo.CartVo;
import vo.MemberVo;

public class CartDao {
	private static CartDao instance = null;

	private CartDao() {

	}

	public static CartDao getInstance() {
		if (instance == null) {
			instance = new CartDao();
		}
		return instance;
	}
	
	JDBCUtil jdbc =JDBCUtil.getInstance();
	
	// 결제 총금액 조회
	public List<CartVo> cartAmtResult(List<Object> param) {
		String sql = "SELECT C.CART_AMT\r\n" + 
				"  FROM CART C\r\n" + 
				" WHERE CART_NO = ?";
		return jdbc.selectList(sql, param, CartVo.class);
	}
	
	// 결제 총금액 삽입
	public void cartAmt(List<Object> param) {
		String sql = "UPDATE CART \r\n" + 
				"SET CART_AMT = (SELECT SUM(P.PROD_PRICE * CP.CART_QTY)\r\n" + 
				"                  FROM CART_PROD CP\r\n" + 
				"                  JOIN PROD P ON P.PROD_ID = CP.PROD_ID\r\n" + 
				"                 WHERE CP.CART_NO = ?)\r\n" + 
				"WHERE CART_NO = ?";
		
		jdbc.update(sql, param);
		
	}
	
	// 결제 후, cart_no insert
	public void cartNoInsert2(List<Object> param) {
		
		String sql = "INSERT INTO CART (CART_NO, MEM_NO) \r\n"
				+ "       VALUES ((SELECT NVL(MAX(CART_NO), 0) + 1\r\n"
				+ "                 FROM CART), ? )";
		int result = jdbc.update(sql, param);
		
		jdbc.update(sql, param);
		
	}
	
}
