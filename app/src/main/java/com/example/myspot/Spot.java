package com.example.myspot;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class Spot extends AppCompatActivity implements PaymentResultListener {
    Button pay;
    TextView textView5, textView, textView2, textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot);
        
        pay = findViewById(R.id.pay);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        textView.setText(getIntent().getStringExtra("NAME"));
        textView2.setText(getIntent().getStringExtra("CAR"));
        textView3.setText(getIntent().getStringExtra("TIME"));

        String total = "100";

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checkout checkout = new Checkout();

                checkout.setKeyID("rzp_test_kbgnaTnAYnN70S");

                checkout.setImage(R.drawable.rzp_logo);

                JSONObject object = new JSONObject();

                try {
                    object.put("name","Parking App");

                    object.put("description","Test Payment");

                    object.put("theme.color", "#0093DD");

                    object.put("amount",total);

                    object.put("Prefilled Number","98765434210");

                    object.put("prefilled email ","parkingapp@gmail.com");


                    checkout.open(Spot.this,object);

                } catch (JSONException  e) {
                    e.printStackTrace();
                }


            }
        });

    }
    @Override
    public void onPaymentSuccess(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Payment ID");

        builder.setMessage(s);

        builder.show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();

    }

}