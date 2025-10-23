package com.bob.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bob.dao.OrderItemDAO;
import com.bob.model.OrderItem;
import com.bob.utility.DbConnection;

public class OrderItemDAOImpl implements OrderItemDAO {

    @Override
    public boolean addOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO order_items (order_id, menu_id, quantity, total_price) VALUES (?, ?, ?, ?)";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderItem.getOrderId());
            ps.setInt(2, orderItem.getMenuId());
            ps.setInt(3, orderItem.getQuantity());
            ps.setDouble(4, orderItem.getTotalPrice());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateOrderItem(OrderItem orderItem) {
        String sql = "UPDATE order_items SET order_id=?, menu_id=?, quantity=?, total_price=? WHERE order_item_id=?";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderItem.getOrderId());
            ps.setInt(2, orderItem.getMenuId());
            ps.setInt(3, orderItem.getQuantity());
            ps.setDouble(4, orderItem.getTotalPrice());
            ps.setInt(5, orderItem.getOrderItemId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteOrderItem(OrderItem orderItem) {
        String sql = "DELETE FROM order_items WHERE order_item_id=?";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderItem.getOrderItemId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public OrderItem getOrderItemById(int orderItemId) {
        String sql = "SELECT oi.order_item_id, oi.order_id, oi.menu_id, oi.quantity, oi.total_price, m.item_name AS menu_name "
                   + "FROM order_items oi "
                   + "JOIN menu m ON oi.menu_id = m.menu_id "
                   + "WHERE oi.order_item_id=?";

        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderItemId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToOrderItem(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderItem> getAllOrderItemsByOrderId(int orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "SELECT oi.order_item_id, oi.order_id, oi.menu_id, oi.quantity, oi.total_price, m.item_name AS menu_name "
                   + "FROM order_items oi "
                   + "JOIN menu m ON oi.menu_id = m.menu_id "
                   + "WHERE oi.order_id=?";

        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    orderItems.add(mapResultSetToOrderItem(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItems;
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "SELECT oi.order_item_id, oi.order_id, oi.menu_id, oi.quantity, oi.total_price, m.item_name AS menu_name "
                   + "FROM order_items oi "
                   + "JOIN menu m ON oi.menu_id = m.menu_id";

        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                orderItems.add(mapResultSetToOrderItem(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItems;
    }

    // Helper method to map ResultSet to OrderItem
    private OrderItem mapResultSetToOrderItem(ResultSet rs) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(rs.getInt("order_item_id"));
        orderItem.setOrderId(rs.getInt("order_id"));
        orderItem.setMenuId(rs.getInt("menu_id"));
        orderItem.setQuantity(rs.getInt("quantity"));
        orderItem.setTotalPrice(rs.getDouble("total_price"));
        orderItem.setMenuName(rs.getString("menu_name"));
        return orderItem;
    }

}
