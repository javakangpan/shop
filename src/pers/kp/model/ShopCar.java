package pers.kp.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class ShopCar {
	
	private List<McBean> list = new ArrayList<McBean>();
	
	
	private ShopCar() {
	}
	
	
	public List<McBean> getList(){
		return list;
	}
	
	
	public void setList(List<McBean> list){
		this.list=list;
	}
	
	public static ShopCar getShopCarInstance(HttpSession session) {
		ShopCar shopCar = (ShopCar) session.getAttribute("SHOP_CAR");
		if (shopCar == null) {
			shopCar = new ShopCar();
			session.setAttribute("SHOP_CAR", shopCar);
		}
		return shopCar;
	}
	
	
	
	
	
	public void addMc(McBean mc) {
		boolean flag = true;
		for (McBean m : list) {
			if (m.getMcid() == mc.getMcid()) {
				m.setCount(m.getCount() + 1);
				flag = false;
				return;
			}
		}
		if (flag) {
			mc.setCount(1);
			list.add(mc);
		}
	}
	
	
	
	public void deleteMc(int mcid) {
		for (McBean mc : list) {
			if (mc.getMcid() == mcid) {
				list.remove(mc);
				return;
			}
		}
	}
	
	public void update(int mcid, int count) {
		for (McBean mc : list) {
			if (mc.getMcid() == mcid) {
				mc.setCount(count);
				return;
			}
		}
	}
	
	
	public void updateMc(McBean mc){
		for (McBean m : list) {
			
			if(m.getMcid() == mc.getMcid()){
				m.setCount(mc.getCount());
				break;
			}
		}
	}
	
	
	
	//如果是驼峰命名法 默认机制是  getTotalCount-->就是它的属性
	public int getTotalCount(){
		int totalCount=0;
		for (McBean mc : list) {
			totalCount+=mc.getCount();
		}
		return totalCount;
	}
	
	public int getTotalType(){
		return list.size();
	}
	
	
	public double getTotalPrice(){
		double totalPrice=0;
		for (McBean mc : list) {
			totalPrice+=mc.getTotalPrice();
		}
		return totalPrice;
	}
	
	
	
	
	public void clear(){
		list.clear();
	}
	
	
	
	
	
}
