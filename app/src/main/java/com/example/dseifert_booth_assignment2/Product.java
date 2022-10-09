package com.example.dseifert_booth_assignment2;

public class Product {
    String name;
    int quantity;
    double price;

    public Product(String s, int q, double p) {
        name = s;
        quantity = q;
        price = p;
    }

    public boolean equals(String n) {
        if (name == n) return true;
        else return false;
    }

}
