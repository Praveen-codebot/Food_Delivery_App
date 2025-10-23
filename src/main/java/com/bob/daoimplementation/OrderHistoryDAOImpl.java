package com.bob.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.bob.dao.OrderHistoryDAO;
import com.bob.model.OrderHistory;
import com.bob.utility.DbConnection;

public class OrderHistoryDAOImpl implements OrderHistoryDAO{

	@Override
	public boolean addOrderHistory(OrderHistory history) {
		String sql="INSERT INTO orderhistory (order_id, status, changed_at) VALUES(?,?,?)";
		
		try  (Connection con=DbConnection.getConnection();
			  PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setInt(1, history.getOrderId());
			ps.setString(2, history.getStatus());
			ps.setTimestamp(3, Timestamp.valueOf(history.getChangedAt()));
			
			return  ps.executeUpdate()>0;
			
		} 
		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<OrderHistory> getHistoryByOrderId(int orderId) {
		
		String sql="SELECT * FROM orderhistory WHERE order_id=?";
		
		List<OrderHistory> historyList = new ArrayList<>();
		
		try (Connection con = DbConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) 
		{
			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				OrderHistory orderHistory = new OrderHistory(
						rs.getInt("history_id"),
						rs.getInt("order_id"),
						rs.getString("status"),
						rs.getTimestamp("changed_at").toLocalDateTime()
						);
				historyList.add(orderHistory);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return historyList;
	}

	@Override
	public OrderHistory getLatestHistoryForOrder(int orderId) {

		String sql ="SELECT * FROM orderhistory WHERE order_id=? ORDER BY changed_at DESC LIMIT 1";
		
		try  (Connection con = DbConnection.getConnection();
			  PreparedStatement ps =con.prepareStatement(sql))
		{
			ps.setInt(1, orderId);
			ResultSet rs= ps.executeQuery();
			
			if(rs.next())
			{
			return new OrderHistory(
						rs.getInt("history_id"),
						rs.getInt("order_id"),
						rs.getString("status"),
						rs.getTimestamp("changed_at").toLocalDateTime()
						);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<OrderHistory> getAllOrderHistories() {

		String sql ="SELECT * FROM orderhistory ORDER BY changed_at DESC";
		List<OrderHistory> historyList = new ArrayList<>();
		
		try (Connection con = DbConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) 
		{
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				OrderHistory orderHistory = new OrderHistory(
						rs.getInt("history_id"),
						rs.getInt("order_id"),
						rs.getString("status"),
						rs.getTimestamp("changed_at").toLocalDateTime()
						);
				historyList.add(orderHistory);
			
		    }
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return historyList;
	}

	@Override
	public boolean deleteOrderHistory(int historyId) {

		String sql = "DELETE FROM orderhistory WHERE history_id=?";
		
		try (Connection con = DbConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setInt(1, historyId);
			
			return ps.executeUpdate() > 0;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public OrderHistory getOrderHistoryById(int historyId) {

		String sql = "SELECT * FROM orderhistory WHERE history_id=?";
		
		try (Connection con = DbConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setInt(1, historyId);
			ResultSet rs= ps.executeQuery();
			
			if(rs.next())
			{
			return new OrderHistory(
						rs.getInt("history_id"),
						rs.getInt("order_id"),
						rs.getString("status"),
						rs.getTimestamp("changed_at").toLocalDateTime()
						);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<OrderHistory> getOrderHistoryForUser(int userId) {

		List<OrderHistory> historyList = new ArrayList<>();
		String sql ="SELECT oh.* " +
				 "FROM orderhistory oh " + 
				" JOIN orders o ON oh.order_id = o.order_id " + 
				 " WHERE o.user_id=? " + 
				 "ORDER BY oh.changed_at DESC";
		
		try (Connection con = DbConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) 
		{
			ps.setInt(1,userId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				OrderHistory history = new OrderHistory(
		                rs.getInt("history_id"),
		                rs.getInt("order_id"),
		                rs.getString("status"),
		                rs.getTimestamp("changed_at").toLocalDateTime()

						);
				historyList.add(history);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return historyList;
	}

}
