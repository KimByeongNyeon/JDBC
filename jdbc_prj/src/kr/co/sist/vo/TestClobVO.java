package kr.co.sist.vo;

import java.sql.Date;

public class TestClobVO {
	private int num;
	private String title, content, writer;
	private Date input_date;
	public TestClobVO() {
		
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setInput_date(Date input_date) {
		this.input_date = input_date;
	}
	public TestClobVO(int num, String title, String content, String writer, Date input_date) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.input_date = input_date;
	}
	public int getNum() {
		return num;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getWriter() {
		return writer;
	}
	public Date getInput_date() {
		return input_date;
	}
	@Override
	public String toString() {
		return "TestClobVO [num=" + num + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", input_date=" + input_date + "]";
	}
}
