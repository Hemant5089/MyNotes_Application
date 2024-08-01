package com.example.mynotes.Activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mynotes.MainActivity;
import com.example.mynotes.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Immediately transition to the main activity
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
