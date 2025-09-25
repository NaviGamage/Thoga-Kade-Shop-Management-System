package controller.orderdetailscontroller;

import controller.orderdetailcontroller.OrderDetailManagementController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.OrderDetail;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderDetailManagementFormController implements Initializable {

    ObservableList<OrderDetail> orderDetails = FXCollections.observableArrayList();

    OrderDetailService orderDetailService = new OrderDetailManagementController();

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colOrderID;

    @FXML
    private TableColumn<?, ?> colOrderQty;

    @FXML
    private TableView<OrderDetail> tblOrderDetails;

    @FXML
    private TextField txtDiscount;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtOrderID;

    @FXML
    private TextField txtOrderQty;

    @FXML
    void btnAddOnAction(ActionEvent event) {

        String OrderID = txtOrderID.getText();
        String ItemCode = txtItemCode.getText();
        int OrderQty = Integer.parseInt(txtOrderQty.getText());
        String Discount = txtDiscount.getText();

        orderDetailService.addOrderDetail(OrderID , ItemCode , OrderQty , Discount);

        loadOrderDetails();

        clearData();

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

        clearData();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        orderDetailService.deleteOrderDetail(txtOrderID.getText() , txtItemCode.getText());

        loadOrderDetails();

        clearData();

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String OrderID = txtOrderID.getText();
        String ItemCode = txtItemCode.getText();
        int OrderQty = Integer.parseInt(txtOrderQty.getText());
        String Discount = txtDiscount.getText();

        orderDetailService.updateOrderDetails(OrderID , ItemCode , OrderQty , Discount);

        loadOrderDetails();

        clearData();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colOrderID.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colOrderQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("Discount"));

        loadOrderDetails();

        tblOrderDetails.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue != null){
                System.out.println(newValue);

                setSelectedValue(newValue);

            }
        });

    }

    private void loadOrderDetails(){

        orderDetails.clear();

        orderDetails = orderDetailService.getAllOrderDetails();

        tblOrderDetails.setItems(orderDetails);

    }

    private void setSelectedValue(OrderDetail selectedValues){
        txtOrderID.setText(selectedValues.getOrderID());
        txtItemCode.setText(selectedValues.getItemCode());
        txtOrderQty.setText(String.valueOf(selectedValues.getQty()));
        txtDiscount.setText(selectedValues.getDiscount());

    }

    private void clearData(){
        txtOrderID.setText(null);
        txtItemCode.setText(null);
        txtOrderQty.setText(null);
        txtDiscount.setText(null);
    }


}
