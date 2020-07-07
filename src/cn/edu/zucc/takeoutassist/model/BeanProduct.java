package cn.edu.zucc.takeoutassist.model;

public class BeanProduct {
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public Double getP_price() {
		return p_price;
	}
	public void setP_price(Double p_price) {
		this.p_price = p_price;
	}
	public Double getP_dsc_price() {
		return p_dsc_price;
	}
	public void setP_dsc_price(Double p_dsc_price) {
		this.p_dsc_price = p_dsc_price;
	}
	private int p_id;
	private int type_id;
	private String p_name;
	private Double p_price;
	private Double p_dsc_price;//”≈ª›º€∏Ò
}
