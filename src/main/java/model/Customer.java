package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {

    private String ID;
    private String Title;
    private String Name;
    private String DOB;
    private double Salary;
    private String Address;
    private String City;
    private String Province;
    private String PostalCode;

}