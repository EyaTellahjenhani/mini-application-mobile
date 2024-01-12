package com.example.miniapplicationandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
 private Button forgotPasswordButton;
 private TextInputLayout forgotPasswordInput;
   private FirebaseAuth auth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        auth = FirebaseAuth.getInstance();
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
                String emailAddress = forgotPasswordInput.getEditText().getText().toString();
                if (forgotPasswordInput.getEditText().getText().toString().isEmpty()==true)
                {
                    forgotPasswordInput.setError("Email is require");
                }else {
                    forgotPasswordInput.setError(null);
                    auth.sendPasswordResetEmail(emailAddress).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(ForgotPassword.this,"Reset Password Successfully send to "+emailAddress,Toast.LENGTH_LONG).show();
                            startActivity(new Intent(ForgotPassword.this,LoginPage.class));
                        }
                    });
                }
            }
        });
    }


}