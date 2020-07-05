package cn.edu.zucc.takeoutassist.model;

import java.util.Date;

public class BeanOwn {
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getShop_id() {
		return shop_id;
	}
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	public Double getC_mon() {
		return c_mon;
	}
	public void setC_mon(Double c_mon) {
		this.c_mon = c_mon;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getUser_due_date() {
		return user_due_date;
	}
	public void setUser_due_date(Date user_due_date) {
		this.user_due_date = user_due_date;
	}
	private String user_id;
	private String c_id;
	private String shop_id;
	private Double c_mon;//се╩щ╫П╤Н
	private int quantity;
	private Date user_due_date;
}
