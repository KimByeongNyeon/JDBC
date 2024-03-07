package day_0305;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import day_03_04.DbConnection;
import kr.co.sist.vo.TestClobVO;

public class UseClob  {
	public List<TestClobVO> selectClob()throws SQLException {
		List<TestClobVO> list = new ArrayList<TestClobVO>();
		DbConnection dbCon = DbConnection.getInstance();
		
		//1.
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//2.
		try {
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);
		//3.
			String selectClob = "select num,title,content,writer,input_date from test_clob";
			pstmt = con.prepareStatement(selectClob);
		//4.
		//5.
			rs = pstmt.executeQuery();
			TestClobVO tcVO = null;
			
			BufferedReader br = null;
			StringBuilder content = new StringBuilder();
			String temp="";
			while(rs.next()) {
				tcVO = new TestClobVO();
				tcVO.setNum(rs.getInt("num"));
				tcVO.setTitle(rs.getString("title"));
				tcVO.setWriter(rs.getString("writer"));
				tcVO.setInput_date(rs.getDate("input_date"));
				//clob를 읽어들이기 위해 별도의 Stream 연결
				br = new BufferedReader(rs.getClob("content").getCharacterStream());
				try {
					while((temp = br.readLine()) != null) {
						content.append(temp).append("\n");
					}
					br.close();
					
				} catch (IOException e) {
					content.append("N/A");
					e.printStackTrace();
				}//end catch
				tcVO.setContent(content.toString());
				//저장된 content를 삭제한다.
				content.delete(0, content.length());
				list.add(tcVO);
			}//end while
		//6.
		}finally {
			dbCon.dbClose(rs, pstmt, con);
		}//end finally
		return list;
	}//selectClob
	public void useSelectClob(List<TestClobVO> list) {
		Iterator<TestClobVO> ita = list.iterator();
		if(list.isEmpty()) {
			System.out.println("작성된 기사가 없습니다. ");
		}//end if
		TestClobVO tcVO = null;
		while(ita.hasNext()) {
			tcVO = ita.next();
		System.out.println("번호 : "+tcVO.getNum()+", 제목 : "+tcVO.getTitle());
		System.out.println("내용 : "+tcVO.getContent());
		System.out.println(tcVO.getWriter());
		System.out.println("-------------------------------------------------");
		}//end while
		
	}//useSelectClob
	public static void main(String[] args) {
		UseClob uc = new UseClob();
		try {
			List<TestClobVO> list = uc.selectClob();
			uc.useSelectClob(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//main
}//class
