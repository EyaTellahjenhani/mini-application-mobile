package com.example.miniapplicationandroid.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miniapplicationandroid.R;
import com.example.miniapplicationandroid.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    private EditText email, firstName, lastName;
    private TextView fullName, emailTxtView;
    private Button button;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        initV();
        displayUserInfo();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyProfile();
            }
        });
    }

    void initV(){
        email=findViewById(R.id.emailEdit);
        firstName=findViewById(R.id.firstNameEdit);
        lastName=findViewById(R.id.lastNameEdit);
        fullName=findViewById(R.id.textView15);
        emailTxtView=findViewById(R.id.textView18);
        button=findViewById(R.id.button);

    }

    private void displayUserInfo()
    {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            DatabaseReference userRef = mDatabase.child(userId);

            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        User userData = snapshot.getValue(User.class);
                        if (userData != null) {
                            firstName.setText(userData.getFirstName());
                            lastName.setText(userData.getLastName());
                            email.setText(userData.getEmail());
                            emailTxtView.setText(userData.getEmail());
                            fullName.setText(userData.getFirstName()+ " " + userData.getLastName());

                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

    }

    private void modifyProfile() {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            String newFirstName = firstName.getText().toString().trim();
            String newLastName = lastName.getText().toString().trim();
            String newEmail = email.getText().toString().trim();


            user.updateEmail(newEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        String userId = user.getUid();
                        User updatedUser = new User(newFirstName, newLastName, newEmail);
                        mDatabase.child(userId).setValue(updatedUser);

                        Toast.makeText(ProfileActivity.this, "User information updated successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ProfileActivity.this, "Failed to update user information", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}