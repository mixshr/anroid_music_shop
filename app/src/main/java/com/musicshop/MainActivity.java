package com.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView quantityTextView;
    short quantity = 0;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;

    HashMap  goodsMap;

    String goodsName;
    double price;

    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createSpinner();

        quantityTextView = (TextView) findViewById(R.id.quantityTextView);

        Button plusButton = (Button) findViewById(R.id.plusButton);
        plusButton.setOnClickListener(this::plusButtonOnClick);

        Button minusButton = (Button) findViewById(R.id.minusButton);
        minusButton.setOnClickListener(this::minusButtonOnClick);

        Button addToCard = findViewById(R.id.addToCard);
        addToCard.setOnClickListener(this::addToCardOnClick);

        userNameEditText = findViewById(R.id.nameEditText);
    }

    void createSpinner() {

        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("guitar");
        spinnerArrayList.add("drums");
        spinnerArrayList.add("keyboard");

        goodsMap = new HashMap();
        goodsMap.put("guitar", 500.0);
        goodsMap.put("drums", 700.0);
        goodsMap.put("keyboard", 1000.0);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinnerAdapter);

    }

    private void minusButtonOnClick(View view) {
        quantity--;
        if (quantity<0) {
            quantity = 0;
        }
        quantityTextView.setText(String.valueOf(quantity));
        TextView priceTextView = findViewById(R.id.priceTextView);
        double result = price * quantity;
        priceTextView.setText(String.valueOf(result));
    }

    private void plusButtonOnClick(View view) {
        quantity++;
        quantityTextView.setText(String.valueOf(quantity));
        TextView priceTextView = findViewById(R.id.priceTextView);
        double result = price * quantity;
        priceTextView.setText(String.valueOf(result));
    }

    private void addToCardOnClick(View view) {
        Order order = new Order();
        order.userName = userNameEditText.getText().toString();
        order.goodsName = goodsName;
        order.quantity = quantity;
        order.orderPrice = quantity * price;
        order.price = price;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userNameForIntent", order.userName);
        orderIntent.putExtra("goodsName", order.goodsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("orderPrice", order.orderPrice);
        orderIntent.putExtra("price", order.price);

        startActivity(orderIntent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double) goodsMap.get(goodsName);
        TextView priceTextView = findViewById(R.id.priceTextView);
        double result = price * quantity;
        priceTextView.setText(String.valueOf(result));

        ImageView goodsImageView = findViewById(R.id.goodsImageView);

        switch (goodsName) {
            case "guitar": goodsImageView.setImageResource(R.drawable.musici_tem_1); break;
            case "drums": goodsImageView.setImageResource(R.drawable.music_item_2); break;
            case "keyboard": goodsImageView.setImageResource(R.drawable.music_item_3); break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}