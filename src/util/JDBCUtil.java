package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUtil {
	/*
	 * JDBC를 좀 더 쉽고 편하게 사용하기 위한 Utility 클래스
	 * 
	 * Map<String, Object> selectOne(String sql)
	 * Map<String, Object> selectOne(String sql, List<Object> param)
	 * List<Map<String, Object>> selectList(String sql)
	 * List<Map<String, Object>> selectList(String sql, List<Object> param) // 회원정보 전체 가져오기 LIST 안에 MAP 담은 것임 //
	 * int update(String sql)
	 * int update(String sql, List<Object> param)
	 * */
	
	// 싱글톤 패턴 : 인스턴스의 생성을 제한하여 하나의 인스턴스만 사용하는 디자인 패턴
	
	// 인스턴스를 보관할 변수
	private static JDBCUtil instance = null;
	// JDBCUtil 객체를 만들 수 없게(인스턴스화 할 수 없게) private으로 제한함
	private JDBCUtil() {} 
	public static JDBCUtil getInstance() {
		if(instance == null) 
			instance = new JDBCUtil();
		return instance;
	}
	
	// 이것 바꿔야지 동작한다.
	private String url = "jdbc:oracle:thin:@192.168.35.28:1521:xe";
	private String user = "PROJECT7";
	private String password = "java";
	
	private Connection conn = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public List<Map<String, Object>> selectList(String sql, List<Object> param){
		// LIKE 검색
		// sql => "SELECT * FROM MEMBER WHERE MEM_ADD1 LIKE '%'||?||'%'"
		// sql => "SELECT * FROM JAVA_BOARD WHERE WRITER=?"
		// sql => "SELECT * FROM JAVA_BOARD WHERE BOARD_NUM > ?"
		List<Map<String, Object>> result = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			ps = conn.prepareStatement(sql);
			for(int i = 0; i < param.size(); i++) {
				ps.setObject(i + 1, param.get(i));
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while(rs.next()) {
				if(result == null) result = new ArrayList<>();
				Map<String, Object> row = new HashMap<>();
				for(int i = 1; i <= columnCount; i++) {
					String key = rsmd.getColumnLabel(i);
					Object value = rs.getObject(i);
					row.put(key, value);
				}
				result.add(row);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close(); } catch(Exception e) {}
			if(ps != null) try { ps.close(); } catch(Exception e) {}
			if(conn != null) try { conn.close(); } catch(Exception e) {}
		}
		return result;
	}
	
	public List<Map<String, Object>> selectList(String sql){
		// sql => "SELECT * FROM MEMBER"
		// sql => "SELECT * FROM JAVA_BOARD"
		// sql => "SELECT * FROM JAVA_BOARD WHERE BOARD_NUM > 10"
		List<Map<String, Object>> result = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while(rs.next()) {
				if(result == null) result = new ArrayList<>();
				Map<String, Object> row = new HashMap<>();
				// 컬럼 개수만큼 자동으로 데이터 가져온다.
				// ColumnLabel: 원래 컬럼의 데이터이름만 가져올수있는데 이름 재정의한 별칭? 가져온다.
				for(int i = 1; i <= columnCount; i++) {
					String key = rsmd.getColumnLabel(i);
					Object value = rs.getObject(i);
					row.put(key, value);
				}
				result.add(row);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close(); } catch(Exception e) {}
			if(ps != null) try { ps.close(); } catch(Exception e) {}
			if(conn != null) try { conn.close(); } catch(Exception e) {}
		}
		return result;
	}

//	public List<Map<String, Object>> update(String sql, List<Object> param) {
	public int update(String sql, List<Object> param) {
		// sql => "DELETE FROM JAVA_BOARD WHERE BOARD_NUMBER=?"
		// sql => "UPDATE JAVA_BOARD SET TITLE='하하' WHERE BOARD_NUMBER=?"
		// sql => "INSERT MY_MEMBER (MEM_ID, MEM_PASS, MEM_NAME) VALUES (?, ?, ?)"
		int result = 0;
//		List<Map<String, Object>> result = 0;
		try {
			conn = DriverManager.getConnection(url, user, password);
			ps = conn.prepareStatement(sql);
			for(int i = 0; i < param.size(); i++) {
				ps.setObject(i + 1, param.get(i));
			}
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {  rs.close();  } catch (Exception e) { }
			if(ps != null) try {  ps.close();  } catch (Exception e) { }
			if(conn != null) try { conn.close(); } catch (Exception e) { }
		}
		return result;
	}
	
	public int update(String sql) {
		// MAP으로 꺼낼 때 ... 기니까 
		// 이름 재정의 해야한다. 아니면 못가져온다..
		// sql => "DELETE FROM JAVA_BOARD"
		// sql => "UPDATE JAVA_BOARD SET TITLE='하하'"
		// sql => "UPDATE JAVA_BOARD SET TITLE='하하' WHERE BOARD_NUMBER=1"
		// sql => "INSERT MY_MEMBER (MEM_ID, MEM_PASS, MEM_NAME) VALUES ('admin', '1234', '홍길동')"
		int result = 0;
		try {
			conn = DriverManager.getConnection(url, user, password);
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {  rs.close();  } catch (Exception e) { }
			if(ps != null) try {  ps.close();  } catch (Exception e) { }
			if(conn != null) try { conn.close(); } catch (Exception e) { }
		}
		return result;
	}
	
	public <T> List<T> selectList(String sql,  List<Object> param , Class<T> type) {
		List<Map<String, Object>> list = selectList(sql, param);
		return ConvertUtils.convertToList(list, type);
	}
	
	public <T> List<T> selectList(String sql, Class<T> type) {
		List<Map<String, Object>> list = selectList(sql);
		return ConvertUtils.convertToList(list, type);
	}
	
	public <T> T selectOne(String sql, Class<T> type) {
		Map<String, Object> map = selectOne(sql);
		return ConvertUtils.convertToVo(map, type);
	}
	
	public <T> T selectOne(String sql, List<Object> param , Class<T> type) {
		Map<String, Object> map = selectOne(sql, param);
		return ConvertUtils.convertToVo(map, type);
	}
