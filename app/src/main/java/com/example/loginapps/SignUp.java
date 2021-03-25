package com.example.loginapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    TextInputLayout regEmail, regPassword;
    Button regBtn, regToLoginBtn;
    FirebaseAuth mFirebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        regEmail = findViewById(R.id.reg_email);
        regPassword = findViewById(R.id.reg_password1);
        regBtn = findViewById(R.id.reg_btn);
        regToLoginBtn = findViewById(R.id.reg_login_btn);
        mFirebaseAuth = FirebaseAuth.getInstance();


        regToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reg_email = regEmail.getEditText().getText().toString();
                String reg_password1 = regPassword.getEditText().getText().toString();

                if (reg_email.isEmpty()) {
                    regEmail.requestFocus();
                } else if (reg_password1.isEmpty()) {
                    regPassword.requestFocus();
                } else if (reg_email.isEmpty() && reg_password1.isEmpty()) {
                    Toast.makeText(SignUp.this, "Email and password are required", Toast.LENGTH_SHORT).show();
                } else if (!(reg_email.isEmpty() && reg_password1.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(reg_email, reg_password1).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(SignUp.this, "registration failed, try again", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(SignUp.this, Login.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(SignUp.this, "ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}