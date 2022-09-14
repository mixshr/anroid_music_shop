package com.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    String subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent receivedOrderIntent = getIntent();
        String userName = receivedOrderIntent.getStringExtra("userNameForIntent");
        String goodsName = receivedOrderIntent.getStringExtra("goodsName");
        int quantity = receivedOrderIntent.getIntExtra("quantity", 0);
        double orderPrice = receivedOrderIntent.getDoubleExtra("orderPrice", 0.0);
        double price = receivedOrderIntent.getDoubleExtra("price", 0.0);

        TextView orderTextView = findViewById(R.id.orderTextView);
        orderTextView.setText("User name: " + userName);

        TextView goodsNameTextView = findViewById(R.id.goodsNameTextview);
        goodsNameTextView.setText("Good: " +goodsName);

        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView.setText("Quantity: " +String.valueOf(quantity));

        TextView orderPriceTextView = findViewById(R.id.orderPriceTextView);
        orderPriceTextView.setText("Order price: " +String.valueOf(orderPrice));

        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("Price: " +String.valueOf(price));

        Button button = findViewById(R.id.submitOrder);
        button.setOnClickListener(this::submitOrderOnClick);

        subject = "User name: " + userName + "\n" + "Good: " +goodsName + "\n" + "Quantity: " +String.valueOf(quantity) + "\n" + "Order price: " +String.valueOf(orderPrice) + "\n" + "Price: " +String.valueOf(price);

        setTitle("New order");
    }

    void submitOrderOnClick(View view) {
        String[] addresses = new String[]{"mixail.sharenko@gmail.com"};
        composeEmail(addresses, subject);
    }

    void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        startActivity(intent);
    }
}