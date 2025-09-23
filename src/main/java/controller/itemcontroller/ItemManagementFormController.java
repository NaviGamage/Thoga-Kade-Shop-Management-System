package controller.itemcontroller;

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
import model.Item;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemManagementFormController implements Initializable {

    ObservableList<Item> items = FXCollections.observableArrayList();

    ItemManagementService itemManagementService = new ItemManagementController();

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colDesc;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colPackSize;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView<Item> tblItems;

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtPackSize;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    void btnAddOnAction(ActionEvent event) {

        String ItemCode = txtItemCode.getText();
        String Desc = txtDesc.getText();
        String PackSize = txtPackSize.getText();
        double UnitPrize = Double.parseDouble(txtUnitPrice.getText());
        int Qty = Integer.parseInt(txtQty.getText());

        itemManagementService.addItems(ItemCode , Desc , PackSize , UnitPrize , Qty);

        loadItemDetails();

        clearData();

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

        clearData();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        itemManagementService.deleteItem(txtItemCode.getText());

        loadItemDetails();

        clearData();

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String ItemCode = txtItemCode.getText();
        String Desc = txtDesc.getText();
        String PackSize = txtPackSize.getText();
        double UnitPrize = Double.parseDouble(txtUnitPrice.getText());
        int Qty = Integer.parseInt(txtQty.getText());

        itemManagementService.updateItem(ItemCode , Desc , PackSize , UnitPrize , Qty);

        loadItemDetails();

        clearData();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("Desc"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("PackSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));

        loadItemDetails();

        tblItems.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue != null){
                System.out.println(newValue);

                setSelectedValue(newValue);

            }
        });
    }

    private void loadItemDetails(){

        items.clear();

        items = itemManagementService.getAllItems();

        tblItems.setItems(items);
    }

    private void setSelectedValue(Item selectedValues){
        txtItemCode.setText(selectedValues.getItemCode());
        txtDesc.setText(selectedValues.getDesc());
        txtPackSize.setText(selectedValues.getPackSize());
        txtUnitPrice.setText(String.valueOf(selectedValues.getUnitPrice()));
        txtQty.setText(String.valueOf(selectedValues.getQty()));
    }

    private void clearData(){
        txtItemCode.setText(null);
        txtDesc.setText(null);
        txtPackSize.setText(null);
        txtUnitPrice.setText(null);
        txtQty.setText(null);
    }
}
