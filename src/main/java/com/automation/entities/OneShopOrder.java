/**
 * 
 */
package com.automation.entities;

/**
 * @ClassName:     OneShopOrder.java
 * @Description:   一站式订单实体类
 * 
 * @author         mazhaoyang
 * @version        V1.0  
 * @Date           2021年1月22日 上午10:43:31 
 */
public class OneShopOrder {
	String name;
	String phoneNumber;
	String DDOrder;
	String HTOrder;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDDOrder() {
		return DDOrder;
	}
	public void setDDOrder(String dDOrder) {
		DDOrder = dDOrder;
	}
	public String getHTOrder() {
		return HTOrder;
	}
	public void setHTOrder(String hTOrder) {
		HTOrder = hTOrder;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{name:"+name+",phoneNumber:"+phoneNumber+",DDOrder:"+DDOrder+",HTOrder:"+HTOrder+"}";
	}
	

}
