package com.example.loginapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    TextInputLayout email, pass;
    Button callSignUp;
    Button loginButton;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        callSignUp = findViewById(R.id.signup_screen);
        loginButton = findViewById(R.id.login_btn);



        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(Login.this, "you have entered", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this, Home.class);
                    startActivity(i);
                }

                else {
                    Toast.makeText(Login.this, "Please enter first", Toast.LENGTH_SHORT).show();
                }
            }
        };
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = email.getEditText().getText().toString();
                String password = pass.getEditText().getText().toString();
                if (username.isEmpty()){
                    email.requestFocus();
                }
                else if (password.isEmpty()){
                    pass.requestFocus();
                }
                else if(username.isEmpty() && password.isEmpty()){
                    Toast.makeText(Login.this, "Can not be empty", Toast.LENGTH_SHORT).show();
                }
                else if (!(username.isEmpty() && password.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(Login.this, "Login error", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent i = new Intent(Login.this,Home.class);
                                startActivity(i);
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(Login.this, "there is an error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}