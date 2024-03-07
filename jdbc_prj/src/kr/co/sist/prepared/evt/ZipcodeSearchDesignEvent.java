package kr.co.sist.prepared.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.design.ZipcodeSearchDesign;
import kr.co.sist.prepared.dao.ZipcodeDAO;
import kr.co.sist.vo.ZipcodeVO;

public class ZipcodeSearchDesignEvent extends WindowAdapter implements ActionListener {
	
	private ZipcodeSearchDesign zsd;
	
	public ZipcodeSearchDesignEvent(ZipcodeSearchDesign zsd) {
		this.zsd = zsd;
	}//ZipcodeSearchDesignEvent
	
	
	
	@Override
	public void windowClosing(WindowEvent e) {
		zsd.dispose();
	}//windowClosing



	@Override
	public void actionPerformed(ActionEvent e) {
		String dong = zsd.getJtfDong().getText().trim();
		if(dong.isEmpty()) {
			JOptionPane.showMessageDialog(zsd, "동 이름은 필수 입력 입니다.");
			return;//early return
		}//end if
	
		setZipcode(dong);
		
		zsd.getJtfDong().setText("");
	}//actionPerformed
	
	private void setZipcode(String dong) {
		
		ZipcodeDAO zDAO = ZipcodeDAO.getInstance();
		try {
			//입력된 동을 사용한 검색 결과를 가져와서 
			List<ZipcodeVO> list = zDAO.selectZipcode(dong);
			//디자인의 모델객체에 값을 설정한다.
			DefaultTableModel dtm = zsd.getDtmJtabResult();
			//값을 설정하기 전에 모델객체를 초기화
			dtm.setRowCount(0);
			StringBuilder sbAddr = new StringBuilder();
			String[] rowData = null;
			ZipcodeVO zVO = null;
			if(list.isEmpty()) {
				JOptionPane.showMessageDialog(zsd, dong + "동 은 존재하지 않습니다.");
			}
			for(int i = 0; i < list.size(); i++) {
				zVO = list.get(i);
				sbAddr.append(zVO.getSido()).append(" ")
				.append(zVO.getGugun()).append(" ")
				.append(zVO.getDong()).append(" ")
				.append(zVO.getBunji());
				
				rowData = new String[2];//행의 값을 넣을 배열을 만들고
				rowData[0] = zVO.getZipcode();//우편번호
				rowData[1] = sbAddr.toString();//주소를 할당
				
				//Model객체의 행으로 등록
				dtm.addRow(rowData);
				//StringBuilder 초기화
				sbAddr.delete(0, sbAddr.length());
				
			}//end for
//			dtm.setRowCount(0);
//			dtm.addRow(rowData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}//class
