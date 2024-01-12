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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpPage extends AppCompatActivity {

    private TextInputLayout InputFirstName;
    private TextInputLayout InputLastName;
    private TextInputLayout InputEmailSignUp;
    private TextInputLayout InputPasswordSignUp;
    private Button ButtonSignUp;
    private DatabaseReference mDatabase;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
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

                String email = InputEmailSignUp.getEditText().getText().toString();
                String password = InputPasswordSignUp.getEditText().getText().toString();
                String firstName = InputFirstName.getEditText().getText().toString();
                String lastName = InputLastName.getEditText().getText().toString();

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
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUpPage.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                                .setDisplayName(InputFirstName.getEditText().getText().toString() + " " + InputLastName.getEditText().getText().toString())
                                                .build();
                                        user.updateProfile(profileUpdates);
                                        String userId = mAuth.getCurrentUser().getUid();
                                        saveUserToDatabase(userId, firstName, lastName, email);
                                        Toast.makeText(SignUpPage.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SignUpPage.this, LoginPage.class));

                                    } else {
                                        Toast.makeText(SignUpPage.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    private void saveUserToDatabase(String userId, String firstName, String lastName, String email) {
        User user = new User(firstName, lastName, email);
        mDatabase.child(userId).setValue(user);
    }

}