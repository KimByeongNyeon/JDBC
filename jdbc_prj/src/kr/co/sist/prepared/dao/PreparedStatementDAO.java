package kr.co.sist.prepared.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.DbConnection;
import kr.co.sist.statement.vo.EmployeeVO;

public class PreparedStatementDAO {
	
	private static PreparedStatementDAO pDao;
	private PreparedStatementDAO() {
		
	}
	public static PreparedStatementDAO getInstance() {
		
		if(pDao == null) {
			pDao = new PreparedStatementDAO();
		}//end if
		
			return pDao;
	}//getInstance
	
	public void insertEmp(EmployeeVO eVO) throws SQLException{
		
		DbConnection dbCon = DbConnection.getInstance();
		
		//1.드라이버로딩
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
		//2.커넥션얻기
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);
		//3.쿼리문생성객체 얻기
			String insertEmp = 
					"insert into employee(empno,ename,job,sal)values(?,?,?,?)";
			pstmt=con.prepareStatement(insertEmp);
		//4.바인드변수에 값설정
			pstmt.setInt(1, eVO.getEmpno());
			pstmt.setString(2, eVO.getEname());
			pstmt.setString(3, eVO.getJob());
			pstmt.setDouble(4, eVO.getSal());
		//5.쿼리문 수행 후 결과 얻기
			pstmt.executeUpdate();
		}finally {
		//6.연결 끊기
			dbCon.dbClose(null, pstmt, con);
		}//end finally
	}
	public int updateEmp(EmployeeVO eVO)throws SQLException{
		int cnt = 0;
		
		DbConnection dbCon = DbConnection.getInstance();
		//1.드라이버 로딩
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			//2.커넥션 얻기
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);
		//3.쿼리문 생성객체 얻기
//			String updateEmp = "update employee set job=?,sal=? where empno=?";
			StringBuilder updateEmp = new StringBuilder();
			updateEmp
			.append("	update employee		")
			.append("	set job=?,sal=?		")
			.append("	where empno=?		");
			pstmt = con.prepareStatement(updateEmp.toString());
		//4.바인드변수에 값 설정
			pstmt.setString(1, eVO.getJob());
			pstmt.setDouble(2, eVO.getSal());
			pstmt.setInt(3, eVO.getEmpno());
			System.out.println(updateEmp.toString());
			
		//5.쿼리문 수행 후 결과 얻기
			cnt = pstmt.executeUpdate();
		}finally {
			//6.연결 끊기
			dbCon.dbClose(null, pstmt, con);
		}
		return cnt;
	}//updateEmp
	public int deleteEmp(int empno)throws SQLException{
		int cnt = 0;
		//1.드라이버로딩
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		//2.커넥션 얻기
		try {
			String id = "scott";
			String pass ="tiger";
			
			con =dbCon.getConnection(id, pass);
		//3.쿼리문 생성객체 얻기
			String deletEmp = "delete from employee where empno=?";
			pstmt = con.prepareStatement(deletEmp);
		//4.바인드
			pstmt.setInt(1, empno);
		//5.쿼리문 수행 후 결과 얻기
			cnt = pstmt.executeUpdate();
		//6.연결끊기
		}finally {
			dbCon.dbClose(null, pstmt, con);
		}
		
		return cnt;
	}//deleteEmp
	public EmployeeVO selectOneEmp(int empno) throws SQLException {
		EmployeeVO eVO = null;
		DbConnection dbCon = DbConnection.getInstance();
		//1.드라이버 로딩
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//2.커넥션 얻기
			String id = "scott";
			String pass = "tiger";
			
			con = dbCon.getConnection(id, pass);
		//3.쿼리문 생성객체 얻기
			StringBuilder selectOneEmp = new StringBuilder();
//			String selectEmp = "select ename,sal,job,hiredate";
			selectOneEmp
			.append("  select ename,sal,job,hiredate,to_char(hiredate, 'yyyy-mm-dd') as hiredate2 ")
			.append("  from employee ")
			.append("  where empno = ?");
			pstmt = con.prepareStatement(selectOneEmp.toString());
			
		//4.바인드
			pstmt.setInt(1, empno);
		//5.쿼리문 수행 후 결과 얻기
			String ename = "", job = "";
			double sal = 0.0;
			Date hiredate = null;
			String hiredate2 = "";
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ename = rs.getString("ename");
				job = rs.getString("job");
				sal = rs.getDouble("sal");
				hiredate = rs.getDate("hiredate");
				hiredate2 = rs.getString("hiredate2");
				
				eVO = new EmployeeVO(empno, ename, job, sal, hiredate, hiredate2);
			}
		}finally{//6.연결끊기
		dbCon.dbClose(rs, pstmt, con);
		}
		return eVO;
	}//selectOneEmp
	public List<EmployeeVO> selectALLEmp() throws SQLException{
		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		
		DbConnection dbCon = DbConnection.getInstance();
		//1.드라이버 로딩
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//2.커넥션 얻기
		try {
			String id = "scott";
			String pass = "tiger";
			
			con = dbCon.getConnection(id, pass);
		//3.쿼리문 생성객체 얻기
			String selectAllEMP = "select empno, ename, job, sal, to_char(hiredate, 'yyyy-mm-dd q\"분기\"') hiredate from employee";
			pstmt = con.prepareStatement(selectAllEMP);
		//4.쿼리문 수행 후 결과 얻기
			EmployeeVO eVO = null;
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				eVO = new EmployeeVO(rs.getInt("empno"), rs.getString("ename"), rs.getString("job"), rs.getDouble("sal"), 
						null,rs.getString("hiredate"));
				list.add(eVO);
			}
			System.out.println(list);
		//5.연결 끊기
		}finally{
			dbCon.dbClose(rs, pstmt, con);
		}
		
		return list;
	}
	
}