//	sql SELECT문이고 param a001d이다?
	public Map<String, Object> selectOne(String sql, List<Object> param){
		// sql => "SELECT * FROM JAVA_BOARD WHERE BOARD_NUMBER=?"
		// param => [1]
		//
		// sql => "SELECT * FROM JAVA_BOARD WHERE WRITER=? AND TITLE=?"
		// param => ["홍길동", "안녕"]
		Map<String, Object> row = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			ps = conn.prepareStatement(sql);
			// param.size(); a001 1234 2개임(0, 1번째)
			for(int i = 0; i < param.size(); i++) {
				ps.setObject(i + 1, param.get(i));
				// 0들어가면 1 a001 , 1+1=2 1234
			}
			rs = ps.executeQuery();
			// 쿼리 실행한 결과, 출력된 것
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while(rs.next()) {
				row = new HashMap<>(); // 행
				for(int i = 1; i <= columnCount; i++) {
					String key = rsmd.getColumnLabel(i); // 컬럼명
					Object value = rs.getObject(i); // 값
					row.put(key,value);
				}
				// {DATETIME=2022-08-05 16:35:08.0, WRITER=홍길동, TITLE=안녕하세요, CONTENT=안녕안녕, BOARD_NUMBER=1}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {  rs.close();  } catch (Exception e) { }
			if(ps != null) try {  ps.close();  } catch (Exception e) { }
			if(conn != null) try { conn.close(); } catch (Exception e) { }
		}
		return row;
	}
	
	public Map<String, Object> selectOne(String sql){
		// sql => "SELECT * FROM JAVA_BOARD 
		//			WHERE BOARD_NUMBER=(SELECT MAX(BOARD_NUMBER) FROM JAVA_BOARD)"
		// sql => "SELECT * FROM MEMBER MEM_ID='a001' AND MEM_PASS='123'"
		Map<String, Object> row = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while(rs.next()) {
				row = new HashMap<>();
				for(int i = 1; i <= columnCount; i++) {
					String key = rsmd.getColumnLabel(i);
					// getColumnName vs getColumnLabel
					// getColumnName : 원본 컬럼명을 가져옴
					// getColumnLabel : as로 선언된 별명을 가져옴, 없으면 원본 컬럼명
					Object value = rs.getObject(i);
					row.put(key,value);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {  rs.close();  } catch (Exception e) { }
			if(ps != null) try {  ps.close();  } catch (Exception e) { }
			if(conn != null) try { conn.close(); } catch (Exception e) { }
		}
		return row;
	}
	
	
	// 추가
	public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // PreparedStatement 객체를 닫는 메서드
    public static void close(PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // ResultSet 객체를 닫는 메서드
    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 리소스를 해제하는 메서드
    public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        close(rs);
        close(pstmt);
        close(conn);
    }
    
    // 아이디 중복확인
	public int selectCount(String memberId){

		int count = 9999;
		
		boolean isDuplicate = false;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			
	        String sql = "SELECT COUNT(MEM_ID) FROM MEMBER WHERE MEM_ID = ?";
	        //  준비된 SQL 문장을 실행하기 위한 PreparedStatement 객체를 생성
	        ps = conn.prepareStatement(sql);
	        // 준비된 문장의 첫 번째 파라미터(?)에 memberId 값을 설정
	        ps.setString(1, memberId);
	        // 쿼리를 실행한 결과를 ResultSet 객체인 rs에 저장
	        rs = ps.executeQuery();
	        
	        // rs에서 다음 레코드로 이동, 더 이상 레코드가 없는 경우 if 문을 빠져나감.
	        // 이후의 로직은 첫 번째 레코드에 접근하는 경우에만 실행
	        if (rs.next()) {
	        	// count 값이 0보다 큰 경우(동일한 아이디를 가진 회원이 이미 존재하는 경우)에는
	        	// isDuplicate 변수를 true로 설정
	            count = rs.getInt(1);

	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	// 데이터베이스 리소스를 안전하게 해제하기 위해 JDBCUtil 클래스의 close 메서드를 호출
	        JDBCUtil.close(rs, ps, conn); // 리소스 해제
	    }
		return count;
	}
	

	
	
    
}







