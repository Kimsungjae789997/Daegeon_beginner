package service;

import java.util.List;

import controller.MainController;
import dao.CartDao;
import dao.MemberDao;
import vo.CartVo;
import vo.MemberVo;

public class CartService {
	private static CartService instance = null;

	private CartService() {

	}

	public static CartService getInstance() {
		if (instance == null) {
			instance = new CartService();
		}
		return instance;
	}
	
	MemberDao memberDao = MemberDao.getInstance();
	CartDao cartDao = CartDao.getInstance();
	
	// 결제 총금액 삽입
	public void cartAmt(List<Object> param) {
		cartDao.cartAmt(param);
	}
	
	// 결제 총금액 조회
	public List<CartVo> cartAmtResult(List<Object> param) {
		return cartDao.cartAmtResult(param);
	}
	// cartNo insert2
	public void cartNoInsert2(List<Object> param) {
		
		cartDao.cartNoInsert2(param);
		
		MemberVo memberLogin = (MemberVo) MainController.sessionStorage.get("member");
		int cartNo = memberDao.selectCartNo(memberLogin.getMem_no());
		MainController.sessionStorage.put("cartNo", cartNo);
	}
}
