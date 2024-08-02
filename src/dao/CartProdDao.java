package dao;

import java.util.List;

import util.JDBCUtil;

public class CartProdDao {
	private static CartProdDao instance = null;

	private CartProdDao() {

	}

	public static CartProdDao getInstance() {
		if (instance == null) {
			instance = new CartProdDao();
		}
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	
	public void createCartProd(List<Object> param) {
		String sql = "INSERT INTO CART_PROD (PROD_ID, CART_NO, CART_QTY)\r\n" + 
				"       VALUES (?, TO_NUMBER(?), ?)";
		
		jdbc.update(sql, param);
	}



}
