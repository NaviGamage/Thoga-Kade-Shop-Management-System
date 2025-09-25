package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class OrderDetail {

    private String OrderID;
    private String ItemCode;
    private int Qty;
    private String Discount;

}
