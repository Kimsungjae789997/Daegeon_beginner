package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.AdminService;
import service.BuyerService;
import service.CartProdService;
import service.CartService;
import service.MemberService;
import service.PayService;
import service.RemainService;
import service.ReviewService;
import util.ScanUtil;
import util.View;
import vo.BuyerListPVo;
import vo.BuyerListPVo2;
import vo.BuyerListRVo;
import vo.CartVo;
import vo.MemberVo;
import vo.PayListVo2;
import vo.RemainVo;
import vo.ReviewVo;

public class MainController  {
	static public Map<String, Object> sessionStorage = new HashMap<>();
	
	AdminService adminService = AdminService.getInstance();
	MemberService memberService = MemberService.getInstance();
	BuyerService buyerService = BuyerService.getInstance();
	CartService cartService = CartService.getInstance();
	CartProdService cartProdService = CartProdService.getInstance();
	PayService payService = PayService.getInstance();
	ReviewService reviewService = ReviewService.getInstance();
	RemainService remainService = RemainService.getInstance();
	
	public static void main(String[] args) {
		new MainController().start();
	}
	
	private void start() {
		View view = View.HOME; // 메인페이지
		while (true) {
			switch (view) {
			case HOME:
				view = home();
				break;
			case ADMIN_LOGIN:
				view = adminLogin();
				break;
			case ADMIN_HOME:
				view = adminHome();
				break;
			case MEMBER_LIST:
				view = memberList();
				break;
			case MEMBER_INFO:
				view = memberInfo();
				break;
			case MEMBER_LOGIN:
				view = memberLogin();
				break;
			case MEMBER_DETAIL:
				view = memberDetail();
				break;
			case MEMBER_UPDATE:
				view = memberUpdate();
				break;
			case MEMBER_DELETE:
				view = memberDelete();
				break;
			case MEMBER_WITHDRAWAL:
				view = memberWith();
				break;
			case PASS_UPDATE:
				view = passUpdate();
				break;
			case NAME_UPDATE:
				view = nameUpdate();
				break;
			case HP_UPDATE:
				view = hpUpdate();
				break;
			case CARDNO_UPDATE:
				view = CardnoUpdate();
				break;
			case MEMBER_FINDID:
				view = memberFindId();
				break;
			case MEMBER_FINDPASS:
				view = memberFindPass();
				break;
			case MEMBER_INSERT:
				view = memberInsert();
				break;
			case MEMBER_HOME:
				view = memberHome();
				break;
			case BUYER_LISTP:
				view = buyerListp();
				break;
			case BUYER_LISTR:
				view = buyerListr();
				break;
			case BUYER_SEARCHP:
				view = buyerPSearch();
				break;
			case BUYER_SEARCHR:
				view = buyerRSearch();
				break;
			case BUYER_DETAILP:
				view = buyerDetailP();
				break;
			case BUYER_DETAILR:
				view = buyerDetailR();
				break;
			case PAY_INSERTP:
				view = payInsertP();
				break;
			case PAY_INSERTR:
				view = payInsertR();
				break;
			case PAY:
				view = payInsert();
				break;
			case PAY_RESULT:
				view = payResult();
				break;
			case REVIEW_ADLIST:
				view = reviewAdlist();
				break;
			case REVIEW_MEMLIST:
				view = reviewMemlist();
				break;
			case REVIEW_DELETE:
				view = reviewDelete();
				break;
			case REVIEW_DELETE2:
				view = reviewDelete2();
				break;
			case REVIEW_UPDATE:
				view = reviewUpdate();
				break;
			case REVIEW_INSERT:
				view = reviewInsert();
				break;
			case REVIEW_DETAIL:
				view = reviewDetail();
				break;
			case REMAIN_LIST:
				view = remainList();
				break;
			case REMAIN_INSERT:
				view = remainInsert();
				break;
			case REMAIN_UPDATE:
				view = remainUpdate();
				break;
			case REMAIN_DELETE:
				view = remainDelete();
				break;
			case PAY_LIST:
				view = payList();
				break;
			case BUYER_REVIEW:
				view = buyerReview();
				break;
			default:
				break;
			}
		}
	}
	
	private View reviewDetail() {
		System.out.println("======================자신이 작성했던 리뷰==============================");
		MemberVo member = (MemberVo) MainController.sessionStorage.get("member");
		List<Object> param = new ArrayList();
		param.add(member.getMem_no());
		List<ReviewVo> list = reviewService.reviewDetail(param);
		for (ReviewVo reviewVo : list) {
			System.out.println(reviewVo);
		}

		System.out.println("");
		System.out.println("1. 리뷰 리스트로 돌아가기 ");
		int sel = ScanUtil.nextInt("메뉴 : ");
		if (sel == 1) {
			return View.REVIEW_MEMLIST;
		} else {

			return View.MEMBER_HOME;
		}
	}

	// 거래처 > 리뷰
	private View buyerReview() {
		/*{
		 * buyerList=[1	성심당 본점          	10,000원 쿠폰	9000	대전 중구 대종로480번길 15, 2	태평소국밥          	10,000원 쿠폰	9000	대전 유성구 온천동로65번길 50, 3	오씨칼국수           	10,000원 쿠폰	9000	대전 동구 옛신탄진로 13, 4	진로집                 	10,000원 쿠폰	9000	대전 중구 중교로 45-5, 5	한영식당              	10,000원 쿠폰	9000	대전 중구 계룡로874번길 6, 6	태화장             	10,000원 쿠폰	9000	대전 동구 중앙로203번길 78, 7	토우베이크하우스	10,000원 쿠폰	9000	대전 유성구 신성남로111번길 28 1층, 8	엠엠엠          	10,000원 쿠폰	9000	대전 동구 대전천북로112번길 2 1층, 9	돈락            	10,000원 쿠폰	9000	대전 서구 도안중로305번안길 7-9 1층, 10	매봉식당           	10,000원 쿠폰	9000	대전 대덕구 계족로664번길 113], 
		 * buyerNo=[1], 
		 * member=회원번호 : 10  회원명 : 지성  아이디 : a010  비밀번호 : 1234  주민번호 : 770227-1000000  전화번호 : 010-1234-5678  카드번호 : 0000-1111-2222-3333  가입일 : 2024/02/01, cartNo=97
		 * }
		 */
		
		int buyerNo = 0;
		
		if(MainController.sessionStorage.get("buyerNo") instanceof ArrayList) {
			ArrayList<Object> arr = (ArrayList<Object>)MainController.sessionStorage.get("buyerNo");
			buyerNo = (int)arr.get(0);
		}else {
			buyerNo = (int)MainController.sessionStorage.get("buyerNo");
		}
		
		List<Object> param = new ArrayList<Object>();
		
		param.add(buyerNo);
		
		List<ReviewVo> reviews = reviewService.reviewListGetBuyer(param);
		
		for (ReviewVo buyer : reviews) {
			System.out.println(buyer);
		}
		
		reviewService.reviewListGetBuyer(param);

	    System.out.println("1.홈");
		
		int sel = ScanUtil.nextInt("선택 : ");

		switch (sel) {
		case 1:
			return View.BUYER_LISTP;
		case 2:
			return View.MEMBER_HOME;
		default:
			System.out.println("잘못입력했습니다. 다시 선택해주세요.");
			return View.BUYER_DETAILP;
		}
		
	}

