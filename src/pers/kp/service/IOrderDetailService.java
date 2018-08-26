package pers.kp.service;

import java.util.List;

import pers.kp.model.OrderDetailBean;


public interface IOrderDetailService {
	/**
	 * ��Ӷ�����������Ϣ
	 * @param odb
	 * @return
	 */
	int  add(OrderDetailBean odb);
	
	/**
	 * ɾ����������
	 * @param orderid  �����ı��
	 * @return
	 */
	int delete(String orderid);
	/**
	 * �޸Ķ�������
	 * @param odb
	 * @return
	 */
	int update(OrderDetailBean odb);
	
	/**
	 * ��ѯ�������������Ϣ
	 * @param odb
	 * @return
	 */
	List<OrderDetailBean> query(OrderDetailBean odb);
	
	/**
	 * ��ѯ��������
	 * @return
	 */
	OrderDetailBean queryById(int detailid);
	
	/**
	 * ���ݶ�����Ų�ѯ����������Ϣ
	 * @param orderid
	 * @return
	 */
	List<OrderDetailBean> queryById(String orderid);
}
