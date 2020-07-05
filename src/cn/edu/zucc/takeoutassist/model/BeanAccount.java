package cn.edu.zucc.takeoutassist.model;

import java.util.Date;

public class BeanAccount {
	public String getR_id() {
		return r_id;
	}
	public void setR_id(String r_id) {
		this.r_id = r_id;
	}
	public String getO_id() {
		return o_id;
	}
	public void setO_id(String o_id) {
		this.o_id = o_id;
	}
	public Date getAcc_time() {
		return acc_time;
	}
	public void setAcc_time(Date acc_time) {
		this.acc_time = acc_time;
	}
	public String getR_cmmt() {
		return r_cmmt;
	}
	public void setR_cmmt(String r_cmmt) {
		this.r_cmmt = r_cmmt;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	private String r_id;
	private String o_id;
	private Date acc_time;
	private String r_cmmt;
	private double income;
}