	// 관리자 > 재고 삭제
	private View remainDelete() {
		int prod_id2 = (int) sessionStorage.get("prod_id2");

		String yn = ScanUtil.nextLine("정말로 삭제 하시겠습니까? ( yes 와 no )");
		if (yn.equals("no")) {
			return View.REMAIN_LIST;
		}
		remainService.remainDelete(prod_id2);
		return View.REMAIN_LIST;
	}

	// 관리자 > 재고 수정
	private View remainUpdate() {
		int prod_id = (int) sessionStorage.get("prod_id");
		System.out.println("수정 할꺼 선택하세요");
		System.out.println("1. 매입 수량");
		System.out.println("2. 매출 수량");
		System.out.println("3. 관리자 홈");

		List<Object> param = new ArrayList();
		int sel = ScanUtil.menu();
		if (sel == 1) {
			int remain_i = ScanUtil.nextInt("매입 수량 : ");
			param.add(remain_i);
			param.add(remain_i);
		}
		if (sel == 2) {
			int remain_o = ScanUtil.nextInt("매출 수량 : ");
			param.add(remain_o);
			param.add(remain_o);
		}
		if (sel == 3) {
			return View.ADMIN_HOME;
		}

		param.add(prod_id);
		remainService.remainUpdate(param, sel);

		return View.REMAIN_LIST;
	}

	// 관리자 > 재고 등록
	private View remainInsert() {
		List<Object> param = new ArrayList();

		String remain_year = ScanUtil.nextLine("년도 :");
		int remain_j_00 = ScanUtil.nextInt("기초수량 :");
		int remain_i = ScanUtil.nextInt("매입 수량 : ");
		int remain_o = ScanUtil.nextInt("매출 수량 : ");
		int remain_j_99 = ScanUtil.nextInt("현재고 :");

		param.add(remain_year);
		param.add(remain_j_00);
		param.add(remain_i);
		param.add(remain_o);
		param.add(remain_j_99);
		remainService.remainInsert(param);

		return View.REMAIN_LIST;
	}

	// 관리자 > 재고 조회
	private View remainList() {
		List<RemainVo> remainList = remainService.remainList();
		for (RemainVo remainvo : remainList) {
			System.out.println(remainvo);
		}

		System.out.println("1. 재고 등록");
		System.out.println("2. 재고 수정");
		System.out.println("3. 재고 삭제");
		System.out.println("4. 관리자 홈");

		int sel = ScanUtil.nextInt("메뉴 선택 : ");
		switch (sel) {
		case 1:
			return View.REMAIN_INSERT;
		case 2:
			int prod_id = ScanUtil.nextInt("수정할 재고 번호 입력 : ");
			sessionStorage.put("prod_id", prod_id);
			return View.REMAIN_UPDATE;
		case 3:
			int prod_id2 = ScanUtil.nextInt("삭제할 재고 번호 입력 : ");
			sessionStorage.put("prod_id2", prod_id2);
			return View.REMAIN_DELETE;
		case 4:
			return View.ADMIN_HOME;
		default:
			return View.REMAIN_LIST;
		}
	}

	// 관리자 > 리뷰 삭제
	private View reviewDelete() {
		int reviewNo = (int) sessionStorage.get("reviewNo");

		String yn = ScanUtil.nextLine("정말로 삭제 하시겠습니까? ( yes 와 no ) : ");
		if (yn.equals("no")) {
			return View.REVIEW_ADLIST;
		}
		reviewService.reviewDelete(reviewNo);
		return View.REVIEW_ADLIST;
	}

	// 관리자 > 리뷰 조회
	private View reviewAdlist() {
			System.out.println("______               _                   _      _       _   \r\n" + 
					"| ___ \\             (_)                 | |    (_)     | |  \r\n" + 
					"| |_/ /  ___ __   __ _   ___ __      __ | |     _  ___ | |_ \r\n" + 
					"|    /  / _ \\\\ \\ / /| | / _ \\\\ \\ /\\ / / | |    | |/ __|| __|\r\n" + 
					"| |\\ \\ |  __/ \\ V / | ||  __/ \\ V  V /  | |____| |\\__ \\| |_ \r\n" + 
					"\\_| \\_| \\___|  \\_/  |_| \\___|  \\_/\\_/   \\_____/|_||___/ \\__|");
			System.out.println("");
			System.out.println("");
			int page = 1;
			if (sessionStorage.containsKey("page")) {
				page = (int) sessionStorage.get("page");
			}
			int startNum = 1 + 10 * (page - 1);
			int endNum = 10 * page;

			List<Object> param = new ArrayList();
			param.add(startNum);
			param.add(endNum);
			List<ReviewVo> list = reviewService.reviewList(param);
			for (ReviewVo reviewVo : list) {
				System.out.println(reviewVo);
			}
			System.out.println("");
			System.out.println("");
			System.out.println("1. 다음페이지");
			System.out.println("2. 이전페이지");
			System.out.println("3. 삭제");
			System.out.println("4. 관리자 홈");
			System.out.println("");
			System.out.println("");
			int sel = ScanUtil.nextInt("메뉴 : ");

			if (sel == 1) {
				page++;
				sessionStorage.put("page", page);
				return View.REVIEW_ADLIST;
			}

			else if (sel == 2) {
				if (page != 1)
					page--;
				sessionStorage.put("page", page);
				return View.REVIEW_ADLIST;
			} else if (sel == 3) {
				int reviewNo = ScanUtil.nextInt("삭제할 리뷰 번호 입력 : ");
				sessionStorage.put("reviewNo", reviewNo);
				return View.REVIEW_DELETE;
			} else if (sel == 4) {
				sessionStorage.remove("page");
				return View.ADMIN_HOME;
			} else {
				return View.REVIEW_ADLIST;
			}
		}

