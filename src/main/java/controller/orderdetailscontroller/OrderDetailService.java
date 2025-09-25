package controller.orderdetailscontroller;

import javafx.collections.ObservableList;
import model.OrderDetail;

public interface OrderDetailService {

    ObservableList <OrderDetail> getAllOrderDetails();

    void addOrderDetail(String orderID, String itemCode, int orderQty, String discount);

    void deleteOrderDetail(String OrderID , String ItemCode);

    void updateOrderDetails(String orderID, String itemCode, int orderQty, String discount);

}
