package com.bob.model;
import java.util.Date;
import java.util.List;

public class Orders 
{
	
	private int orderId;
	private int userId;
	private int restaurantId;
	private Date orderDate;
	private double totalAmount;
	private String Status ;
	private String paymentMode;
	private String address;
	private List<OrderItem> orderItems;
	private List<OrderHistory> histories;
	
	
	public Orders() {
	
	}

	public Orders(int orderId, int userId, int restaurantId, Date orderDate, double totalAmount, String status,
			String paymentMode, String address) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		Status = status;
		this.paymentMode = paymentMode;
		this.address=address;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	
	public static void main(String[] args)
	{
		
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	@Override
		public String toString() {
		    StringBuilder sb = new StringBuilder();
		    sb.append("Orders{orderId=").append(orderId)
		      .append(", userId=").append(userId)
		      .append(", restaurantId=").append(restaurantId)
		      .append(", orderDate=").append(orderDate)
		      .append(", totalAmount=").append(totalAmount)
		      .append(", status='").append(Status).append('\'')
		      .append(", paymentMode='").append(paymentMode).append("'}");

		    // Print order items if available
		    if (orderItems != null && !orderItems.isEmpty()) {
		        sb.append("\n Order Items:");
		        for (OrderItem item : orderItems) {
		            sb.append("\n  ").append(item.toString());
		        }
		    }

		    // Print order histories if available
		    if (histories != null && !histories.isEmpty()) {
		        sb.append("\n Order Histories:");
		        for (OrderHistory h : histories) {
		            sb.append("\n  ").append(h.toString());
		        }
		    }

		    return sb.toString();
		}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


}
