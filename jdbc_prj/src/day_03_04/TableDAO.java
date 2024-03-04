package day_03_04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableDAO {
	private static TableDAO tDao;
	private TableDAO() {
		
	}
	public static TableDAO getInstance() {
		if(tDao == null) {
			tDao = new TableDAO();
		}//end if
		
		return tDao;
	}
	
	public List<String> selectAllTab() throws SQLException{
		List<String> list = new ArrayList<String>();
		
		DbConnection dbCon = DbConnection.getInstance();
		//1.드라이버 로딩
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//2.커넥션 얻기
		try {
			String id =  "scott";
			String pass = "tiger";
			
			con = dbCon.getConnection(id, pass);
		//3.쿼리문 생성객체 얻기
			String selectAllTab = "select * from tab";
			pstmt = con.prepareStatement(selectAllTab);
		//4.쿼리문 수행 후 결과 얻기
			String tName = "";
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				tName = rs.getString("TNAME");
				list.add(tName);
			}
		}finally {
			dbCon.dbClose(rs, pstmt, con);
		}
		return list;
	}
}
