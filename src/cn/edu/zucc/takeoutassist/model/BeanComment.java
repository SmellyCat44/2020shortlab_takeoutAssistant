package cn.edu.zucc.takeoutassist.model;

import java.awt.Image;
import java.util.Date;

public class BeanComment {
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCmt_date() {
		return cmt_date;
	}
	public void setCmt_date(Date cmt_date) {
		this.cmt_date = cmt_date;
	}
	public String getCmt_rank() {
		return cmt_rank;
	}
	public void setCmt_rank(String cmt_rank) {
		this.cmt_rank = cmt_rank;
	}
	public Image getPic() {
		return pic;
	}
	public void setPic(Image pic) {
		this.pic = pic;
	}
	private String p_id;
	private String user_id;
	private String shop_id;
	private String content;
	private Date cmt_date;
	private String cmt_rank;
	private Image pic;//Õº∆¨¿‡£ø£ø
}
