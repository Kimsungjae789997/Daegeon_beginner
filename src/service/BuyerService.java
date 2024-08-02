package service;

import java.util.List;

import controller.MainController;
import dao.BuyerDao;
import vo.BuyerListPVo;
import vo.BuyerListPVo2;
import vo.BuyerListRVo;

public class BuyerService {
	private static BuyerService instance = null;

	private BuyerService() {

	}

	public static BuyerService getInstance() {
		if (instance == null) {
			instance = new BuyerService();
		}
		return instance;
	}
	
	BuyerDao buyerDao = BuyerDao.getInstance();
	
	// 맛집 자세히 보기
	public List<BuyerListPVo2> buyerRDetail(List<Object> param) {
		MainController.sessionStorage.put("buyerNo", param);
		return buyerDao.buyerRDetail(param);
	}
	
	// 맛집 리스트 찾기
	public List<BuyerListRVo> buyerRSearch(List<Object> param, int sel){
		return buyerDao.buyerRSearch(param, sel);
	}
	
	// 맛집 리스트
	public List<BuyerListRVo> BuyerListr() {
		return buyerDao.BuyerListr();
	}
	
	// 명소 자세히 보기
	public List<BuyerListPVo2> buyerPDetail(List<Object> param) {
//		MainController.sessionStorage.put("buyerNo", param);
		return buyerDao.buyerPDetail(param);
	}

	// 명소 리스트 찾기
	public List<BuyerListPVo> buyerPSearch(List<Object> param, int sel){
		return buyerDao.buyerPSearch(param, sel);
	}
	
	// 명소 리스트
	public List<BuyerListPVo> BuyerListp() {
		return buyerDao.BuyerListp();
	}

}
