package info.design;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import info.VO.InfoVO;
import info.evt.InfoDesignEvent;

@SuppressWarnings("serial")
public class InfoDesgin extends JFrame {

	private JTextField jtfNum, jtfName, jtfAge, jtfInputDate;
	private JButton jbtnAdd, jbtnChange, jbtnDelete, jbtnQuit;
	private JLabel jlImg;
	private JComboBox<String>jcbImg;
	private DefaultListModel<String> listModel;
	
	public JComboBox<String> getJcbImg() {
		return jcbImg;
	}

	private JList<String> infoList;
	public DefaultListModel<String> getListModel() {
		return listModel;
	}

	public void setInfoList(JList<String> infoList) {
		this.infoList = infoList;
	}

	public void setListModel(DefaultListModel<String> listModel) {
		this.listModel = listModel;
		infoList.setModel(listModel);
	}

	private JLabel imgLabel;
	public JTextField getJtfNum() {
		return jtfNum;
	}

	public JTextField getJtfName() {
		return jtfName;
	}

	public void setJtfNum(int count) {
		jtfNum.setText(Integer.toString(count));
	}


	public JTextField getJtfAge() {
		return jtfAge;
	}

	public JTextField getJtfInputDate() {
		return jtfInputDate;
	}

	public void setJtfInputDate(String formattedDate) {
		jtfInputDate.setText(formattedDate);
	}

	public JButton getJbtnAdd() {
		return jbtnAdd;
	}

	public JButton getJbtnChange() {
		return jbtnChange;
	}

	public JButton getJbtnDelete() {
		return jbtnDelete;
	}

	public JButton getJbtnQuit() {
		return jbtnQuit;
	}

	public JList<String> getInfoList() {
		return infoList;
	}

	public JLabel getImgLabel() {
		return imgLabel;
	}
	
	public InfoDesgin() {
		super("회원 정보 확인");
		String[] items = {"img1.png","img2.png","img3.png","img4.png"};
		jcbImg = new JComboBox<>(items);
		jtfNum  = new JTextField();
		jtfNum.setEditable(false);
		jtfInputDate  = new JTextField(10);
		jtfInputDate.setEditable(false);
		jtfName = new JTextField(10);
		
		jtfAge = new JTextField(10);
		
		JLabel lblNum = new JLabel("번호:");
        JLabel lblName = new JLabel("이름:");
        JLabel lblImg = new JLabel("이미지:");
        JLabel lblAge = new JLabel("나이:");
        JLabel lblInputDate = new JLabel("입력일:");
        JLabel jlImg = new JLabel("아이콘 넣어라");
        
        
        listModel = new DefaultListModel<>();
        infoList = new JList<>(listModel);
        
        JScrollPane jsp = new JScrollPane(infoList);
        
		
		
		jbtnAdd = new JButton("추가");
		jbtnChange = new JButton("변경");
		jbtnDelete = new JButton("삭제");
		jbtnQuit = new JButton("종료");
		InfoDesignEvent ide = new InfoDesignEvent(this);
		jbtnAdd.addActionListener(ide);
		jbtnDelete.addActionListener(ide);
		jbtnChange.addActionListener(ide);
		jbtnQuit.addActionListener(ide);
		infoList.addMouseListener(ide);
			
		JPanel panel = new JPanel(new GridLayout(5,2));
		panel.add(lblNum);
        panel.add(jtfNum);
        panel.add(lblName);
        panel.add(jtfName);
        panel.add(lblImg);
        panel.add(jcbImg);
        panel.add(lblAge);
        panel.add(jtfAge);
        panel.add(lblInputDate);
        panel.add(jtfInputDate);
        
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(jsp, BorderLayout.CENTER);
        
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(jbtnAdd);
        buttonPanel.add(jbtnChange);
        buttonPanel.add(jbtnDelete);
        buttonPanel.add(jbtnQuit);
		
        JPanel imgPanel = new JPanel(new BorderLayout());
        imgPanel.add(jlImg);
		
        add(panel, BorderLayout.WEST);
		add(listPanel, BorderLayout.CENTER);
		add(imgPanel, BorderLayout.EAST);
		add(buttonPanel,BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 300);
		setResizable(false);
		setVisible(true);
		
		
	}


}
