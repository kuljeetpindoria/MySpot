package com.example.myspot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    Button signUp;
    Button callLogin;
    TextInputLayout mName, mUsername, mEmail, mPhoneNo, mPassword;

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mName = findViewById(R.id.name);

        mEmail = findViewById(R.id.email);
        mPhoneNo = findViewById(R.id.phoneNo);
        mPassword = findViewById(R.id.password);
        signUp = findViewById(R.id.signUp);
        callLogin = findViewById(R.id.login_screen);
        fAuth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =  mEmail.getEditText().getText().toString().trim();
                String password = mPassword.getEditText().toString().trim();
                String phoneNo = mPhoneNo.getEditText().toString().trim();
                String Name = mName.getEditText().toString().trim();



                if(Name.isEmpty()){
                    mName.setError("Name required");
                    return;
                }

                if(password.isEmpty()){
                    mPassword.setError("Password required");
                    return;
                }
                if(email.isEmpty()){
                    mEmail.setError("Email required");
                    return;
                }
                if(phoneNo.isEmpty()){
                    mPhoneNo.setError("Phone Number required");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Intent intent3 = new Intent(SignUp.this,book.class);
                        startActivity(intent3);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                

            }
        });

        callLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(SignUp.this,Login.class);
                startActivity(intent4);
            }
        });
    }
}