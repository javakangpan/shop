package pers.kp.model;

import java.sql.Date;

/**
 * ����Ա�û���Ϣ
 * @author 
 *
 */
public class ManagerBean {

	private int mid;
	private String musername;
	private String mpassword;
	private String mtruename;
	private String msex;
	private int mlevel; //0��ʾ��������Ա  1��ʾ��ͨ����Ա
	private Date mdate;
	public ManagerBean() {
		super();
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMusername() {
		return musername;
	}
	public void setMusername(String musername) {
		this.musername = musername;
	}
	public String getMpassword() {
		return mpassword;
	}
	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}
	public String getMtruename() {
		return mtruename;
	}
	public void setMtruename(String mtruename) {
		this.mtruename = mtruename;
	}
	public String getMsex() {
		return msex;
	}
	public void setMsex(String msex) {
		this.msex = msex;
	}
	public int getMlevel() {
		return mlevel;
	}
	public void setMlevel(int mlevel) {
		this.mlevel = mlevel;
	}
	public Date getMdate() {
		return mdate;
	}
	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}  
	
}
