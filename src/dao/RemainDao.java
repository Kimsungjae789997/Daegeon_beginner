package dao;

import java.util.List;

import util.JDBCUtil;
import vo.RemainVo;

public class RemainDao {
private static RemainDao instance = null;

private RemainDao() {
}

public static RemainDao getInstance() {

	if (instance == null) {
		instance = new RemainDao();
	}
	return instance;
}
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	
	public List<RemainVo> remainList(){
		
		String sql = " SELECT REMAIN_YEAR, PROD_ID, REMAIN_J_00, REMAIN_I, REMAIN_O, REMAIN_J_99,\r\n" + 
				"  TO_CHAR(REMAIN_DATE,'YYYY.MM.DD') REMAIN_DATE, REMAIN_DEL FROM REMAIN\r\n" + 
				"  WHERE REMAIN_DEL IS NULL\r\n" + 
				"  ORDER BY PROD_ID";
		
		return jdbc.selectList(sql, RemainVo.class);
	}
	
	
	public void remainInsert(List<Object> param) {
		
	String sql =" INSERT INTO REMAIN(REMAIN_YEAR,PROD_ID,REMAIN_J_00,REMAIN_I,REMAIN_O,REMAIN_J_99)\r\n" + 
			"  VALUES(?,(SELECT MAX(to_number(PROD_ID))+1 FROM REMAIN),?,?,?,?) ";

	jdbc.update(sql, param);
}

		public void remainUpdate(List<Object> param, int sel) {
			String sql = "UPDATE REMAIN "
					+ " SET ";
		
			if(sel ==1) {
				sql+=" REMAIN_I = ?, REMAIN_J_99 = REMAIN_J_00 - REMAIN_O +? ";
			}
			if(sel ==2) {
				sql+=" REMAIN_O = ?, REMAIN_J_99 = REMAIN_J_00 - ? + REMAIN_I ";
			}
			
			sql+=" WHERE PROD_ID = ?";
			
			jdbc.update(sql, param);
			
		}

		public void remainDelete(int prod_id2) {
			String sql =  "UPDATE REMAIN SET REMAIN_DEL = sysdate \r\n" + 
					" WHERE PROD_ID = "+prod_id2;
			
			jdbc.update(sql);
		}

	
	
}
