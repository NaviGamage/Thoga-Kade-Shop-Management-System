package controller.ordercontroller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderManagementController implements OrderManagementService{
    @Override
    public ObservableList<Order> getAllOrders() {

        ObservableList <Order> orders = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM orders");
            ResultSet rst = pstm.executeQuery();
            while (rst.next()){
                orders.add(new Order(
                        rst.getString("OrderID"),
                        rst.getString("OrderDate"),
                        rst.getString("CustID")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    @Override
    public void addOrders(String orderID, String orderDate, String custID) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO orders VALUES (? , ? , ?);");
            pstm.setObject(1,orderID);
            pstm.setObject(2,orderDate);
            pstm.setObject(3,custID);

            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrder(String orderID) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM orders WHERE OrderID = ?");
            pstm.setObject(1,orderID);

            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateOrder(String orderID, String orderDate, String custID) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE orders SET OrderID = ? , OrderDate = ? , CustId = ? WHERE OrderID = ?");
            pstm.setObject(1,orderID);
            pstm.setObject(2,orderDate);
            pstm.setObject(3,custID);
            pstm.setObject(4,orderID);

            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
