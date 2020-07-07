package cn.edu.zucc.takeoutassist.model;

public class BeanShop {
	public String getShop_pwd() {
		return shop_pwd;
	}
	public void setShop_pwd(String shop_pwd) {
		this.shop_pwd = shop_pwd;
	}
	public String getShop_id() {
		return shop_id;
	}
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getShop_rank() {
		return shop_rank;
	}
	public void setShop_rank(String shop_rank) {
		this.shop_rank = shop_rank;
	}
	public double getAvg_csm() {
		return avg_csm;
	}
	public void setAvg_csm(double avg_csm) {
		this.avg_csm = avg_csm;
	}
	public int getSum_vlm() {
		return sum_vlm;
	}
	public void setSum_vlm(int sum_vlm) {
		this.sum_vlm = sum_vlm;
	}
	private String shop_id;
	private String shop_pwd;
	private String shop_name;
	private String shop_rank;
	private double avg_csm;
	private int sum_vlm;
	
}