	// 회원 > 구매 내역
	private View payList() {
		
		MemberVo memberVo = (MemberVo) sessionStorage.get("member");
		
		int mem_no = memberVo.getMem_no();
		int page = 1;
		if (sessionStorage.containsKey("page")) {
			page = (int) sessionStorage.get("page");
		}
		int startNum = 1 + 5 * (page - 1);
		int endNum = 5 * page;

		List<Object> param = new ArrayList();
		param.add(mem_no);
		param.add(startNum);
		param.add(endNum);
		
		payService.payList(param);
		
		System.out.println("\n==================================== 구매 내역 ==============================================");
		System.out.println("회원번호\t결제번호 \t구매일\t\t\t명소/맛집명\t\t상품\t\t수량\t금액");
		
		List<PayListVo2> list = payService.payList(param);
		for (PayListVo2 payVo : list) {
			System.out.println(payVo);
		}
		System.out.println();

		System.out.println("1. 다음페이지");
		System.out.println("2. 이전페이지");
		System.out.println("3. 회원 홈");

		int sel = ScanUtil.nextInt("메뉴 : ");

		if (sel == 1) {
			page++;
			sessionStorage.put("page", page);
			return View.PAY_LIST;
		}

		else if (sel == 2) {
			if (page != 1)
				page--;
			sessionStorage.put("page", page);
			return View.PAY_LIST;
		} 
		else if(sel ==3) {
				sessionStorage.remove("page");
				return View.MEMBER_HOME;
			}
		 else {
			return View.PAY_LIST;
		}
	}

	// 회원 > 리뷰 수정
	private View reviewUpdate() {
			MemberVo user = (MemberVo) sessionStorage.get("member");
			int rev_no = (int) sessionStorage.get("rev_no");
			
			List<Object> chkList = new ArrayList();
			chkList.add(user.getMem_no());
			chkList.add(rev_no);
			boolean chk = reviewService.check2(chkList);
			if(!chk) {
				System.out.println();
				System.out.println("리뷰 수정 권한이 없습니다");
				return View.REVIEW_MEMLIST;
			}
			
			System.out.println("리뷰 수정 권한이 있습니다.");
			System.out.println("");
			System.out.println("수정 할꺼 선택하세요");
			System.out.println("1. 별점");
			System.out.println("2. 제목");
			System.out.println("3. 내용");
			System.out.println("4. 별점,제목,내용");
			System.out.println("5. 이전 페이지");
			
			List<Object> param = new ArrayList();
			int sel = ScanUtil.menu();
			if(sel ==1||sel == 4) {
			int grade = ScanUtil.nextInt("별점 : ");
				param.add(grade);
			}
			if(sel==2|| sel==4) {
			String title =ScanUtil.nextLine("제목 : ");
				param.add(title);
			}
			if(sel==3|| sel==4) {
				String content =ScanUtil.nextLine("내용 : ");
					param.add(content);
				}
			
			param.add(rev_no);
			reviewService.reviewUpdate(param, sel);
			
			return View.REVIEW_MEMLIST;
		}
	
	// 회원 > 리뷰 등록
	private View reviewInsert() {
		
			
		MemberVo reviewVo = (MemberVo) sessionStorage.get("member");

		List<Object> chkList = new ArrayList();
		chkList.add(reviewVo.getMem_no());
		boolean chk = reviewService.check(chkList);
		if (!chk) {
			System.out.println("");
			System.out.println("상품을 구매하지 않으셔서 리뷰를 등록할 수 없습니다.");
			return View.REVIEW_MEMLIST;
		}

		List<Object> param = new ArrayList();

		int rev_grade = ScanUtil.nextInt("별점(1,2,3,4,5) :");
		String rev_title = ScanUtil.nextLine("제목 :");
		String rev_content = ScanUtil.nextLine("내용 :");
		int pay_no = ScanUtil.nextInt("결제번호 :");

		param.add(rev_grade);

		param.add(rev_title);
		param.add(rev_content);
		param.add(pay_no);
		reviewService.reviewInsert(param);

		return View.REVIEW_MEMLIST;
		
		}

	// 회원 > 리뷰 조회
	private View reviewMemlist() {
		System.out.println("______               _                   _      _       _   \r\n" + 
				"| ___ \\             (_)                 | |    (_)     | |  \r\n" + 
				"| |_/ /  ___ __   __ _   ___ __      __ | |     _  ___ | |_ \r\n" + 
				"|    /  / _ \\\\ \\ / /| | / _ \\\\ \\ /\\ / / | |    | |/ __|| __|\r\n" + 
				"| |\\ \\ |  __/ \\ V / | ||  __/ \\ V  V /  | |____| |\\__ \\| |_ \r\n" + 
				"\\_| \\_| \\___|  \\_/  |_| \\___|  \\_/\\_/   \\_____/|_||___/ \\__|");
		System.out.println("");
		System.out.println("");
		int page = 1;
		if (sessionStorage.containsKey("page")) {
			page = (int) sessionStorage.get("page");
		}
		int startNum = 1 + 5 * (page - 1);
		int endNum = 5 * page;

		List<Object> param = new ArrayList();
		param.add(startNum);
		param.add(endNum);
		List<ReviewVo> list = reviewService.reviewList(param);
		for (ReviewVo reviewVo : list) {
			System.out.println(reviewVo);
		}
		System.out.println("");
		System.out.println("1. 다음페이지");
		System.out.println("2. 이전페이지");
		System.out.println("3. 리뷰 등록");
		System.out.println("4. 자신의 리뷰 보기");
		System.out.println("5. 리뷰 삭제");
		System.out.println("6. 리뷰 수정");
		System.out.println("7. 회원 홈");
		System.out.println("");
		System.out.println("");
		int sel = ScanUtil.nextInt("메뉴 : ");

		if (sel == 1) {
			page++;
			sessionStorage.put("page", page);
			return View.REVIEW_MEMLIST;
		}

		else if (sel == 2) {
			if (page != 1)
				page--;
			sessionStorage.put("page", page);
			return View.REVIEW_MEMLIST;
		} else if (sel == 3) {
			return View.REVIEW_INSERT;
		}else if (sel == 4) {
				return View.REVIEW_DETAIL;
		} else if (sel == 5) {
			int reviewNo = ScanUtil.nextInt("삭제할 리뷰 번호 입력 : ");
			sessionStorage.put("reviewNo", reviewNo);
			return View.REVIEW_DELETE2;
		} else if (sel == 6) {
			int rev_no = ScanUtil.nextInt("수정할 리뷰 번호 입력 : ");
			sessionStorage.put("rev_no", rev_no);
			return View.REVIEW_UPDATE;
		} else if (sel == 7) {
			sessionStorage.remove("page");
			return View.MEMBER_HOME;
		} else {
			return View.REVIEW_MEMLIST;
		}

	}
	
	// 결제 결과(총금액)
	private View payResult() {
		System.out.println("\n============= 결제 완료 =============");
		Integer cartNo = (Integer) MainController.sessionStorage.get("cartNo"); // Integer로 받음
		String cartNoStr = String.valueOf(cartNo); // String으로 변환

		List<Object> param = new ArrayList();

		param.add(cartNoStr);
		
		cartService.cartAmtResult(param);
		
		List<CartVo> result = cartService.cartAmtResult(param);

		for (CartVo payAmt : result) {
			System.out.println(payAmt);
		}
		
		param.clear();

		MemberVo member = (MemberVo) MainController.sessionStorage.get("member");

		int memberNo = member.getMem_no();

		param.add(memberNo);

		cartService.cartNoInsert2(param);
		
		return View.PAY_LIST;
	}

