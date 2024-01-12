package com.example.miniapplicationandroid.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.miniapplicationandroid.Adapter.PupolarAdapter;
import com.example.miniapplicationandroid.Domain.PopularDomain;
import com.example.miniapplicationandroid.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    private RecyclerView.Adapter adapterPupolar;
    private RecyclerView recyclerViewPupolar;
    private FirebaseUser user;
    private TextView nameUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        user= FirebaseAuth.getInstance().getCurrentUser();
        nameUser= findViewById(R.id.textView);
        nameUser.setText("Hi "+user.getDisplayName());
        initRecyclerView();
        bottomNavigation();
    }

    private void bottomNavigation() {
        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        LinearLayout cartBtn=findViewById(R.id.cartBtn);
        LinearLayout profilBtn=findViewById(R.id.profilBtn);

        homeBtn.setOnClickListener(v -> startActivity(new Intent(HomePage.this,HomePage.class)));

        cartBtn.setOnClickListener(v -> startActivity(new Intent(HomePage.this, CartActivity.class)));

        profilBtn.setOnClickListener(v -> startActivity(new Intent(HomePage.this, ProfileActivity.class)));
    }

    private void initRecyclerView() {
        ArrayList<PopularDomain> item = new ArrayList<>();
        item.add(new PopularDomain("T-shirt black", "You can't go wrong when you pair blue jeans with a black t-shirt.\n" +
                "You can feel confident in a pair of thin jeans, distressed denim, sweatpants, and any other type of jeans you want.\n" +
                "For a more sporty vibe, throw on a bold belt and some white sneakers.\n" +
                "When going out, though, you should wear heels.","pic1",15,4,500));
        item.add(new PopularDomain("Smart watch", "You can't go wrong when you pair blue jeans with a black t-shirt.\n" +
                "You can feel confident in a pair of thin jeans, distressed denim, sweatpants, and any other type of jeans you want.\n" +
                "For a more sporty vibe, throw on a bold belt and some white sneakers.\n" +
                "When going out, though, you should wear heels.","pic2",10,4.5,450));
        item.add(new PopularDomain("Iphone 14", "You can't go wrong when you pair blue jeans with a black t-shirt.\n" +
                "You can feel confident in a pair of thin jeans, distressed denim, sweatpants, and any other type of jeans you want.\n" +
                "For a more sporty vibe, throw on a bold belt and some white sneakers.\n" +
                "When going out, though, you should wear heels.","pic3",15,4.3,800));
        item.add(new PopularDomain("VisionX Pro LEO TV", "You can't go wrong when you pair blue jeans with a black t-shirt.\n" +
                "You can feel confident in a pair of thin jeans, distressed denim, sweatpants, and any other type of jeans you want.\n" +
                "For a more sporty vibe, throw on a bold belt and some white sneakers.\n" +
                "When going out, though, you should wear heels.","pic4",18,4.8,1500));

        recyclerViewPupolar = findViewById(R.id.View1);
        recyclerViewPupolar.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        adapterPupolar = new PupolarAdapter(item);
        recyclerViewPupolar.setAdapter(adapterPupolar);
    }
}