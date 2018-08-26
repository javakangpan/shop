package pers.kp.dao;

import java.util.List;

import pers.kp.model.OrderBean;

import pers.kp.model.PageBean;

/**
 * ����������ķ���
 * @author 
 *
 */
public interface IOrderDao {
	/**
	 * ��Ӷ�������Ϣ
	 * @param ob Ҫ��ӵĶ�����Ϣ
	 * @return
	 * 		-1  ��ʾ���ʧ��
	 * 		������ʾ��ӳɹ�
	 */
	int add(OrderBean ob);
	
	/**
	 * ɾ������   ɾ��������ͬʱҲҪɾ������������е��������
	 * @param orderid  �����ı��
	 * @return
	 */
	int delete(String orderid);
	
	/**
	 * �޸Ķ���
	 * @param ob
	 * @return
	 */
	int update(OrderBean ob);
	
	/**
	 * ��ѯ���������Ϣ
	 * @param ob
	 * @return
	 */
	List<OrderBean> query(OrderBean ob);
	
	/**
	 * ��ѯ����������Ϣ
	 * @param orderid
	 * @return
	 */
	OrderBean queryById(String orderid);

	/**
	 * ��ҳ��ѯ
	 * @param ob
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageBean queryPageModel(OrderBean ob, int currentPage, int pageSize);
}
