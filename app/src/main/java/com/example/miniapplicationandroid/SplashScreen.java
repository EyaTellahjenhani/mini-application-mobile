package com.example.miniapplicationandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);


        ImageView logoView = findViewById(R.id.logoImageView);

        // Create an alpha animation for the logo
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setInterpolator(new DecelerateInterpolator());

        // Start the animation on the logo
        logoView.startAnimation(alphaAnimation);

        // Start the second activity after the animation is complete
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Do nothing
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashScreen.this, LoginPage.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // Do nothing
            }
        });



    }
}