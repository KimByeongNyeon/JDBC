package kr.co.sist.prepared.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import day_03_04.DbConnection;
import kr.co.sist.vo.ZipcodeVO;

public class ZipcodeDAO {
	private static ZipcodeDAO zDAO;
	
	private ZipcodeDAO() {
		
	}//ZipcodeDAO
	
	public static ZipcodeDAO getInstance() {	
		if(zDAO == null) {
			zDAO = new ZipcodeDAO();
		}//end if
		return zDAO;
	}//getInstance
	
	public List<ZipcodeVO> selectZipcode(String dong)throws SQLException{
		List<ZipcodeVO> list = new ArrayList<ZipcodeVO>();
		
		DbConnection dbCon = DbConnection.getInstance();
		//1. 드라이버 로딩
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		//2. 커넥션 얻기
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);
		//3. 쿼리문 작성
			StringBuilder selectZipcode = new StringBuilder();
			selectZipcode
			.append("   select  zipcode,sido,gugun,dong,nvl(bunji,' ') bunji ")
			.append("   from    zipcode 					  ")
			.append("   where   dong	like  ?||'%' 		  ");
		//4. 바인드
			pstmt = con.prepareStatement(selectZipcode.toString());
			//.append(""   where   dong	like  '"+dong+"%' 			  ");
//			pstmt.setString(1, "%"+ dong + "%");
			pstmt.setString(1, dong);
		//5. 수행결과
			rs = pstmt.executeQuery();
			
			ZipcodeVO zVO = null;
			while(rs.next()) {//조회된 결과에서 다음 레코드가 존재
				zVO = new ZipcodeVO(rs.getString("zipcode"), rs.getString("sido"), 
						rs.getString("gugun"), rs.getString("dong"), rs.getString("bunji"));
				list.add(zVO);
				
			}//end while
		//6. 연결 끊기
		}finally{
			dbCon.dbClose(rs, pstmt, con);
		}
		return list;
	}	
}//class
