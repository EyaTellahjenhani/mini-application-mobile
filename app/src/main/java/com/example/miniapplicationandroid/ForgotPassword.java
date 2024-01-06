package com.example.miniapplicationandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class ForgotPassword extends AppCompatActivity {
 private Button forgotPasswordButton;
 private TextInputLayout forgotPasswordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        initView();
        buttonForgotPasswordClick();
    }

    private void initView(){
        forgotPasswordInput = findViewById(R.id.ForgotPassworfEmail);
        forgotPasswordButton = findViewById(R.id.ButtonForgotPassword);
    }

    private void buttonForgotPasswordClick(){
        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (forgotPasswordInput.getEditText().getText().toString().isEmpty()==true)
                {
                    forgotPasswordInput.setError("Email is require");
                }else {
                    forgotPasswordInput.setError(null);
                    Toast.makeText(ForgotPassword.this,"Password reset link successfully sent to your email",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(ForgotPassword.this, LoginPage.class);
                    startActivity(i);
                }
            }
        });
    }


}