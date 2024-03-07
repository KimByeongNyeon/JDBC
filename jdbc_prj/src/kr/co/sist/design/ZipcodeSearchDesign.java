package kr.co.sist.design;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.prepared.evt.ZipcodeSearchDesignEvent;

@SuppressWarnings("serial")
public class ZipcodeSearchDesign extends JFrame {

	private JTextField jtfDong;
	private JButton jbtnSearch;
	private JTable jtabResult;
	private DefaultTableModel dtmJtabResult;

	public ZipcodeSearchDesign() {
		super("우편번호 검색");
		jtfDong = new JTextField(10);
		jbtnSearch = new JButton("검색");

		String[] columnName = { "우편번호", "주소" };
		// 행이 없고, 컬럼명만 가진 DefaultTablemodel을 생성
		dtmJtabResult = new DefaultTableModel(columnName, 0);

		jtabResult = new JTable(dtmJtabResult);
		
		JScrollPane jspJtaResult = new JScrollPane(jtabResult);
		jspJtaResult.setBorder(new TitledBorder("검색결과"));
		
		//JTable 컬럼의 넓이 변경
		jtabResult.getColumnModel().getColumn(0).setPreferredWidth(80);
		jtabResult.getColumnModel().getColumn(1).setPreferredWidth(720);
		//배치
		JPanel jpNorth = new JPanel();
		jpNorth.add(new JLabel("동) "));
		jpNorth.add(jtfDong);
		jpNorth.add(jbtnSearch);
		
		add("North", jpNorth);
		add("Center", jspJtaResult);
		
		ZipcodeSearchDesignEvent zsde = new ZipcodeSearchDesignEvent(this);
		jtfDong.addActionListener(zsde);
		jbtnSearch.addActionListener(zsde);
		addWindowListener(zsde);
		
		setBounds(100, 100, 800, 600);
		setVisible(true);
	}

	public JTextField getJtfDong() {
		return jtfDong;
	}

	public JButton getJbtnSearch() {
		return jbtnSearch;
	}

	public JTable getJtabResult() {
		return jtabResult;
	}

	public DefaultTableModel getDtmJtabResult() {
		return dtmJtabResult;
	}

	public static void main(String[] args) {
		new ZipcodeSearchDesign();
	}// main

}// class