	// 결제
	private View payInsert() {
		Integer cartNo = (Integer) MainController.sessionStorage.get("cartNo"); // Integer로 받음
		String cartNoStr = String.valueOf(cartNo); // String으로 변환

		List<Object> param = new ArrayList();

		param.add(cartNoStr);
		param.add(cartNoStr);
		cartService.cartAmt(param);

		param.clear();

		param.add(cartNoStr);
		payService.payInsert(param);


		return View.PAY_RESULT;
	}
	
	// 맛집 결제 중
	private View payInsertR() {
		
		System.out.println("\n============= 맛집 결제 =============");
	    int productNo = ScanUtil.nextInt("상품 번호  : ");
	    Integer cartNo = (Integer) MainController.sessionStorage.get("cartNo"); // Integer로 받음
	    String cartNoStr = String.valueOf(cartNo); // String으로 변환
	    int productCnt = ScanUtil.nextInt("수량  : ");
	    
	    List<Object> param = new ArrayList();
		
	    param.add(productNo);
	    param.add(cartNo);
	    param.add(productCnt);
		
	    cartProdService.cartProd(param);
	    
	    System.out.println("1. 결제");
		System.out.println("2. 돌아가기");
		
		int sel = ScanUtil.nextInt("선택 : ");

		switch (sel) {
		case 1:
			return View.PAY;
		case 2:
			return View.BUYER_DETAILP;
		default:
			System.out.println("잘못입력했습니다. 다시 선택해주세요.");
			return View.BUYER_DETAILP;
		}
	}

	// 맛집 거래처 자세히보기
	private View buyerDetailR() {
		/*
		{buyerList=[1	성심당 본점          	10,000원 쿠폰	9000	대전 중구 대종로480번길 15, 2	태평소국밥          	10,000원 쿠폰	9000	대전 유성구 온천동로65번길 50, 3	오씨칼국수           	10,000원 쿠폰	9000	대전 동구 옛신탄진로 13, 4	진로집                 	10,000원 쿠폰	9000	대전 중구 중교로 45-5, 5	한영식당              	10,000원 쿠폰	9000	대전 중구 계룡로874번길 6, 6	태화장             	10,000원 쿠폰	9000	대전 동구 중앙로203번길 78, 7	토우베이크하우스	10,000원 쿠폰	9000	대전 유성구 신성남로111번길 28 1층, 8	엠엠엠          	10,000원 쿠폰	9000	대전 동구 대전천북로112번길 2 1층, 9	돈락            	10,000원 쿠폰	9000	대전 서구 도안중로305번안길 7-9 1층, 10	매봉식당           	10,000원 쿠폰	9000	대전 대덕구 계족로664번길 113], 
		member=회원번호 : 10  회원명 : 지성  아이디 : a010  비밀번호 : 1234  주민번호 : 770227-1000000  전화번호 : 010-1234-5678  카드번호 : 0000-1111-2222-3333  가입일 : 2024/02/01, cartNo=104}
		 */
		System.out.println("\n============= 맛집 자세히 보기 =============");
		int buyerNo = ScanUtil.nextInt("맛집 번호  : ");
		MainController.sessionStorage.put("buyerNo", buyerNo);
		
		List<Object> param = new ArrayList();

		param.add(buyerNo);
		
		MemberVo member = (MemberVo) MainController.sessionStorage.get("member");
		
		int memberNo = member.getMem_no();
		
		System.out.println("\n상품번호 \t맛집 \t\t상품 \t\t할인가 \t주소");
		List<BuyerListPVo2> buyerDetailRList = buyerService.buyerRDetail(param);

		for (BuyerListPVo2 buyer : buyerDetailRList) {
			System.out.println(buyer);
		}

		System.out.println("1. 구매하기");
		System.out.println("2. 리뷰 보기");
		System.out.println("3. 맛집 리스트");
		
		int sel = ScanUtil.nextInt("선택 : ");

		switch (sel) {
		case 1:
			return View.PAY_INSERTR;
		case 2:
			return View.BUYER_REVIEW;
		case 3:
			return View.BUYER_LISTR;
		default:
			System.out.println("잘못입력했습니다. 다시 선택해주세요.");
			return View.BUYER_DETAILP;
		}
	}

	// 맛집 거래처 검색
	private View buyerRSearch() {
		
		System.out.println("\n============= 맛집 검색 =============");
		System.out.println("1. 이름 검색");
		System.out.println("2. 주소 검색");
		System.out.println("3. 분류 검색 (ex: 한식)");
		
		List<Object> param = new ArrayList();
		int sel = ScanUtil.menu();
		
		System.out.println("\n============= 검색 결과 =============");
		System.out.println("번호 \t명소 \t\t상품 \t\t할인가 \t주소");
		if(sel == 1) {
			String name = ScanUtil.nextLine("맛집이름 : ");
			param.add(name);
		}
		if(sel ==2) {
			String add = ScanUtil.nextLine("주소 : ");
			param.add(add);
		}
		if(sel ==3) {
			String add = ScanUtil.nextLine("분류 : ");
			param.add(add);
		}
		
		System.out.println("\n번호 \t맛집 \t\t상품 \t\t할인가 \t주소");
		List<BuyerListRVo> buyerRsList = buyerService.buyerRSearch(param, sel);
		for (BuyerListRVo buyerRSearch : buyerRsList) {
			System.out.println(buyerRSearch);
		}
		
		sessionStorage.put("buyerList", buyerRsList);
		
		System.out.println("1. 맛집 선택 ");
		System.out.println("2. 맛집 전체 리스트 ");
		
		int sel2 = ScanUtil.menu();
		
		switch (sel2) {
		case 1:
			return View.BUYER_DETAILR;
		case 2:
			return View.BUYER_LISTR;
		default:
			return View.BUYER_LISTR;
		}
	}

	// 맛집 리스트
	private View buyerListr() {
		
		System.out.println("\n============= 맛집 목록 =============");
		System.out.println("번호 \t맛집 \t\t상품 \t\t할인가 \t주소");
		
		List<BuyerListRVo> buyerRList = buyerService.BuyerListr();
		
		for(BuyerListRVo buyer : buyerRList) {
			System.out.println(buyer);
		}
		
		System.out.println("\n1. 검색");
		System.out.println("2. 맛집 자세히 보기");
		System.out.println("3. 홈");
		
		sessionStorage.put("buyerList", buyerRList);
		
		int sel = ScanUtil.nextInt("선택 : ");
		switch (sel) {
		case 1:
			return View.BUYER_SEARCHR;
		case 2:
			return View.BUYER_DETAILR;
		case 3:
			return View.MEMBER_HOME;
		default:
			return View.BUYER_LISTR;
		}
	}

