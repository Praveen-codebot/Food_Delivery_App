package com.bob.model;

import java.time.LocalDateTime;

public class OrderHistory {
	
	private int historyId;
	private int orderId;
	private String status;
	private LocalDateTime changedAt;
	
	
	public OrderHistory()
	{
		
	}


	public OrderHistory(int historyId, int orderId, String status, LocalDateTime changedAt) {
		super();
		this.historyId = historyId;
		this.orderId = orderId;
		this.status = status;
		this.changedAt = changedAt;
	}


	public int getHistoryId() {
		return historyId;
	}


	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public LocalDateTime getChangedAt() {
		return changedAt;
	}


	public void setChangedAt(LocalDateTime changedAt) {
		this.changedAt = changedAt;
	}
	
	@Override
	public String toString() {
		return "OrderHistory{"+
				"historyId=" + historyId +
				", orderId=" + orderId +
				", status='" + status + '\''+
				", chagedAt=" + changedAt +
				'}';
	}
	
	
	

}
