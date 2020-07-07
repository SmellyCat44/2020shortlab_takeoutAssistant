package cn.edu.zucc.takeoutassist.model;

import java.util.Date;

public class BeanOrder {
	public int getO_id() {
		return o_id;
	}
	public void setO_id(int o_id) {
		this.o_id = o_id;
	}
	public int getDsc_id() {
		return dsc_id;
	}
	public void setDsc_id(int dsc_id) {
		this.dsc_id = dsc_id;
	}
	public int getAddr_id() {
		return addr_id;
	}
	public void setAddr_id(int addr_id) {
		this.addr_id = addr_id;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getShop_id() {
		return shop_id;
	}
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	public Double getRow() {
		return row;
	}
	public void setRow(Double row) {
		this.row = row;
	}
	public Double getMon() {
		return mon;
	}
	public void setMon(Double mon) {
		this.mon = mon;
	}
	public Date getO_time() {
		return o_time;
	}
	public void setO_time(Date o_time) {
		this.o_time = o_time;
	}
	public Date getRq_time() {
		return rq_time;
	}
	public void setRq_time(Date rq_time) {
		this.rq_time = rq_time;
	}
	public String getO_status() {
		return o_status;
	}
	public void setO_status(String o_status) {
		this.o_status = o_status;
	}
	public String getR_id() {
		return r_id;
	}
	public void setR_id(String r_id) {
		this.r_id = r_id;
	}
	private int o_id;
	private String user_id;
	private int dsc_id;
	private String shop_id;
	private int addr_id;
	private int c_id;
	private Double row;
	private Double mon;
	private Date o_time;
	private Date rq_time;
	private String o_status;
	private String r_id;
}
