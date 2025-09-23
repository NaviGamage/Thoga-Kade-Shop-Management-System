package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Item {
    private String ItemCode;
    private String Desc;
    private String PackSize;
    private double UnitPrice;
    private int Qty;
}
