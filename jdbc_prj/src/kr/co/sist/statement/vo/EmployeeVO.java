package kr.co.sist.statement.vo;

import java.sql.Date;

public class EmployeeVO {
	private int empno;		
	private String ename, job;
	private Double sal;
	private Date hiredate;
	public int getEmpno() {
		return empno;
	}
	
	public EmployeeVO() {
		
	}
	
	public EmployeeVO(int empno, String ename, String job, Double sal, Date hiredate) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.sal = sal;
		this.hiredate = hiredate;
	}
	
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Double getSal() {
		return sal;
	}
	public void setSal(Double sal) {
		this.sal = sal;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	
	@Override
	public String toString() {
		return "EmployeeVO [empno=" + empno + ", ename=" + ename + ", job=" + job + ", sal=" + sal + ", hiredate="
				+ hiredate + "]";
	}
	
	
}