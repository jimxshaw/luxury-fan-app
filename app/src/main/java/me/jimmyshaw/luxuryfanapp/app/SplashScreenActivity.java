package me.jimmyshaw.luxuryfanapp.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Duration of splash screen in milliseconds.
        final int DISPLAY_LENGTH = 2000;

        // The handler will start a new activity and then close the splash screen after
        // a set duration.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, ModelActivity.class);
                startActivity(intent);
                finish();
            }
        }, DISPLAY_LENGTH);

    }
}