	// 명소 결제 중
	private View payInsertP() {
		
		System.out.println("\n============= 명소 결제 =============");
	    int productNo = ScanUtil.nextInt("상품 번호  : ");
	    Integer cartNo = (Integer) MainController.sessionStorage.get("cartNo"); // Integer로 받음
	    String cartNoStr = String.valueOf(cartNo); // String으로 변환
	    int productCnt = ScanUtil.nextInt("수량  : ");
	    
	    List<Object> param = new ArrayList();
		
	    param.add(productNo);
	    param.add(cartNo);
	    param.add(productCnt);
		
	    cartProdService.cartProd(param);
	    
	    System.out.println("1. 결제");
		System.out.println("2. 돌아가기");
		
		int sel = ScanUtil.nextInt("선택 : ");

		switch (sel) {
		case 1:
			return View.PAY;
		case 2:
			return View.BUYER_DETAILP;
		default:
			System.out.println("잘못입력했습니다. 다시 선택해주세요.");
			return View.BUYER_DETAILP;
		}
	}

	// 명소 거래처 자세히보기
	private View buyerDetailP() {
		/*
		int buyerNo = ScanUtil.nextInt("맛집 번호  : ");
		sessionStorage.put("buyerNo", buyerNo);
		 */
		/*
		{
		buyerList=[11	대전오월드            	10,000원 쿠폰	9000	대전 중구 사정공원로 70, 12	대전신세계      	10,000원 쿠폰	9000	대전 유성구 엑스포로 1, 13	이응노미술관	10,000원 쿠폰	9000	대전 서구 둔산대로 157 이응노미술관, 14	옛터민속박물관	10,000원 쿠폰	9000	대전 동구 산내로 321-35, 15	대전시립미술관	10,000원 쿠폰	9000	대전 서구 둔산대로 155 둔산대공원, 16	대전중앙시장	10,000원 쿠폰	9000	대전 동구 대전로 783, 17	뿌리공원            	10,000원 쿠폰	9000	대전 중구 뿌리공원로 79 뿌리공원, 18	대청호 로하스 캠핑장	10,000원 쿠폰	9000	대전광역시 대덕구 대청로424번길 200, 19	대전아쿠아리움	10,000원 쿠폰	9000	대전 중구 보문산공원로 469, 20	티놀자 애니멀파크	10,000원 쿠폰	9000	대전 유성구 대덕대로989번길 9-51], 
		member=회원번호 : 10  회원명 : 지성  아이디 : a010  비밀번호 : 1234  주민번호 : 770227-1000000  전화번호 : 010-1234-5678  카드번호 : 0000-1111-2222-3333  가입일 : 2024/02/01, cartNo=103}
		 */
		System.out.println("\n============= 자세히 볼 명소 번호 선택 =============");
		int buyerNo = ScanUtil.nextInt("명소 번호  : ");
		MainController.sessionStorage.put("buyerNo", buyerNo);

		List<Object> param = new ArrayList();

		param.add(buyerNo);
		
		MemberVo member = (MemberVo) MainController.sessionStorage.get("member");
		
		int memberNo = member.getMem_no();
		System.out.println("\n============= 명소 상품 자세히 보기 =============");
		System.out.println("\n상품번호 \t명소 \t\t상품 \t\t할인가 \t주소");
		List<BuyerListPVo2> buyerDetailPList = buyerService.buyerPDetail(param);

		for (BuyerListPVo2 buyer : buyerDetailPList) {
			System.out.println(buyer);
		}


		System.out.println("\n1. 구매하기");
		System.out.println("2. 리뷰 보기");
		System.out.println("3. 명소 리스트");
		
		int sel = ScanUtil.nextInt("선택 : ");

		switch (sel) {
		case 1:
			return View.PAY_INSERTP;
		case 2:
			return View.BUYER_REVIEW;
		case 3:
			return View.BUYER_LISTP;
		default:
			System.out.println("잘못입력했습니다. 다시 선택해주세요.");
			return View.BUYER_DETAILP;
		}
	}

	// 명소 거래처 검색
	private View buyerPSearch() {
		
		System.out.println("\n============= 명소 검색 =============");
		System.out.println("1. 명소이름 검색");
		System.out.println("2. 주소 검색");
		System.out.println("3. 돌아가기");
		
		List<Object> param = new ArrayList();
		
		int sel = ScanUtil.menu();
		if(sel == 1) {
			String name = ScanUtil.nextLine("명소이름 : ");
			param.add(name);
		}
		if(sel ==2) {
			String add = ScanUtil.nextLine("주소 : ");
			param.add(add);
		}
		if(sel ==3) {
			return View.BUYER_LISTP;
		}
		System.out.println("\n============= 검색 결과 =============");
		List<BuyerListPVo> buyerPsList = buyerService.buyerPSearch(param, sel);
		for (BuyerListPVo buyerPSearch : buyerPsList) {
			System.out.println(buyerPSearch);
		}
		
		sessionStorage.put("buyerList", buyerPsList);
		System.out.println("\n1. 상품 선택 ");
		System.out.println("2. 명소 전체 리스트 ");
		
		int sel2 = ScanUtil.menu();
		
		switch (sel2) {
		case 1:
			return View.BUYER_DETAILP;
		case 2:
			return View.BUYER_LISTP;
		default:
			return View.BUYER_LISTP;
		}
	}

	// 명소 거래처 리스트
	private View buyerListp() {
		
		System.out.println("\n============= 명소 목록 =============");
		System.out.println("번호 \t명소 \t\t상품 \t\t할인가 \t주소");
		List<BuyerListPVo> buyerPList = buyerService.BuyerListp();
		
		for(BuyerListPVo buyer : buyerPList) {
			System.out.println(buyer);
		}
		
		System.out.println("\n1. 검색");
		System.out.println("2. 명소 자세히 보기");
		System.out.println("3. 회원 홈");
		
		sessionStorage.put("buyerList", buyerPList);
		
		
		int sel = ScanUtil.nextInt("선택 : ");
		switch (sel) {
		case 1:
			return View.BUYER_SEARCHP;
		case 2:
			return View.BUYER_DETAILP;
		case 3:
			return View.MEMBER_HOME;
		default:
			System.out.println("다시 입력해주세요");
			return View.BUYER_LISTP;
		}
	}
	
	// 회원 홈
	private View memberHome() {
		
		System.out.println("로그인이 완료되었습니다.");
		MemberVo memberVo = (MemberVo) MainController.sessionStorage.get("member");
		String memName = memberVo.getMem_name();
		System.out.println("\n============================");
		System.out.println("\n ♥ \'" + memName + "\'님 환영합니다 ♥");
		
		System.out.println("\n=========== 회원 홈 ===========");
		System.out.println("1. 명소 리스트");
		System.out.println("2. 맛집 리스트");
		System.out.println("3. 구매 내역");
		System.out.println("4. 리뷰 내역");
		System.out.println("5. 회원 정보");
		System.out.println("6. 로그아웃");
		
		int sel = ScanUtil.nextInt("메뉴 선택 : ");
		switch (sel) {
		case 1:
			return View.BUYER_LISTP;
		case 2:
			return View.BUYER_LISTR;
		case 3:
			return View.PAY_LIST;
		case 4: 
			return View.REVIEW_MEMLIST;
		case 5: 
			return View.MEMBER_INFO;
		default:
			return View.HOME;
		}
	}

