package cn.edu.zucc.takeoutassist.model;

import java.util.Date;

public class BeanRider {
	public String getR_pwd() {
		return r_pwd;
	}
	public void setR_pwd(String r_pwd) {
		this.r_pwd = r_pwd;
	}
	public String getR_id() {
		return r_id;
	}
	public void setR_id(String r_id) {
		this.r_id = r_id;
	}
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	public Date getR_date() {
		return r_date;
	}
	public void setR_date(Date r_date) {
		this.r_date = r_date;
	}
	public String getR_rank() {
		return r_rank;
	}
	public void setR_rank(String r_rank) {
		this.r_rank = r_rank;
	}
	private String r_id;
	private String r_pwd;
	private String r_name;
	private Date r_date;//入职日期
	private String r_rank;
	
}
