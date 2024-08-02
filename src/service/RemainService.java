package service;

import java.util.List;

import dao.RemainDao;
import vo.RemainVo;

public class RemainService {
private static RemainService instance = null;

private RemainService() {
}

public static RemainService getInstance() {

	if (instance == null) {
		instance = new RemainService();
	}
	return instance;
}
	RemainDao RemDao = RemainDao.getInstance();
	
	public List<RemainVo> remainList(){
		
		return RemDao.remainList();
	}

	public void remainInsert(List<Object> param) {
		
		RemDao.remainInsert(param);
		
	}

	public void remainUpdate(List<Object> param, int sel) {
		RemDao.remainUpdate(param,sel);
		
	}

	public void remainDelete(int prod_id2) {
		RemDao.remainDelete(prod_id2);
		
	}


}