	// 회원가입, 아이디 중복확인
	private View memberInsert() {

		System.out.println("\n============= 회원 가입 =============");
		boolean isDuplicateId = true;

		String memberId = null;

		while (isDuplicateId) {
			memberId = ScanUtil.nextLine("아이디: ");
			int count = memberService.selectCount(memberId);

			if (count != 0) {
				System.out.println("이미 사용 중인 아이디입니다. 다른 아이디를 입력해주세요.");
			} else {
				isDuplicateId = false;
			}
		}

		String memberPass = ScanUtil.nextLine("비밀번호: ");
		String memberName = ScanUtil.nextLine("이름: ");
		String memberRegno1 = ScanUtil.nextLine("주민번호 앞자리 (ex: 970415) : ");
		String memberRegno2 = ScanUtil.nextLine("주민번호 뒷자리 (ex: 1234567) : ");
		String memberHp = ScanUtil.nextLine("핸드폰 번호 (ex: 010-9225-4953) : ");
		String memberCardNo = ScanUtil.nextLine("카드 번호 (ex: 0000-0000-0000-0000) : ");

		List<Object> param = new ArrayList();

		param.add(memberId);
		param.add(memberPass);
		param.add(memberName);
		param.add(memberRegno1);
		param.add(memberRegno2);
		param.add(memberHp);
		param.add(memberCardNo);

		memberService.memberInsert(param);

		System.out.println("회원가입이 완료되었습니다.");
		System.out.println("로그인 해주시기 바랍니다.");

		return View.MEMBER_LOGIN;
	}
	
	// 회원 비밀번호 찾기
	private View memberFindPass() {
		System.out.println("\n============= 회원 비밀번호 찾기=============");
		String memberId = ScanUtil.nextLine("아이디: ");
		String memberName = ScanUtil.nextLine("이름: ");
		String memberHp = ScanUtil.nextLine("핸드폰 번호 (ex: 010-9225-4953) : ");

		List<Object> param = new ArrayList();
		
		param.add(memberId);
		param.add(memberName);
		param.add(memberHp);
		
		MemberVo memberFindPass = memberService.findMemberPass(param);
		
		if(memberFindPass == null) {
			System.out.println("찾는 유저의 정보가 없습니다. 확인하고 재시도 해주세요.");
		}else {
			System.out.println("회원님의 비밀번호는 '" + memberFindPass.getMem_pass() + "' 입니다.");
			System.out.println("로그인하여 주시기 바랍니다.");
		}
		
		return View.MEMBER_LOGIN;
	}

	// 회원 아이디 찾기
	private View memberFindId() {
		System.out.println("\n============= 회원 아이디 찾기 =============");
		String memberName = ScanUtil.nextLine("이름: ");
		String memberHp = ScanUtil.nextLine("핸드폰 번호 (ex: 010-9225-4953) : ");

		List<Object> param = new ArrayList();
		
		param.add(memberName);
		param.add(memberHp);
		
		MemberVo memberFindId = memberService.findMemberId(param);
		
		if(memberFindId == null) {
			System.out.println("찾는 유저의 정보가 없습니다. 확인하고 재시도 해주세요.");
		}else {
			System.out.println("회원님의 아이디는 '" + memberFindId.getMem_id() + "' 입니다.");
		}
		
		return View.MEMBER_LOGIN;
	}

	// 회원 로그인
	private View memberLogin() {
		
		System.out.println("\n============= 회원 로그인 =============");
		String memId = ScanUtil.nextLine("아이디: ");
		String memPass = ScanUtil.nextLine("비밀번호: ");
		
		List<Object> param = new ArrayList();
		
		param.add(memId);
		param.add(memPass);
		
		boolean login = memberService.memberLogin(param);
		
		if(!login) {
			System.out.println("\n로그인에 실패하였습니다.");
			System.out.println("1. 재로그인");
			System.out.println("2. 아이디 찾기");
			System.out.println("3. 비밀번호 찾기");
			System.out.println("4. 홈");
			
			int sel;
			do {
				System.out.println();
				sel = ScanUtil.nextInt("메뉴 선택: ");
				
				switch (sel) {
				case 1:
					return View.MEMBER_LOGIN;
				case 2:
					return View.MEMBER_FINDID;
				case 3:
					return View.MEMBER_FINDPASS;
				case 4:
					return View.HOME;
				default:
					System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
				}
			} while (sel != 1 && sel != 2);
		}
		
			
		return View.MEMBER_HOME;
	}

	// 관리자 > 회원 삭제
	private View memberDelete() {

		System.out.println("\n============= 관리자 > 회원 삭제 =============");
		int memberNo = ScanUtil.nextInt("삭제하고 싶은 회원 번호 : ");
		memberService.memberDelete(memberNo);

		return View.MEMBER_LIST;
	}
	
	// 회원 > 리뷰 삭제
	private View reviewDelete2() {
		int reviewNo = (int) sessionStorage.get("reviewNo");

		String yn = ScanUtil.nextLine("정말로 삭제 하시겠습니까? ( yes 와 no ) : ");
		if (yn.equals("no")) {
			return View.REVIEW_MEMLIST;
		}
		reviewService.reviewDelete(reviewNo);
		return View.REVIEW_MEMLIST;
	}
	
	// 회원 > 카드번호 수정
	private View CardnoUpdate() {
		MemberVo memberVo =(MemberVo) sessionStorage.get("member");
		System.out.println("\n============= 인증 과정입니다. 회원님의 정보를 입력하세요 =============");
		String pass = ScanUtil.nextLine("회원님의 비밀번호는 무엇입니까?");
		String pass2 = memberVo.getMem_pass();
		
		if(pass.equals(pass2)) {
			System.out.println("회원님 인증에 성공 했습니다.");
			List<Object> param = new ArrayList();
			String Card_no = ScanUtil.nextLine("새로운 카드번호를 입력해주세요('-'써주세요!) : ");
		    param.add(Card_no);
		    param.add(memberVo.getMem_no());
		    memberService.CardnoUpdate(param);
		    System.out.println("");
		    System.out.println("회원님의 카드번호가 변경되었습니다.");
		    return View.MEMBER_UPDATE;
		}
		else {
			System.out.println("");
			System.out.println("회원님 인증에 실패 했습니다.");
			System.out.println("1. 재입력");
			System.out.println("2. 이전페이지");
			System.out.println("");
			int sel = ScanUtil.nextInt("메뉴 선택 : "); 
			switch (sel) {
			case 1:
				return View.CARDNO_UPDATE;
			case 2:
				return View.MEMBER_UPDATE;
			default :
				return View.MEMBER_UPDATE;
			}
	}
	}

