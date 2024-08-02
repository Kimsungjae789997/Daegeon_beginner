package service;

import java.util.List;

import dao.PayDao;
import vo.PayListVo2;
import vo.PayVo;

public class PayService {
	private static PayService instance = null;

	private PayService() {

	}

	public static PayService getInstance() {
		if (instance == null) {
			instance = new PayService();
		}
		return instance;
	}
	
	PayDao payDao = PayDao.getInstance();
	
	public void payInsert(List<Object> param) {
		
		payDao.payInsert(param);
		
	}
	
	public List<PayListVo2> payList(List<Object> param) {
		return payDao.payList(param);
	}

}
