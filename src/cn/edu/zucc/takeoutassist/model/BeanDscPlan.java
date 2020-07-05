package cn.edu.zucc.takeoutassist.model;

public class BeanDscPlan {
	public String getDsc_id() {
		return dsc_id;
	}
	public void setDsc_id(String dsc_id) {
		this.dsc_id = dsc_id;
	}
	public double getDsc_minus() {
		return dsc_minus;
	}
	public void setDsc_minus(double dsc_minus) {
		this.dsc_minus = dsc_minus;
	}
	public double getDsc_mon() {
		return dsc_mon;
	}
	public void setDsc_mon(double dsc_mon) {
		this.dsc_mon = dsc_mon;
	}
	public boolean isDsc_overlay() {
		return dsc_overlay;
	}
	public void setDsc_overlay(boolean dsc_overlay) {
		this.dsc_overlay = dsc_overlay;
	}
	private String dsc_id;
	private double dsc_minus;
	private double dsc_mon;
	private boolean dsc_overlay;
	
}
