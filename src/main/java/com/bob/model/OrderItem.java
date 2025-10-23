package com.bob.model;

public class OrderItem 
{

	private int orderItemId;
	private int orderId;
	private int menuId;
	private int quantity;
	private double totalPrice;
	private String itemName;
	private String menuName;
	
	
	
	
	public OrderItem() 
	{
		
	}



	public OrderItem(int orderItemId, int orderId, int menuId, int quantity, double totalPrice , String menuName) {
		super();
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.menuName = menuName;
		
	}



	public int getOrderItemId() {
		return orderItemId;
	}



	public void setOrderItemId(int orderItemID) {
		this.orderItemId = orderItemID;
	}



	public int getOrderId() {
		return orderId;
	}



	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}



	public int getMenuId() {
		return menuId;
	}



	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public double getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public static void main(String[] args) 
	{
		
	}



	public String getItemName() {
		return itemName;
	}



	public void setItemName(String itemName) {
		this.itemName = itemName;
	}



	public String getMenuName() {
		return menuName;
	}



	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	
}
