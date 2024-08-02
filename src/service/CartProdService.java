package service;

import java.util.List;

import dao.CartProdDao;
import dao.MemberDao;
import util.JDBCUtil;

public class CartProdService {
	private static CartProdService instance = null;

	private CartProdService() {

	}

	public static CartProdService getInstance() {
		if (instance == null) {
			instance = new CartProdService();
		}
		return instance;
	}
	CartProdDao cartProdDao = CartProdDao.getInstance();
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public void cartProd(List<Object> param) {
		
		cartProdDao.createCartProd(param);
		
	}
	
}
