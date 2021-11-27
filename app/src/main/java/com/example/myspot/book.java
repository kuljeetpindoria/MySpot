package com.example.myspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class book extends AppCompatActivity {

    TextInputLayout sName, mobileNo, carNo, duration;
    Button Submit , LogOut;

    public static final String EXTRA_NAME = "com.example.parkingapp.extra.NAME";
    public static final String EXTRA_DURATION = "com.example.parkingapp.extra.DURATION";
    public static final String EXTRA_CARNO = "com.example.parkingapp.extra.CARNO";

    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        sName =  findViewById(R.id.sName);
        mobileNo = findViewById(R.id.mobileNo);
        carNo = findViewById(R.id.carNo);
        duration = findViewById(R.id.duration);
        Submit = findViewById(R.id.Submit);
        LogOut = findViewById(R.id.LogOut);

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.sName, RegexTemplate.NOT_EMPTY, R.string.invalid_name);

        awesomeValidation.addValidation(this, R.id.duration, "[0-9]$", R.string.invalid_Duaration);

        awesomeValidation.addValidation(this, R.id.mobileNo, "^[0-9]{2}[0-9]{8}$", R.string.invalid_Mobile_Number);

        awesomeValidation.addValidation(this, R.id.carNo, "^[A-Z]{2}\\s[0-9]{2}\\s[A-Z]{2}\\s[0-9]{4}$", R.string.invalid_Car_Number);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate()){
                    String name = sName.getEditText().toString();
                    String car = carNo.getEditText().toString();
                    String time = duration.getEditText().toString();
                    Intent intent = new Intent(book.this,Spot.class);
                    intent.putExtra("NAME", name);
                    intent.putExtra("CAR", car);
                    intent.putExtra("TIME", time);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Input Not Valid",Toast.LENGTH_SHORT).show();
                }
            }
        });





    }
}