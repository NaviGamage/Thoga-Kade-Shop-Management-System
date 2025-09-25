package controller.orderdetailcontroller;

import controller.orderdetailscontroller.OrderDetailService;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailManagementController implements OrderDetailService {


    @Override
    public ObservableList<OrderDetail> getAllOrderDetails() {

        ObservableList <OrderDetail> orderDetails = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM orderDetail");
            ResultSet rst = pstm.executeQuery();

            while (rst.next()){
                orderDetails.add(new OrderDetail(
                        rst.getString("OrderID"),
                        rst.getString("ItemCode"),
                        rst.getInt("OrderQty"),
                        rst.getString("Discount")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderDetails;
    }

    @Override
    public void addOrderDetail(String orderID, String itemCode, int orderQty, String discount) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO orderDetail VALUES (? , ? , ? , ?);");
            pstm.setObject(1,orderID);
            pstm.setObject(2,itemCode);
            pstm.setObject(3,orderQty);
            pstm.setObject(4,discount);

            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteOrderDetail(String OrderID, String ItemCode) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM orderDetail WHERE OrderID = ? && ItemCode = ?");
            pstm.setObject(1,OrderID);
            pstm.setObject(2,ItemCode);

            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateOrderDetails(String orderID, String itemCode, int orderQty, String discount) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE orderDetail SET OrderID = ? , ItemCode = ? , OrderQty = ? , Discount = ? WHERE OrderID = ? AND ItemCode = ?");
            pstm.setObject(1,orderID);
            pstm.setObject(2,itemCode);
            pstm.setObject(3,orderQty);
            pstm.setObject(4,discount);
            pstm.setObject(5,orderID);
            pstm.setObject(6,itemCode);

            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