	// 회원 > 전화번호 수정
	private View hpUpdate() {
		MemberVo memberVo =(MemberVo) sessionStorage.get("member");
		System.out.println("\n============= 인증 과정입니다. 회원님의 정보를 입력하세요 =============");
		String pass = ScanUtil.nextLine("회원님의 비밀번호는 무엇입니까?");
		String pass2 = memberVo.getMem_pass();
		
		if(pass.equals(pass2)) {
			System.out.println("회원님 인증에 성공 했습니다.");
			List<Object> param = new ArrayList();
			String hp = ScanUtil.nextLine("새로운 전화번호를 입력해주세요('-'써주세요!) : ");
		    param.add(hp);
		    param.add(memberVo.getMem_no());
		    memberService.hpUpdate(param);
		    System.out.println("");
		    System.out.println("회원님의 전화번호가 변경되었습니다.");
		    return View.MEMBER_UPDATE;
		}
		else {
			System.out.println("");
			System.out.println("회원님 인증에 실패 했습니다.");
			System.out.println("1. 재입력");
			System.out.println("2. 이전페이지");
			System.out.println("");
			int sel = ScanUtil.nextInt("메뉴 선택 : "); 
			switch (sel) {
			case 1:
				return View.HP_UPDATE;
			case 2:
				return View.MEMBER_UPDATE;
			default :
				return View.MEMBER_UPDATE;
			}
	}
	}

	// 회원 > 이름 수정
	private View nameUpdate() {
		MemberVo memberVo =(MemberVo) sessionStorage.get("member");
		System.out.println("\n============= 인증 과정입니다. 회원님의 정보를 입력하세요 =============");
		String pass = ScanUtil.nextLine("회원님의 비밀번호는 무엇입니까?");
		String pass2 = memberVo.getMem_pass();
		
		if(pass.equals(pass2)) {
			System.out.println("회원님 인증에 성공 했습니다.");
			List<Object> param = new ArrayList();
			String id = ScanUtil.nextLine("새로운 이름을 입력해주세요 : ");
		    param.add(id);
		    param.add(memberVo.getMem_no());
		    memberService.nameUpdate(param);
		    System.out.println("");
		    System.out.println("회원님의 이름이 변경되었습니다.");
		    return View.MEMBER_UPDATE;
		}
		else {
			System.out.println("");
			System.out.println("회원님 인증에 실패 했습니다.");
			System.out.println("1. 재입력");
			System.out.println("2. 이전페이지");
			System.out.println("");
			int sel = ScanUtil.nextInt("메뉴 선택 : "); 
			switch (sel) {
			case 1:
				return View.NAME_UPDATE;
			case 2:
				return View.MEMBER_UPDATE;
			default :
				return View.MEMBER_UPDATE;
			}
		}
	}
	
	// 회원 > 비밀번호 수정
	private View passUpdate() {
		MemberVo memberVo =(MemberVo) sessionStorage.get("member");
		System.out.println("\n============= 인증 과정입니다. 회원님의 정보를 입력하세요 =============");
		String id = ScanUtil.nextLine("회원님의 아이디는 무엇입니까? : ");
		String name = ScanUtil.nextLine("회원님의 이름은 무엇입니까? : ");
		String hp = ScanUtil.nextLine("회원님의 연락처는 무엇입니까? : ");
		String id2 = memberVo.getMem_id();
		String name2 = memberVo.getMem_name();
		String hp2 = memberVo.getMem_hp();
		
		if (id2.equals(id) && name2.equals(name) &&  hp2.equals(hp)) { 
				System.out.println("회원님 인증에 성공 했습니다.");
			    List<Object> param = new ArrayList();
			    String pass = ScanUtil.nextLine("새로운 비밀번호를 입력해주세요 : ");
			    param.add(pass);
			    param.add(memberVo.getMem_no());
			    memberService.passUpdate(param);
			    System.out.println("");
			    System.out.println("비밀번호가 변경되었습니다.");
			    return View.HOME;
			} 
		else {
				System.out.println("");
			    System.out.println("회원님 인증에 실패 했습니다.");
			    System.out.println("");
				System.out.println("1. 재입력");
				System.out.println("2. 이전페이지");
				int sel = ScanUtil.nextInt("메뉴 선택 : "); 
				switch (sel) {
				case 1:
					return View.PASS_UPDATE;
				case 2:
					return View.MEMBER_UPDATE;
				default :
					return View.MEMBER_UPDATE;
				}
			}
		
	}

	// 회원 > 탈퇴
	private View memberWith() {
		MemberVo memberVo =(MemberVo) sessionStorage.get("member");
		System.out.println("\n============= 회원 탈퇴 =============");
		String mem_pass = ScanUtil.nextLine("회원 탈퇴를 위해 비밀번호를 입력해주세요 : ");
		if(mem_pass.equals(memberVo.getMem_pass())) {
			String yn = ScanUtil.nextLine("인증 되었습니다 정말로 탈퇴하겠습니까? (yes 와 no)");
			if (yn.equals("no")) {
				return View.MEMBER_INFO;
			}
			List<Object> param = new ArrayList();
			param.add(memberVo.getMem_no());
			memberService.memberWith(param);
			System.out.println("");
			System.out.println("탈퇴 되었습니다 그동안 이용해주셔서 감사합니다!");
			return View.HOME;	
		}
		else {
			System.out.println("");
			System.out.println("인증에 실패했습니다.");
			System.out.println("1. 재입력");
			System.out.println("2. 이전페이지");
			System.out.println("");
			int sel = ScanUtil.nextInt("메뉴 선택 : "); 
			switch (sel) {
			case 1:
				return View.MEMBER_WITHDRAWAL;
			case 2:
				return View.MEMBER_INFO;
			default :
				return View.MEMBER_WITHDRAWAL;
		}
		}
		
	}

	// 회원 > 회원 수정 페이지
	private View memberUpdate() {
		System.out.println("\n============= 회원 정보 수정 ============= ");
		System.out.println("1. 비밀번호 수정");
		System.out.println("2. 이름 수정");
		System.out.println("3. 연락처 수정");
		System.out.println("4. 카드번호 수정");
		System.out.println("5. 이전 페이지");

		int sel = ScanUtil.nextInt("메뉴 선택 : ");
		switch (sel) {
		case 1:
			return View.PASS_UPDATE;
		case 2:
			return View.NAME_UPDATE;
		case 3:
			return View.HP_UPDATE;
		case 4:
			return View.CARDNO_UPDATE;
		case 5:
			return View.MEMBER_INFO;
		default:
			return View.MEMBER_UPDATE;
		}
	}

