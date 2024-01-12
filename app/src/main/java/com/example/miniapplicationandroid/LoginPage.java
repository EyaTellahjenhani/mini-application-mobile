package com.example.miniapplicationandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miniapplicationandroid.Activity.HomePage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class LoginPage extends AppCompatActivity {

    private TextInputLayout InputEmail;
    private TextInputLayout InputPassword;
    private Button ButtonLogin;
    private TextView SignUpLink;
    private TextView Forgot_password_link;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        initView();
        buttonLoginClick();
        signeUpClick();
        forgotPasswordOnClick();

    }

    private void initView(){
        InputEmail = findViewById(R.id.InputEmail);
        InputPassword = findViewById(R.id.InputPassword);
        ButtonLogin = findViewById(R.id.ButtonLogin);
        SignUpLink = findViewById(R.id.SignUpLink);
        Forgot_password_link = findViewById(R.id.Forgot_password_link);

    }

    private void buttonLoginClick(){
        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (InputEmail.getEditText().getText().toString().isEmpty()==true)
                {
                    InputEmail.setError("Email is require");
                } else if (InputPassword.getEditText().getText().toString().isEmpty()==true) {
                    InputEmail.setError(null);
                    InputPassword.setError("Password is require");
                } else if (InputPassword.getEditText().getText().toString().length()<8) {
                    InputEmail.setError(null);
                    InputPassword.setError("Password must be at lease 8 character");
                }else {
                    InputPassword.setError(null);
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    auth.signInWithEmailAndPassword(InputEmail.getEditText().getText().toString(), InputPassword.getEditText().getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(LoginPage.this,HomePage.class));
                                    } else {
                                        // Sign in failed
                                        Toast.makeText(LoginPage.this,"Email or password are invalid",Toast.LENGTH_LONG).show();

                                    }
                                }
                });
                }
            }
        });
    }

    private void signeUpClick(){
        SignUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginPage.this,SignUpPage.class);
                startActivity(i);

            }
        });
    }


    private void forgotPasswordOnClick(){
        Forgot_password_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginPage.this,ForgotPassword.class);
                startActivity(i);

            }
        });
    }



}