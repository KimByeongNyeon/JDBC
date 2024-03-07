package info.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import info.VO.InfoVO;

public class InfoDAO {
	private static InfoDAO iDAO;

	private InfoDAO() {

	}

	public static InfoDAO getInstance() {
		if (iDAO == null) {
			iDAO = new InfoDAO();
		} // end if
		return iDAO;
	}// getInstance

	public void insertInfo(InfoVO iVO) throws SQLException {

		DbConnection dbCon = DbConnection.getInstance();

		// 1.
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 2.
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);
			// 3.
			String insertInfo = "insert into work_jdbc(num, name, img, age, input_date)" + "values(?,?,?,?,?)";
			pstmt = con.prepareStatement(insertInfo);
			// 4.
			pstmt.setInt(1, iVO.getNum());
			pstmt.setString(2, iVO.getName());
			pstmt.setString(3, iVO.getImg());
			pstmt.setInt(4, iVO.getAge());
			pstmt.setString(5, iVO.getInputdate());
			// 5.
			pstmt.executeUpdate();
		} finally {
			dbCon.dbClose(null, pstmt, con);
		} // end finally
	}// insertInfo

	public int updateInfo(InfoVO iVO) throws SQLException {
		int cnt = 0;
		DbConnection dbCon = DbConnection.getInstance();

		// 1.
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 2.
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);
			// 3.
			String changeInfo = "update work_jdbc set name=?, img=?, age=? where num= ?";

			pstmt = con.prepareStatement(changeInfo);
			// 4.
			pstmt.setString(1, iVO.getName());
			pstmt.setString(2, iVO.getImg());
			pstmt.setInt(3, iVO.getAge());
			pstmt.setInt(4, iVO.getNum());
			// 5.
			pstmt.executeUpdate();
		} finally {
			dbCon.dbClose(null, pstmt, con);
		} // end finally
		return cnt;
	}//updateInfo
	
	public int deleteInfo(int num) throws SQLException {
		int cnt = 0;
		DbConnection dbCon = DbConnection.getInstance();
		//1.드라이버 로딩
		Connection con = null;
		PreparedStatement pstmt = null;
		//2.커넥션 얻기
		String id = "scott";
		String pass = "tiger";
		con = dbCon.getConnection(id, pass);
		try {
		//3.쿼리문 작성
			String deleteInfo = "delete from work_jdbc where num =?";
			pstmt = con.prepareStatement(deleteInfo);
		//4.바인드 변수
			pstmt.setInt(1, num);
		//5.실행 후 결과
			cnt = pstmt.executeUpdate();
		//6.연결 끊기
		}finally {
			dbCon.dbClose(null, pstmt, con);
		}
		return cnt;
	}
	
//	public List<InfoVO> allInfo()throws SQLException{
//		List<InfoVO> list = new ArrayList<InfoVO>();
//		
//		DbConnection dbCon = DbConnection.getInstance();
//		
//		//1.
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		//2.
//		try {
//			String id = "scott";
//			String pass = "tiger";
//			con = dbCon.getConnection(id, pass);
//		//3.
//			String allInfo = "select num, name, img, age, input_date from work_jdbc ";
//			pstmt = con.prepareStatement(allInfo);
//		//4,
//			InfoVO iVO = null;
//			
//			rs = pstmt.executeQuery();
////			while(rs.next()) {
////				iVO = new InfoVO(rs.getInt("num"),
////								 rs.getString("name"),
////								 rs.getString("img"),
////								 rs.getInt("age"),
////								 rs.getString("input_data"));
////			}
//		}finally {
//			dbCon.dbClose(rs, pstmt, con);
//		}
//		return list;
//	}
}
