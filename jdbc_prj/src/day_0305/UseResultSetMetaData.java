package day_0305;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import day_03_04.DbConnection;
import kr.co.sist.statement.vo.EmployeeVO;

/**
 * 조회하는 테이블의 Schema 정보를 얻을 때 사용하는 객체.
 * desc 테이블명 수준의 정보를 얻는다. 더 자세한 정보를 얻을 때에는 DD를 사용해야 한다. 
 */
@SuppressWarnings("serial")
public class UseResultSetMetaData extends JFrame {
	private JTextArea result;
	
	public UseResultSetMetaData()throws SQLException{
		//emp 테이블의 MetaData 얻기
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
			String tname = "dept";
			String selectEmp = "select * from " + tname; //bind 변수로는 사용할 수 없음
			pstmt = con.prepareStatement(selectEmp);
		//4. 바인드 
//			pstmt.setString(1, tname);
			
			rs = pstmt.executeQuery();
			//ResultSetMetaData를 얻는다.
			ResultSetMetaData rsmd = rs.getMetaData();
			System.out.println("컬럼의 수 : "+rsmd.getColumnCount());
			System.out.println("컬럼의 이름 : "+rsmd.getColumnName(1));
			System.out.println("컬럼의 데이터형 : "+rsmd.getColumnTypeName(1));
			System.out.println("컬럼의 크기 : "+rsmd.getPrecision(1));
			System.out.println("컬럼의 null 허용 여부 : "+rsmd.isNullable(1));
			
			StringBuilder output = new StringBuilder();
			output.append(tname).append("테이블의 정보\n");
			output.append("컬럼명\tNull허용\t데이터형\n");
			int size = 0;
			for(int i = 1; i <= rsmd.getColumnCount(); i++) {
				size = rsmd.getPrecision(i);
				output.append(rsmd.getColumnName(i)).append("\t")
				.append(rsmd.isNullable(i)==0?"Not Null":"").append("\t")
				.append(rsmd.getColumnTypeName(i)).append("(")
				.append(rsmd.getPrecision(i)).append(")\n");
				if(size != 0) {
					output.append("(").append(size).append(")");
				}
				output.append("\n");
			}//end for
			JTextArea result = new JTextArea(output.toString(), 10,50);
			JScrollPane jsp  = new JScrollPane(result);
			JOptionPane.showMessageDialog(result, jsp);
			
		
		//5. 결과
		}finally {
		//6. 연결 끊기
			dbCon.dbClose(rs, pstmt, con);	
		}
	}//UseResultSetMetaData
	public static void main(String[] args) {
		try {
			new UseResultSetMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//main
}//class