	// 회원 > 회원 정보 상세
	private View memberDetail() {
		System.out.println("\n===================================================================== 회원 정보 상세==================================================================");
		MemberVo memberVo = (MemberVo) sessionStorage.get("member");
		List<Object> param =new ArrayList();
		param.add(memberVo.getMem_no());
		
		MemberVo memberList = memberService.memberDetail(param);
		System.out.println(memberList);

		return View.MEMBER_INFO;
	}
	
	// 회원 > 회원 정보 
	private View memberInfo() {
		System.out.println("\n========= 회원정보 ===========");
		System.out.println("1. 회원 정보 보기");
		System.out.println("2. 회원 정보 수정");
		System.out.println("3. 회원 탈퇴");
		System.out.println("4. 회원 홈");
		int sel = ScanUtil.nextInt("메뉴 선택 : ");
		switch (sel) {
		case 1:
			return View.MEMBER_DETAIL;
		case 2:
			return View.MEMBER_UPDATE;
		case 3:
			return View.MEMBER_WITHDRAWAL;
		case 4:
			return View.MEMBER_HOME;
		default:
			return View.MEMBER_INFO;
		}
	}
	
	// 관리자 회원 조회
	private View memberList() {

		System.out.println("\n============= 관리자 > 회원 조회 =============");
		List<MemberVo> memberList = memberService.memberList();
		for (MemberVo memberVo : memberList) {
			System.out.println(memberVo);
		}
		System.out.println(" 1. 회원 삭제하기");
		System.out.println(" 2. 관리자 홈");
		int sel = ScanUtil.nextInt("메뉴 선택 : ");
		switch (sel) {
		case 1:
			return View.MEMBER_DELETE;
		case 2:
			return View.ADMIN_HOME;
		default:
			return View.MEMBER_LIST;
		}
	}
	
	// 관리자 홈
	private View adminHome() {
		System.out.println("=====================================================");
		System.out.println(" __    __  ____ __      ___   ___   ___  ___  ____\r\n" + 
				" ||    || ||    ||     //    // \\\\  ||\\\\//|| ||   \r\n" + 
				" \\\\ /\\ // ||==  ||    ((    ((   )) || \\/ || ||== \r\n" + 
				"  \\V/\\V/  ||___ ||__|  \\\\__  \\\\_//  ||    || ||___");
		System.out.println("\n==================== 관리자 홈 =========================");
		System.out.println();
		System.out.println();
		System.out.println(" 1. 회원 리스트 조회");
		System.out.println(" 2. 리뷰 리스트 조회");
		System.out.println(" 3. 재고 현황");
		System.out.println(" 4. 로그아웃");
		System.out.println();
		System.out.println();
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.MEMBER_LIST;
		case 2:
			return View.REVIEW_ADLIST;
		case 3:
			return View.REMAIN_LIST;
		case 4:
			sessionStorage.remove("adminLogin");
			return View.HOME;
		default:
			return View.ADMIN_HOME;
		}
	}

	// 관리자 로그인
	private View adminLogin() {
		
		System.out.println("\n============= 관리자 로그인 =============");
		String adId = ScanUtil.nextLine("아이디: ");
		String adPass = ScanUtil.nextLine("비밀번호: ");
		
		List<Object> param = new ArrayList();
		
		param.add(adId);
		param.add(adPass);
		
		boolean login = adminService.adminLogin(param);
		
		if(!login) {
			System.out.println("로그인에 실패하였습니다.");
			System.out.println("1. 재로그인");
			System.out.println("2. 홈");
			
			int sel;
			do {
				sel = ScanUtil.nextInt("메뉴 선택: ");
				
				switch (sel) {
				case 1:
					return View.ADMIN_LOGIN;
				case 2:
					return View.HOME;
				default:
					System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
				}
			} while (sel != 1 && sel != 2);
		}
		
		return View.ADMIN_HOME;
	}

	// 전체 홈화면
	private View home() {
		System.out.println("♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥");
		System.out.println(" ____       ______      ____       _____     ____       _____       __  __     \r\n" + 
				"/\\  _`\\    /\\  _  \\    /\\  _`\\    /\\___ \\   /\\  _`\\    /\\  __`\\    /\\ \\/\\ \\    \r\n" + 
				"\\ \\ \\/\\ \\  \\ \\ \\L\\ \\   \\ \\ \\L\\_\\  \\/__/\\ \\  \\ \\ \\L\\_\\  \\ \\ \\/\\ \\   \\ \\ `\\\\ \\   \r\n" + 
				" \\ \\ \\ \\ \\  \\ \\  __ \\   \\ \\  _\\L     _\\ \\ \\  \\ \\  _\\L   \\ \\ \\ \\ \\   \\ \\ , ` \\  \r\n" + 
				"  \\ \\ \\_\\ \\  \\ \\ \\/\\ \\   \\ \\ \\L\\ \\  /\\ \\_\\ \\  \\ \\ \\L\\ \\  \\ \\ \\_\\ \\   \\ \\ \\`\\ \\ \r\n" + 
				"   \\ \\____/   \\ \\_\\ \\_\\   \\ \\____/  \\ \\____/   \\ \\____/   \\ \\_____\\   \\ \\_\\ \\_\\\r\n" + 
				"    \\/___/     \\/_/\\/_/    \\/___/    \\/___/     \\/___/     \\/_____/    \\/_/\\/_/\r\n" + 
				"                                                                               \r\n" + 
				"			______      ____                   __  __     \r\n" + 
				"			/\\__  _\\    /\\  _`\\                /\\ \\/\\ \\    \r\n" + 
				"			\\/_/\\ \\/    \\ \\,\\L\\_\\              \\ \\ \\ \\ \\   \r\n" + 
				"			   \\ \\ \\     \\/_\\__ \\               \\ \\ \\ \\ \\  \r\n" + 
				"			    \\_\\ \\__    /\\ \\L\\ \\              \\ \\ \\_\\ \\ \r\n" + 
				"			    /\\_____\\   \\ `\\____\\              \\ \\_____\\\r\n" + 
				"			    \\/_____/    \\/_____/               \\/_____/\r\n" + 
				"                                               ");
		System.out.println("♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥");
		System.out.println("");
		System.out.println("1. 관리자 로그인 ヽ(･̑ᴗ･̑)ﾉ");
		System.out.println("2. 회원 로그인 ヽ(･̑ᴗ･̑)ﾉ");
		System.out.println("3. 회원가입 ヽ(･̑ᴗ･̑)ﾉ");
		
		int sel = ScanUtil.nextInt("메뉴 선택 : ");
		switch (sel) {
		case 1:
			return View.ADMIN_LOGIN;
		case 2:
			return View.MEMBER_LOGIN;
		case 3:
			return View.MEMBER_INSERT;
		default:
			return View.HOME;
		}
	}
	
	
}
