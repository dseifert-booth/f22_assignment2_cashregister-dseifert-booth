package com.example.dseifert_booth_assignment2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Product> prodList = new ArrayList<Product>(3) {
        {
            add(new Product("Pants", 10, 20.44));
            add(new Product("Shoes", 100, 10.44));
            add(new Product("Hats", 30, 5.9));
        }
    };
    ArrayList<Purchase> purchList = new ArrayList<Purchase>(0);
    TextView prodType;
    private TextView quantity;
    private TextView totCost;
    private NumberPicker picker;
    private ListView dispList;

    DecimalFormat priceFormat = new DecimalFormat("#.00");
    double price = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        prodType = findViewById(R.id.productType);
        quantity = findViewById(R.id.quantity);
        totCost = findViewById(R.id.totalCost);
        picker = findViewById(R.id.numSlider);

        picker.setMinValue(0);
        picker.setMaxValue(100);

        quantity.setText(String.valueOf(picker.getValue()));

        dispList = findViewById(R.id.productList);

        ProductBaseAdapter productBaseAdapter = new ProductBaseAdapter(this, prodList);
        dispList.setAdapter(productBaseAdapter);

        dispList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                prodType.setText(prodList.get(i).name);
                price = prodList.get(i).price;
                totCost.setText(String.valueOf(Math.round((picker.getValue() * price) * 100.0) / 100.0));
            }
        });

        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                quantity.setText(String.valueOf(picker.getValue()));
                if (price == 0) {
                    totCost.setText("0");
                } else {
                    totCost.setText(String.valueOf(Math.round((picker.getValue() * price) * 100.0) / 100.0));;
                }
            }
        });
    }

    public void purchase(View view) {
        int i = 0;
        Product purchasedProduct = new Product("", 0, 0);

        for (Product p : prodList) {
            if (p.equals((String) prodType.getText())) {
                i = prodList.indexOf(p);
                purchasedProduct = p;
            }
        }

        if (picker.getValue() == 0 || price == 0) {
            Toast.makeText(MainActivity.this, "All fields are required!", Toast.LENGTH_LONG).show();
        } else {
            if (picker.getValue() > purchasedProduct.quantity) {
                Toast.makeText(MainActivity.this, "Not enough quantity in stock!", Toast.LENGTH_LONG).show();
            } else {
                purchList.ensureCapacity(purchList.size() + 1);
                purchList.add(new Purchase(purchasedProduct.name, picker.getValue(), Double.parseDouble((String) totCost.getText())));

                purchasedProduct.quantity -= picker.getValue();
                prodList.set(i, purchasedProduct);

                ProductBaseAdapter productBaseAdapter = new ProductBaseAdapter(this, prodList);
                dispList.setAdapter(productBaseAdapter);
            }
        }
    }

}
