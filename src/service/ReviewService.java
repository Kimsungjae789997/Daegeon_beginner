package service;

import java.util.List;

import dao.ReviewDao;
import vo.ReviewVo;

public class ReviewService {
	private static ReviewService instance = null;

	private ReviewService() {

	}

	public static ReviewService getInstance() {
		if (instance == null) {
			instance = new ReviewService();
		}
		return instance;
	}
	
	ReviewDao reviewDao = ReviewDao.getInstance();
	
	public List<ReviewVo> reviewListGetBuyer(List<Object> param) {
		return reviewDao.reviewListGetBuyer(param);
	}
	
	public List<ReviewVo> reviewList(List<Object> param) {
		
		return reviewDao.reviewList(param);
	}

	public void reviewDelete(int reviewNo) {
		
		reviewDao.reviewDelete(reviewNo);
		
	}

	public void reviewInsert(List<Object> param) {
		reviewDao.reviewInsert(param);
		
	}

	public boolean check(List<Object> param) {
		ReviewVo review = reviewDao.check(param);
		if(review == null) return false;
		else return true;
	}

	public void reviewUpdate(List<Object> param, int sel) {
		reviewDao.reviewUpdate(param,sel);
		
	}

	public boolean check2(List<Object> chkList) {
		ReviewVo free = reviewDao.check2(chkList);
		if(free == null) return false;
		else return true;
	}

	public List<ReviewVo> reviewDetail(List<Object> param) {
		
		return reviewDao.reviewDetail(param);
	}
}
