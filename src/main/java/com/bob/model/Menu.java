package com.bob.model;

public class Menu 
{
	
	private int menuid ;
	private int restaurantId;
	private String itemName;
	private String description;
	private double price;
	private double ratings;
	private boolean isAvailable;
	private String imagePath;
	private int quantity;
	
	public Menu() 
	{
		
		
	}
	
	
	
	public Menu(int menuid, int restaurantId, String itemName, String description, double price, double ratings,
			Boolean isAvailable, String imagePath) {
		super();
		this.menuid =menuid;
		this.restaurantId = restaurantId;
		this.itemName = itemName;
		this.description = description;
		this.price = price;
		this.ratings = ratings;
		this.isAvailable = isAvailable;
		this.imagePath = imagePath;
	}
	



	public int getMenuId() {
		return menuid;
	}



	public void setMenuId(int menuid) {
		this.menuid = menuid;
	}



	public int getRestaurantId() {
		return restaurantId;
	}



	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}



	public String getItemName() {
		return itemName;
	}



	public void setItemName(String itemName) {
		this.itemName = itemName;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public double getRatings() {
		return ratings;
	}



	public void setRatings(double ratings) {
		this.ratings = ratings;
	}



	public boolean getIsAvailable() {
		return isAvailable;
	}



	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}



	public String getImagePath() {
		return imagePath;
	}



	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}



	public static void main(String[] args)
	{
		
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}





}
