package cn.edu.zucc.takeoutassist.model;

public class BeanOrderDetail {
	public String getO_id() {
		return o_id;
	}
	public void setO_id(String o_id) {
		this.o_id = o_id;
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public int getAmt() {
		return amt;
	}
	public void setAmt(int amt) {
		this.amt = amt;
	}
	public double getFinal_price() {
		return final_price;
	}
	public void setFinal_price(double final_price) {
		this.final_price = final_price;
	}
	public double getDsc_sig() {
		return dsc_sig;
	}
	public void setDsc_sig(double dsc_sig) {
		this.dsc_sig = dsc_sig;
	}
	private String o_id;
	private String p_id;
	private int amt;
	private double final_price;//价格
	private double dsc_sig;//单品优惠金额
}
