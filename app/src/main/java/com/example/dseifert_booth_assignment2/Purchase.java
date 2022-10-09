package com.example.dseifert_booth_assignment2;

import java.util.Date;

public class Purchase {
    String name;
    int quantity;
    double charge;
    Date purchaseDate;

    Purchase(String s, int q, double c) {
        name = s;
        quantity = q;
        charge = c;
        purchaseDate = new java.util.Date();
    }
}
