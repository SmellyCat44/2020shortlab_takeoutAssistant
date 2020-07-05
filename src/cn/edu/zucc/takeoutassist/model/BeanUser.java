package cn.edu.zucc.takeoutassist.model;

import java.util.Date;

public class BeanUser {
	private String user_id;
	private String user_pwd;
	private String user_name;
	private String sex;
	private int user_tel;
	private String email;
	private String user_city;
	private Date reg_time;
	private  boolean is_vip;
	private Date vip_due_date;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(int user_tel) {
		this.user_tel = user_tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUser_city() {
		return user_city;
	}
	public void setUser_city(String user_city) {
		this.user_city = user_city;
	}
	public Date getReg_time() {
		return reg_time;
	}
	public void setReg_time(Date reg_time) {
		this.reg_time = reg_time;
	}
	public boolean isIs_vip() {
		return is_vip;
	}
	public void setIs_vip(boolean is_vip) {
		this.is_vip = is_vip;
	}
	public Date getVip_due_date() {
		return vip_due_date;
	}
	public void setVip_due_date(Date vip_due_date) {
		this.vip_due_date = vip_due_date;
	}

}
