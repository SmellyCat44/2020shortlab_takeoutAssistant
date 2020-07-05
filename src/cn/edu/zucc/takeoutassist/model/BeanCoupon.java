package cn.edu.zucc.takeoutassist.model;

import java.util.Date;

public class BeanCoupon {
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
	public String getO_id() {
		return o_id;
	}
	public void setO_id(String o_id) {
		this.o_id = o_id;
	}
	public Double getC_mon() {
		return c_mon;
	}
	public void setC_mon(Double c_mon) {
		this.c_mon = c_mon;
	}
	public int getC_num() {
		return c_num;
	}
	public void setC_num(int c_num) {
		this.c_num = c_num;
	}
	public Date getC_start_date() {
		return c_start_date;
	}
	public void setC_start_date(Date c_start_date) {
		this.c_start_date = c_start_date;
	}
	public Date getC_end_date() {
		return c_end_date;
	}
	public void setC_end_date(Date c_end_date) {
		this.c_end_date = c_end_date;
	}
	private String c_id;
	private String shop_id;
	private String o_id;
	private Double c_mon;//优惠金额
	private int c_num;//集单要求数
	private Date c_start_date;
	private Date c_end_date;
}
