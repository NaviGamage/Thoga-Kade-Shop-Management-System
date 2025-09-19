package controller.customercontroller;

import javafx.collections.ObservableList;
import model.Customer;

public interface CustomerManagementService {

    ObservableList<Customer> getAllCustomers();

    void addCustomer(String custID , String custTitle , String custName , String DOB , double salary , String address , String city , String province , String postalcode);

    void deleteCustomer(String custID);

    void updateCustomer(String custID , String custTitle , String custName , String DOB , double salary , String address , String city , String province , String postalcode);


}