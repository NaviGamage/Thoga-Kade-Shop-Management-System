package controller.itemcontroller;

import javafx.collections.ObservableList;
import model.Item;

public interface ItemManagementService {
    ObservableList<Item> getAllItems();

    void addItems (String itemCode, String desc, String packSize, double unitPrize, int qty);

    void deleteItem(String itemCode);

    void updateItem(String itemCode, String desc, String packSize, double unitPrize, int qty);
}
