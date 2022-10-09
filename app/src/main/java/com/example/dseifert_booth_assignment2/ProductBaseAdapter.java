package com.example.dseifert_booth_assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductBaseAdapter extends BaseAdapter {
    Context context;
    ArrayList<Product> listOfProducts;

    ProductBaseAdapter(Context c, ArrayList<Product> list) {
        context = c;
        listOfProducts = list;
    }

    @Override
    public int getCount() {
        return listOfProducts.size();
    }

    @Override
    public Object getItem(int i) {
        return listOfProducts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.productlist_row_layout, null);
        TextView productName = view.findViewById(R.id.product_name);
        TextView productQuantity = view.findViewById(R.id.product_quantity);
        TextView productPrice = view.findViewById(R.id.product_price);

        productName.setText(listOfProducts.get(i).name);
        productQuantity.setText(String.valueOf(listOfProducts.get(i).quantity));
        productPrice.setText(String.valueOf(listOfProducts.get(i).price));

        return view;
    }
}
