package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {
    Stage customerManagement = new Stage();
    Stage itemManagement = new Stage();
    Stage orderManagement=new Stage();
    Stage orderdetailsMenagement=new Stage();


    @FXML
    private Button btnCustomerManagement;

    @FXML
    private Button btnItemManagement;

    @FXML
    private Button btnOrderDetailManagement;

    @FXML
    private Button btnOrderManagement;

    @FXML
    void btnCustomerManagementOnAction(ActionEvent event) {
        try {
            customerManagement.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/customer_management.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        customerManagement.setResizable(false);
        customerManagement.show();
    }

    @FXML
    void btnItemManagementOnAction(ActionEvent event) {
        try {
            itemManagement.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/item_Management.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        itemManagement.setResizable(false);
        itemManagement.show();


    }

    @FXML
    void btnOrderDetailManagementOnAction(ActionEvent event) {
        try {
            orderdetailsMenagement.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/order_Detail_Management.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        orderdetailsMenagement.setResizable(false);
        orderdetailsMenagement.show();


    }
    @FXML
    void btnOrderManagementOnAction(ActionEvent event) {
        try {
            orderManagement.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/order_Management.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        orderManagement.setResizable(false);
        orderManagement.show();
    }

}
