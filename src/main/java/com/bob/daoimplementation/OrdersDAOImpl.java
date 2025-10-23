package com.bob.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.bob.dao.OrdersDAO;
import com.bob.model.OrderItem;
import com.bob.model.Orders;
import com.bob.utility.DbConnection;

public class OrdersDAOImpl implements OrdersDAO {

	@Override
	public boolean placeOrder(Orders order) {
	    boolean success = false;
	    String orderSql = "INSERT INTO orders (user_id, restaurant_id, total_amount, status, payment_mode, order_date, address) VALUES (?,?, ?, ?, ?, ?, ?)";

	    try (Connection con = DbConnection.getConnection();
	         PreparedStatement psOrder = con.prepareStatement(orderSql, PreparedStatement.RETURN_GENERATED_KEYS)) {

	        psOrder.setInt(1, order.getUserId());
	        psOrder.setInt(2, order.getRestaurantId());
	        psOrder.setDouble(3, order.getTotalAmount());
	        psOrder.setString(4, order.getStatus());
	        psOrder.setString(5, order.getPaymentMode());
	        psOrder.setTimestamp(6, new Timestamp(order.getOrderDate().getTime()));
	        psOrder.setString(7, order.getAddress());

	        int inserted = psOrder.executeUpdate();
	        if (inserted > 0) {
	            ResultSet rs = psOrder.getGeneratedKeys();
	            if (rs.next()) {
	                int orderId = rs.getInt(1);
	                order.setOrderId(orderId);

	                // Save order items
	                List<OrderItem> items = order.getOrderItems();
	                if (items != null && !items.isEmpty()) {
	                    for (OrderItem item : items) {
	                        item.setOrderId(orderId); // set FK
	                        boolean itemSaved = saveOrderItem(item);
	                        if (!itemSaved) {
	                            throw new Exception("Failed to save order item: " + item.getMenuName());
	                        }
	                    }
	                }
	            }
	            success = true;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        success = false;
	    }

	    return success;
	}


	@Override
	public boolean updateOrderStatus(int orderId, String status) {
		String sql = "UPDATE orders SET status=? WHERE order_id=?";
		
		try (Connection con = DbConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setString(1, status);
			ps.setInt(2, orderId);
		
			return ps.executeUpdate() > 0;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Orders getOrderById(int orderId) {

		String sql = "SELECT * FROM orders WHERE order_id=?";
		
		try (Connection con = DbConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) 
		{
			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				return new Orders(
						rs.getInt("order_id"),
						rs.getInt("user_id"),
						rs.getInt("restaurant_id"),
						rs.getTimestamp("order_date"),
						rs.getDouble("total_amount"),
						rs.getString("status"),
						rs.getString("payment_mode"),	
						rs.getString("address")
						);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Orders> getOrderByUser(int userId) {

		String sql = "SELECT * FROM orders WHERE user_id=?";
		
		List<Orders> orderList = new ArrayList<>();
		
		try (Connection con = DbConnection.getConnection();
			 PreparedStatement ps =con.prepareStatement(sql)) 
		{
			ps.setInt(1, userId);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Orders orders = new Orders(
						rs.getInt("order_id"),
						rs.getInt("user_id"),
						rs.getInt("restaurant_id"),
						rs.getTimestamp("order_date"),
						rs.getDouble("total_amount"),
						rs.getString("status"),
						rs.getString("payment_mode"),	
						rs.getString("address")
						);
				orderList.add(orders);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderList;
	}

	@Override
	public List<Orders> getOrderByRestaurantId(int restaurantId) {

		String sql = "SELECT * FROM orders WHERE restaurant_id=?";
		
		List<Orders> orderList = new ArrayList<>();
		
		try (Connection con = DbConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setInt(1, restaurantId);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Orders orders = new Orders(
						rs.getInt("order_id"),
						rs.getInt("user_id"),
						rs.getInt("restaurant_id"),
						rs.getTimestamp("order_date"),
						rs.getDouble("total_amount"),
						rs.getString("status"),
						rs.getString("payment_mode"),	
						rs.getString("address")
						);
				orderList.add(orders);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderList;
	}			


	@Override
	public List<Orders> getAllOrders() {
		String sql ="SELECT * FROM orders ";
		
		List<Orders> orderList = new ArrayList<>();
		try (Connection con = DbConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql))
		{
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Orders orders = new Orders(
						rs.getInt("order_id"),
						rs.getInt("user_id"),
						rs.getInt("restaurant_id"),
						rs.getTimestamp("order_date"),
						rs.getDouble("total_amount"),
						rs.getString("status"),
						rs.getString("payment_mode"),	
						rs.getString("address")
						);
				orderList.add(orders);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderList;
	}

	@Override
	public boolean deleteOrder(int orderId) {
		String sql ="DELETE FROM orders WHERE order_id=?";
				
		try (Connection con = DbConnection.getConnection();
			 PreparedStatement ps =con.prepareStatement(sql))
		{
			ps.setInt(1, orderId);
		    
			return ps.executeUpdate()>0;
		} 
		catch (Exception e) 
		{
			e.addSuppressed(e);
		}
		return false;
	}

	@Override
	public int saveOrderReturnId(Orders order) {
		 int generatedId = 0;
		    String sql = "INSERT INTO orders (user_id, restaurant_id, order_date, total_amount, status, payment_mode,address) VALUES (?,?, ?, ?, ?, ?, ?)";

		    try (Connection conn = DbConnection.getConnection();
		         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

		        ps.setInt(1, order.getUserId());
		        ps.setInt(2, order.getRestaurantId());
		        ps.setTimestamp(3, new Timestamp(order.getOrderDate().getTime()));
		        ps.setDouble(4, order.getTotalAmount());
		        ps.setString(5, order.getStatus());
		        ps.setString(6, order.getPaymentMode());
		        ps.setString(7, order.getAddress());

		        ps.executeUpdate();

		        try (ResultSet rs = ps.getGeneratedKeys()) {
		            if (rs.next()) {
		                generatedId = rs.getInt(1);
		            }
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return generatedId;
	}


	@Override
	public boolean saveOrderItem(OrderItem item) {
	    String sql = "INSERT INTO order_items (order_id, menu_id, quantity, total_price) VALUES (?, ?, ?, ?)";

	    try (Connection conn = DbConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, item.getOrderId());
	        ps.setInt(2, item.getMenuId());
	        ps.setInt(3, item.getQuantity());
	        ps.setDouble(4, item.getTotalPrice());

	        return ps.executeUpdate() > 0;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}



	@Override
	public boolean placeOrder(Orders order, List<OrderItem> orderItems) {
	    try {
	        // 1. Save the main order and get generated ID
	        int orderId = saveOrderReturnId(order);
	        if (orderId == 0) return false; // failed to insert main order

	        // 2. Save all order items
	        for (OrderItem item : orderItems) {
	            item.setOrderId(orderId); // assign the generated order_id
	            boolean itemSaved = saveOrderItem(item);
	            if (!itemSaved) {
	                // Optionally: rollback main order if using transactions
	                return false;
	            }
	        }

	        // 3. Success
	        order.setOrderId(orderId);
	        order.setOrderItems(orderItems);
	        return true;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}



}
