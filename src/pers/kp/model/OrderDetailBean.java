package pers.kp.model;

/**
 * ������������Ϣ
 * @author
 *
 */
public class OrderDetailBean {
	
	private int detailid;  //��������ı��
	private String orderid;  //������� 
	private int mcid;  //��Ʒ���
	private int buynum;  //�������Ʒ����
	/**
	 * ��ʾ��ǰ�Ķ��������������ĸ�����
	 * ���Ի�ȡ���˶���������Ϣ  �Ϳ��Ի�ȡ��������������Ϣ
	 * ���1��ϵ
	 */
	private OrderBean orderBean;//���������-->������
	public OrderDetailBean(int detailid, String orderid, int mcid, int buynum, OrderBean orderBean) {
		super();
		this.detailid = detailid;
		this.orderid = orderid;
		this.mcid = mcid;
		this.buynum = buynum;
		this.orderBean = orderBean;
	}
	public OrderDetailBean() {
		super();
	}
	public int getDetailid() {
		return detailid;
	}
	public void setDetailid(int detailid) {
		this.detailid = detailid;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public int getMcid() {
		return mcid;
	}
	public void setMcid(int mcid) {
		this.mcid = mcid;
	}
	public int getBuynum() {
		return buynum;
	}
	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}
	public OrderBean getOrderBean() {
		return orderBean;
	}
	public void setOrderBean(OrderBean orderBean) {
		this.orderBean = orderBean;
	}
	
}
