package service;

import java.util.List;
import java.util.Map;

import controller.MainController;
import dao.AdminDao;
import vo.AdminVo;

public class AdminService {
	private static AdminService instance = null;

	private AdminService() {

	}

	public static AdminService getInstance() {
		if (instance == null) {
			instance = new AdminService();
		}
		return instance;
	}
	
	AdminDao adminDao = AdminDao.getInstance();
	
	public boolean adminLogin(List<Object> param) {
		AdminVo adminLogin = adminDao.adminLogin(param);
		// 아이디, 비밀번호 일치시 리턴 없음
		
		// 불일치시 리턴
		if (adminLogin == null) return false;
		// 로그인 성공시
		MainController.sessionStorage.put("adminLogin", adminLogin); return true;
		
	}
	
}
