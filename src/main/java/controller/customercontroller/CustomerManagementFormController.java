package controller.customercontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerManagementFormController implements Initializable {

    ObservableList<Customer> customers = FXCollections.observableArrayList();

    CustomerManagementService customerManagementService = new CustomerManagementController();

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colCustID;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private ComboBox<String> comboTitle;

    @FXML
    private TableView<Customer> tblCustomers;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtCustID;

    @FXML
    private TextField txtDOB;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPostalCode;

    @FXML
    private TextField txtProvince;

    @FXML
    private TextField txtSalary;

    @FXML
    void btnAddOnAction(ActionEvent event) {

        String CustID = txtCustID.getText();
        String CustTitle = comboTitle.getValue();
        String CustName = txtName.getText();
        String DOB = txtDOB.getText();
        double Salary = Double.parseDouble(txtSalary.getText());
        String Address = txtAddress.getText();
        String City = txtCity.getText();
        String Province = txtProvince.getText();
        String Postalcode = txtPostalCode.getText();

        customerManagementService.addCustomer(CustID , CustTitle , CustName , DOB , Salary , Address , City , Province , Postalcode);

        loadCustomerDetails();

        clearData();

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

        clearData();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        customerManagementService.deleteCustomer(txtCustID.getText());

        loadCustomerDetails();

        clearData();

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String CustID = txtCustID.getText();
        String CustTitle = comboTitle.getValue();
        String CustName = txtName.getText();
        String DOB = txtDOB.getText();
        double Salary = Double.parseDouble(txtSalary.getText());
        String Address = txtAddress.getText();
        String City = txtCity.getText();
        String Province = txtProvince.getText();
        String Postalcode = txtPostalCode.getText();

        customerManagementService.updateCustomer(CustID , CustTitle , CustName , DOB , Salary , Address , City , Province , Postalcode);

        loadCustomerDetails();

        clearData();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList <String> custTitleTypes = FXCollections.observableArrayList(
                "Mr.",
                "Ms.",
                "Miss."
        );
        comboTitle.setItems(custTitleTypes);

        //--------------------------------------------------------------------------------------------------------------

        colCustID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("City"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("Province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));

        loadCustomerDetails();

        //--------------------------------------------------------------------------------------------------------------

        tblCustomers.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue != null){
                System.out.println(newValue);

                setSelectedValue(newValue);

            }
        });
    }

    private void loadCustomerDetails(){

        customers.clear();

        customers = customerManagementService.getAllCustomers();

        tblCustomers.setItems(customers);
    }

    private void clearData(){

        txtCustID.setText(null);
        comboTitle.getSelectionModel().clearSelection();
        txtName.setText(null);
        txtDOB.setText(null);
        txtSalary.setText(null);
        txtAddress.setText(null);
        txtCity.setText(null);
        txtProvince.setText(null);
        txtPostalCode.setText(null);
    }

    private void setSelectedValue(Customer selectedValues){

        txtCustID.setText(selectedValues.getID());
        comboTitle.setValue(selectedValues.getTitle());
        txtName.setText(selectedValues.getName());
        txtDOB.setText(selectedValues.getDOB());
        txtSalary.setText(String.valueOf(selectedValues.getSalary()));
        txtAddress.setText(selectedValues.getAddress());
        txtCity.setText(selectedValues.getCity());
        txtProvince.setText(selectedValues.getProvince());
        txtPostalCode.setText(selectedValues.getPostalCode());
    }


}




