package dao;

import java.util.List;

import util.JDBCUtil;
import vo.BuyerListPVo;
import vo.BuyerListPVo2;
import vo.BuyerListRVo;

public class BuyerDao {
	private static BuyerDao instance = null;

	private BuyerDao() {

	}

	public static BuyerDao getInstance() {
		if (instance == null) {
			instance = new BuyerDao();
		}
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();

	
	// 명소 자세히 보기
	public List<BuyerListPVo2> buyerPDetail(List<Object> param) {
		String sql = "SELECT P.PROD_ID\r\n" + 
				"     , B.BUYER_NAME\r\n" + 
				"     , B.BUYER_ADD1\r\n" + 
				"     , PL.PROD_NAME\r\n" + 
				"     , P.PROD_PRICE\r\n" + 
				"  FROM BUYER B, PROD P, PROD_LGU PL\r\n" + 
				"WHERE B.BUYER_NO = P.BUYER_NO\r\n" + 
				"  AND P.PROD_LGU = PL.PROD_LGU\r\n" + 
				"  AND P.BUYER_NO BETWEEN 11 AND 20\r\n" + 
				"  AND P.BUYER_NO = ?\r\n" + 
				"ORDER BY 1";
		
		return jdbc.selectList(sql, param, BuyerListPVo2.class);
	}

	
	// 명소 리스트에서 거래처명, 주소로 검색하기
	public List<BuyerListPVo> buyerPSearch(List<Object> param, int sel){
		String sql = "SELECT B.BUYER_NO \r\n" + 
				"     , B.BUYER_NAME\r\n" + 
				"     , B.BUYER_ADD1\r\n" + 
				"     , PL.PROD_NAME\r\n" + 
				"     , P.PROD_PRICE\r\n" + 
				"  FROM BUYER B, PROD P, PROD_LGU PL\r\n" + 
				"WHERE B.BUYER_NO = P.BUYER_NO\r\n" + 
				"  AND P.PROD_LGU = PL.PROD_LGU\r\n" + 
				"  AND P.PROD_PRICE = 9000\r\n" + 
				"  AND P.BUYER_NO BETWEEN 11 AND 20";
		
		// 거래처명
		if(sel == 1) {
			sql += " AND BUYER_NAME LIKE '%'||?||'%'";
		}
		// 주소
		if(sel == 2) {
			sql += " AND BUYER_ADD1 LIKE '%'||?||'%'";
		}
		
		sql+= " ORDER BY 1";
		
		return jdbc.selectList(sql, param, BuyerListPVo.class);
	}

	// 명소 리스트
	public List<BuyerListPVo> BuyerListp() {
		String sql = " SELECT B.BUYER_NO \r\n" + 
				"     , B.BUYER_NAME\r\n" + 
				"     , B.BUYER_ADD1\r\n" + 
				"     , PL.PROD_NAME\r\n" + 
				"     , P.PROD_PRICE\r\n" + 
				"  FROM BUYER B, PROD P, PROD_LGU PL\r\n" + 
				"WHERE B.BUYER_NO = P.BUYER_NO\r\n" + 
				"  AND P.PROD_LGU = PL.PROD_LGU\r\n" + 
				"  AND P.PROD_PRICE = 9000\r\n" + 
				"  AND P.BUYER_NO BETWEEN 11 AND 20";
		
		return jdbc.selectList(sql, BuyerListPVo.class);
	}
	
	// 맛집 자세히 보기
	public List<BuyerListPVo2> buyerRDetail(List<Object> param) {
		String sql = "SELECT P.PROD_ID\r\n" + 
				"     , B.BUYER_NAME\r\n" + 
				"     , B.BUYER_ADD1\r\n" + 
				"     , PL.PROD_NAME\r\n" + 
				"     , P.PROD_PRICE\r\n" + 
				"  FROM BUYER B, PROD P, PROD_LGU PL\r\n" + 
				"WHERE B.BUYER_NO = P.BUYER_NO\r\n" + 
				"  AND P.PROD_LGU = PL.PROD_LGU\r\n" + 
				"  AND P.BUYER_NO BETWEEN 1 AND 10\r\n" + 
				"  AND P.BUYER_NO = ?\r\n" + 
				"ORDER BY 1";
		
		return jdbc.selectList(sql, param, BuyerListPVo2.class);
	}

	
	// 맛집 리스트에서 거래처명, 주소로 검색하기
	public List<BuyerListRVo> buyerRSearch(List<Object> param, int sel){
		String sql = "SELECT DISTINCT B.BUYER_NO \r\n" + 
				"     , B.BUYER_NAME\r\n" + 
				"     , BL.BUYER_LGUNAME\r\n" + 
				"     , B.BUYER_ADD1\r\n" + 
				"     , PL.PROD_NAME\r\n" + 
				"     , P.PROD_PRICE\r\n" + 
				"  FROM BUYER B, BUYER_LGU BL,  PROD P, PROD_LGU PL\r\n" + 
				"WHERE B.BUYER_NO = P.BUYER_NO\r\n" + 
				"  AND B.BUYER_LGU = BL.BUYER_LGU\r\n" + 
				"  AND P.PROD_PRICE = 9000\r\n" + 
				"  AND PL.PROD_NAME LIKE '%10,000%'\r\n" + 
				"  AND P.BUYER_NO BETWEEN 1 AND 10";
		
		// 거래처명
		if(sel == 1) {
			sql += "     AND B.BUYER_NAME LIKE '%'||?||'%'";
		}
		// 주소
		if(sel == 2) {
			sql += "     AND B.BUYER_ADD1 LIKE '%'||?||'%'";
		}
		// 분류
		if(sel == 3) {
			sql += "     AND BL.BUYER_LGUNAME LIKE '%'||?||'%'";
		}
		
		sql+= " ORDER BY 1";
		
		return jdbc.selectList(sql, param, BuyerListRVo.class);
	}
	
	// 맛집 리스트
	public List<BuyerListRVo> BuyerListr() {
		String sql = "SELECT DISTINCT B.BUYER_NO \r\n" + 
				"     , B.BUYER_NAME\r\n" + 
				"     , BL.BUYER_LGUNAME\r\n" + 
				"     , B.BUYER_ADD1\r\n" + 
				"     , PL.PROD_NAME\r\n" + 
				"     , P.PROD_PRICE\r\n" + 
				"  FROM BUYER B, BUYER_LGU BL,  PROD P, PROD_LGU PL\r\n" + 
				"WHERE B.BUYER_NO = P.BUYER_NO\r\n" + 
				"  AND B.BUYER_LGU = BL.BUYER_LGU\r\n" + 
				"  AND P.PROD_PRICE = 9000\r\n" + 
				"  AND PL.PROD_NAME LIKE '%10,000%'\r\n" + 
				"  AND P.BUYER_NO BETWEEN 1 AND 10\r\n" + 
				"  ORDER BY 1";
			
		return jdbc.selectList(sql, BuyerListRVo.class);
	}
}
