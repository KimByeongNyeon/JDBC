package day_0307;

import java.sql.Date;

public class LoginResultVO {
	private String name;
	private Date inputDate;
	
	public LoginResultVO() {
	}

	public LoginResultVO(String name, Date inputDate) {
		super();
		this.name = name;
		this.inputDate = inputDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	@Override
	public String toString() {
		return "LoginResultVO [name=" + name + ", inputDate=" + inputDate + "]";
	}
	
	
}
