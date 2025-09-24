package controller.ordercontroller;

import javafx.collections.ObservableList;
import model.Order;

public interface OrderManagementService {

    ObservableList<Order> getAllOrders();

    void addOrders(String orderID, String orderDate, String custID);

    void deleteOrder(String orderID);

    void updateOrder(String orderID, String orderDate, String custID);
}