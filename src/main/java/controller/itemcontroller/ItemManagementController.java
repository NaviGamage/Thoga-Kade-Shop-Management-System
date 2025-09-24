package controller.itemcontroller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemManagementController implements ItemManagementService{
    @Override
    public ObservableList<Item> getAllItems() {

        ObservableList <Item> items = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM item");
            ResultSet rst = pstm.executeQuery();

            while (rst.next()){
                items.add(new Item(
                        rst.getString("ItemCode"),
                        rst.getString("Description"),
                        rst.getString("PackSize"),
                        rst.getDouble("UnitPrice"),
                        rst.getInt("QtyOnHand")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    @Override
    public void addItems(String itemCode, String desc, String packSize, double unitPrize, int qty) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO item VALUES (? , ? , ? , ? , ? );");
            pstm.setObject(1,itemCode);
            pstm.setObject(2,desc);
            pstm.setObject(3,packSize);
            pstm.setObject(4,unitPrize);
            pstm.setObject(5,qty);

            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteItem(String itemCode) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM item WHERE ItemCode = ?");
            pstm.setObject(1,itemCode);

            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateItem(String itemCode, String desc, String packSize, double unitPrize, int qty) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE item SET ItemCode = ? , Description = ? , PackSize = ? , UnitPrice = ? , QtyOnHand = ? WHERE ItemCode = ?");
            pstm.setObject(1,itemCode);
            pstm.setObject(2,desc);
            pstm.setObject(3,packSize);
            pstm.setObject(4,unitPrize);
            pstm.setObject(5,qty);
            pstm.setObject(6,itemCode);

            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

