package service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import controller.MainController;
import dao.MemberDao;
import util.JDBCUtil;
import vo.MemberVo;

public class MemberService {
	private static MemberService instance = null;

	private Set<String> memberIds;

	private MemberService() {
		memberIds = new HashSet<>();
	}

	public static MemberService getInstance() {
		if (instance == null) {
			instance = new MemberService();
		}
		return instance;
	}

	MemberDao memberDao = MemberDao.getInstance();
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	
	public boolean memberLogin(List<Object> param) {

		MemberVo memberLogin = memberDao.memberLogin(param);

		if (memberLogin == null) {
//			System.out.println("로그인이 정상적으로 처리 되지 않았습니다. 아이디와 비밀번호를 확인하여 재시도 해주세요.");
			return false;
		}

		int cartResult = memberDao.createLoginUserCart(memberLogin.getMem_no());
		
		// insert & update 가 실패하였을경우 0으로 반환된다.
		// insert의 경우 성공하면 1
		// update의 경우 성공하면 업데이트된 row수가 반환
		if (cartResult < 1) {
			System.out.println("장바구니 생성이 처리되지 않았습니다.");
			return false;
		}
		int cartNo = memberDao.selectCartNo(memberLogin.getMem_no());

		MainController.sessionStorage.put("member", memberLogin);
		MainController.sessionStorage.put("cartNo", cartNo);

		return true;
	}

	public void memberInsert(List<Object> param) {
		memberDao.memberInsert(param);
	}

	public int selectCount(String memberId) {
		return memberDao.selectCount(memberId);
	}

	public MemberVo findMemberId(List<Object> param) {
		MemberVo memberFindId = memberDao.findMemberId(param);
		return memberFindId;
	}
	public MemberVo findMemberPass(List<Object> param) {
		MemberVo memberFindPass = memberDao.findMemberPass(param);
		return memberFindPass;
	}
	
	public void memberDelete(int memberNo) {
		memberDao.memberDelete(memberNo);
	}
	
	
	public List<MemberVo> memberList(){
		return memberDao.memberList();
	}

	public void passUpdate(List<Object> param) {
		memberDao.passUpdate(param);
		
	}

	public void memberWith(List<Object> param) {

			memberDao.memberWith(param);
		
	}

	public MemberVo memberDetail(List<Object> param) {
		
		return memberDao.memberDetail(param);
	}

	public void nameUpdate(List<Object> param) {
		memberDao.nameUpdate(param);
		
	}

	public void hpUpdate(List<Object> param) {
		memberDao.hpUpdate(param);
		
	}

	public void CardnoUpdate(List<Object> param) {
		memberDao.CardnoUpdate(param);
		
	}

}
