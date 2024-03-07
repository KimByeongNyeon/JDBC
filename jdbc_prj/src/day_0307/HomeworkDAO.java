package day_0307;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import info.dao.DbConnection;

public class HomeworkDAO {
	private static HomeworkDAO hDAO;
	private HomeworkDAO() {
		
	}
	public static HomeworkDAO getInstance() {
		if(hDAO == null) {
			hDAO = new HomeworkDAO();
		}//end if
		return hDAO;
	}//getInstance
	
	public List<HomeworkVO> selectCar(String maker) throws SQLException {
		List<HomeworkVO> list = new ArrayList<HomeworkVO>();
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
		//3.쿼리문 작성
			StringBuilder selectCar = new StringBuilder();
			selectCar
			.append("	select country, maker, model, car_year, price, car_option	")
			.append("	from (select cc.country, cc.maker, cmo.model, cmo.CAR_YEAR, CMO.PRICE, CMO.CAR_OPTION,	")
			.append("	row_number() over(order by hiredate desc) rnum	")
			.append("	from   CAR_COUNTRY cc, CAR_MAKER cma, CAR_MODEL cmo	")
			.append("	where (cma.maker(+)=cc.maker and cmo.model(+)=cma.model) and cc.maker = ?)	")
			.append("	where rnum between 1 and 10	");
//			String selectAllCar = "select country, maker, model, car_year, price, car_option\r\n"
//					+ "from (select cc.country, cc.maker, cmo.model, cmo.CAR_YEAR, CMO.PRICE, CMO.CAR_OPTION,\r\n"
//					+ "						row_number() over(order by hiredate desc) rnum\r\n"
//					+ "		from   CAR_COUNTRY cc, CAR_MAKER cma, CAR_MODEL cmo\r\n"
//					+ "		where (cma.maker(+)=cc.maker and cmo.model(+)=cma.model) and cc.maker = ?)\r\n"
//					+ "where rnum between 1 and 10;";
			pstmt = con.prepareStatement(selectCar.toString());
		//4.바인드 변수
			pstmt.setString(1, maker);
		//5.쿼리문 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HomeworkVO hVO = new HomeworkVO(rs.getString("country"), 
						rs.getString("maker"), rs.getString("model"), 
						rs.getString("car_year"),rs.getInt("price"), 
						rs.getString("car_option"));

				list.add(hVO);
				
			}//end while
		//6.연결끊기
		}finally {
			dbCon.dbClose(rs, pstmt, con);
		}//end finally
		return list;
	}
	public static void main(String[] args) throws SQLException {
		System.out.println(HomeworkDAO.getInstance().selectCar("현대"));
	}
}
