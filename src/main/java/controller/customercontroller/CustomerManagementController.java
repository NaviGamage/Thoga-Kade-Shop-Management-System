package controller.customercontroller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.*;

public class CustomerManagementController implements CustomerManagementService {

    @Override
    public ObservableList<Customer> getAllCustomers() {

        ObservableList <Customer> customers = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            //DriverManager.getConnection("jdbc:mysql://localhost/thogakade","root","1234");
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM customer");
            ResultSet rst = pstm.executeQuery();

            while (rst.next()){
                customers.add(new Customer(
                        rst.getString("CustID"),
                        rst.getString("CustTitle"),
                        rst.getString("CustName"),
                        rst.getString("DOB"),
                        rst.getDouble("Salary"),
                        rst.getString("CustAddress"),
                        rst.getString("City"),
                        rst.getString("Province"),
                        rst.getString("PostalCode")

                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    @Override
    public void addCustomer(String custID , String custTitle , String custName , String DOB , double salary , String address , String city , String province , String postalcode) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO customer VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ?);");
            pstm.setObject(1,custID);
            pstm.setObject(2,custTitle);
            pstm.setObject(3,custName);
            pstm.setObject(4,DOB);
            pstm.setObject(5,salary);
            pstm.setObject(6,address);
            pstm.setObject(7,city);
            pstm.setObject(8,province);
            pstm.setObject(9,postalcode);

            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteCustomer(String custID) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM customer WHERE CustID = ? ");
            pstm.setObject(1,custID);

            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCustomer(String custID, String custTitle, String custName, String DOB, double salary, String address, String city, String province, String postalcode) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE customer SET CustID = ? , CustTitle = ? , CustName = ? , DOB = ? , Salary = ? , CustAddress = ? , City = ? , Province = ? , PostalCode = ? WHERE CustID = ? ");
            pstm.setObject(1,custID);
            pstm.setObject(2,custTitle);
            pstm.setObject(3,custName);
            pstm.setObject(4,DOB);
            pstm.setObject(5,salary);
            pstm.setObject(6,address);
            pstm.setObject(7,city);
            pstm.setObject(8,province);
            pstm.setObject(9,postalcode);
            pstm.setObject(10,custID);

            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

