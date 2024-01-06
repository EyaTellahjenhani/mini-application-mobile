package com.example.miniapplicationandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class SignUpPage extends AppCompatActivity {

    private TextInputLayout InputFirstName;
    private TextInputLayout InputLastName;
    private TextInputLayout InputEmailSignUp;
    private TextInputLayout InputPasswordSignUp;
    private Button ButtonSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        initView();
        buttonSignUpOnClick();

    }

    private void initView(){
        InputFirstName = findViewById(R.id.InputFirstName);
        InputLastName = findViewById(R.id.InputLastName);
        InputEmailSignUp = findViewById(R.id.InputEmailSignUp);
        InputPasswordSignUp = findViewById(R.id.InputPasswordSignUp);
        ButtonSignUp = findViewById(R.id.ButtonSignUp);
    }

    private void buttonSignUpOnClick(){
        ButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (InputFirstName.getEditText().getText().toString().isEmpty()==true)
                {
                    InputFirstName.setError("First Name is require");
                } else if (InputLastName.getEditText().getText().toString().isEmpty()==true) {
                    InputFirstName.setError(null);
                    InputLastName.setError("Last Name is require");
                } else if (InputEmailSignUp.getEditText().getText().toString().isEmpty()==true) {
                    InputLastName.setError(null);
                    InputEmailSignUp.setError("Email is require");
                } else if (InputPasswordSignUp.getEditText().getText().toString().length()<8) {
                    InputEmailSignUp.setError(null);
                    InputPasswordSignUp.setError("Email is require");
                }else {
                    InputPasswordSignUp.setError(null);
                    Intent i = new Intent(SignUpPage.this, LoginPage.class);
                    startActivity(i);
                }
            }
        });
    }

}