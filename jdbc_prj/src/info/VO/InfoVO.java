package info.VO;

import java.sql.Date;

import javax.swing.JTextField;

public class InfoVO {
	private int num, age;
	private String name, img;
	private String input_date;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String jTextField) {
		this.name = jTextField;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getInputdate() {
		return input_date;
	}
	public void setInputdate(String formattedDate) {
		this.input_date = formattedDate;
	}
	public InfoVO() {
		
	}
	public InfoVO(int num, int age, String name, String img, String input_date) {
		super();
		this.num = num;
		this.age = age;
		this.name = name;
		this.img = img;
		this.input_date = input_date;
	}
	@Override
	public String toString() {
		return "InfoVO [num=" + num + ", age=" + age + ", name=" + name + ", img=" + img + ", input_date=" + input_date
				+ "]";
	}
	
	
}
