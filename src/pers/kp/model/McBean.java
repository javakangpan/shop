package pers.kp.model;

import java.sql.Date;

public class McBean {
	private int mcid;
	private String mcname;
	private String mcdecx;
	private double price;
	private String pic;
	private String flag;
	private int smalltypeid;
	private Date createdate;
	private int quantity;
	
	
	private int count;
	
	
	
	
	
	public McBean() {
		super();
	}
	public McBean(int mcid, String mcname, String mcdecx, double price, String pic, String flag, int smalltypeid,
			Date createdate, int quantity) {
		super();
		this.mcid = mcid;
		this.mcname = mcname;
		this.mcdecx = mcdecx;
		this.price = price;
		this.pic = pic;
		this.flag = flag;
		this.smalltypeid = smalltypeid;
		this.createdate = createdate;
		this.quantity = quantity;

	}
	public int getMcid() {
		return mcid;
	}
	public void setMcid(int mcid) {
		this.mcid = mcid;
	}
	public String getMcname() {
		return mcname;
	}
	public void setMcname(String mcname) {
		this.mcname = mcname;
	}
	public String getMcdecx() {
		return mcdecx;
	}
	public void setMcdecx(String mcdecx) {
		this.mcdecx = mcdecx;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public int getSmalltypeid() {
		return smalltypeid;
	}
	public void setSmalltypeid(int smalltypeid) {
		this.smalltypeid = smalltypeid;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getTotalPrice(){
		
		return this.price*this.count;
		
		
	}

}
