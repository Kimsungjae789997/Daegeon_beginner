package util;

public enum View {
	HOME,					// 기본화면
	
	// 회원
	MEMBER_LOGIN,			// 회원 로그인
	MEMBER_HOME,			// 회원 홈
	MEMBER_LIST,			// 회원 목록
	MEMBER_INSERT,			// 회원  등록
	MEMBER_UPDATE,			// 회원 수정(이름, 연락처, 카드번호, 주민번호)
	MEMBER_DELETE,			// 회원 삭제
	MEMBER_INFO,			// 회원 정보
	MEMBER_DETAIL,			// 회원 정보 보기
	MEMBER_FINDID,			// 아이디 찾기
	MEMBER_FINDPASS,		// 비밀번호 찾기
	MEMBER_WITHDRAWAL,		// 회원 탈퇴
	
	// 회원수정
	PASS_UPDATE,			// 비밀번호 수정
	NAME_UPDATE,			// 이름 수정
	HP_UPDATE,				// 전화번호 수정
	CARDNO_UPDATE,			// 카드번호 수정

	// 관리자
	ADMIN_LOGIN,			// 관리자 로그인
	ADMIN_HOME,				// 관리자 홈
	ADMIN_LOGOUT,			// 회원 로그아웃
	
	//상품
	PROD_INSERT,			// 상품 등록
	PROD_UPDATE,			// 상품 수정
	PROD_DELETE,			// 상품 삭제
	
	// 재고
	REMAIN_LIST,			// 재고 목록
	REMAIN_INSERT,			// 재고 등록
	REMAIN_UPDATE,			// 재고 수정
	REMAIN_DELETE,			// 재고 삭제
	
	// 구매
	PAY,					// 결제
	PAY_LIST,				// 구매 내역
	PAY_INSERTP,			// 명소 구매
	PAY_INSERTR,			// 명소 구매
	PAY_RESULT,				// 결제 결과 조회
	
	// 거래처(명소,맛집)
	BUYER_SEARCHP,			// 거래처 명소 검색
	BUYER_SEARCHR,			// 거래처 맛집 검색
	BUYER_LISTR,			// 거래처 목록 = 맛집 리스트 10단위씩 잘라 사용하기
	BUYER_LISTP,			// 거래처 목록 = 명소 리스트 10단위씩 잘라 사용하기
	BUYER_DETAILR,			// 거래처 맛집 자세히 보기
	BUYER_DETAILP,			// 거래처 명소 자세히 보기
	BUYER_REVIEW,			// 거래처별 리뷰 조회
	
	// 리뷰
	REVIEW_INSERT,			// 리뷰 등록
	REVIEW_UPDATE,			// 리뷰 수정
	REVIEW_DELETE,			// 관리자 > 리뷰 삭제
	REVIEW_DELETE2,			// 회원 > 리뷰 삭제
	REVIEW_ADLIST,			// 괸리자  목록
	REVIEW_MEMLIST,        // 회원 리뷰 목록
	REVIEW_DETAIL          // 회원 리뷰 디테일
	
}
