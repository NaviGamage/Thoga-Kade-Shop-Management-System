package controller.ordercontroller;

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
import model.Order;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderManagementFormController implements Initializable {

    ObservableList<Order> order = FXCollections.observableArrayList();

    OrderManagementService orderManagementService = new OrderManagementController();

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colCustID;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colOrderID;

    @FXML
    private TableView<Order> tblOrder;

    @FXML
    private TextField txtCustID;

    @FXML
    private TextField txtOrderDate;

    @FXML
    private TextField txtOrderID;

    @FXML
    void btnAddOnAction(ActionEvent event) {

        String OrderID = txtOrderID.getText();
        String OrderDate = txtOrderDate.getText();
        String CustID = txtCustID.getText();

        orderManagementService.addOrders(OrderID , OrderDate , CustID);

        loadeAllOrders();

        clearData();

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

        clearData();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        orderManagementService.deleteOrder(txtOrderID.getText());

        loadeAllOrders();

        clearData();


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String OrderID = txtOrderID.getText();
        String OrderDate = txtOrderDate.getText();
        String CustID = txtCustID.getText();

        orderManagementService.updateOrder(OrderID , OrderDate , CustID);

        loadeAllOrders();

        clearData();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCustID.setCellValueFactory(new PropertyValueFactory<>("CustID"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colOrderID.setCellValueFactory(new PropertyValueFactory<>("ID"));

        loadeAllOrders();

        tblOrder.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue != null){
                System.out.println(newValue);

                setSelectedValue(newValue);

            }
        });
    }

    private void loadeAllOrders(){

        order.clear();

        order = orderManagementService.getAllOrders();

        tblOrder.setItems(order);
    }

    private void setSelectedValue(Order selectedValues){
        txtOrderID.setText(selectedValues.getID());
        txtOrderDate.setText(selectedValues.getDate());
        txtCustID.setText(selectedValues.getCustID());
    }

    private void clearData(){
        txtOrderID.setText(null);
        txtOrderDate.setText(null);
        txtCustID. setText(null);
    }
}
