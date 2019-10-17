package com.example.shakil.androidgridlayoutdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.muddzdev.styleabletoast.StyleableToast;

public class MainActivity extends AppCompatActivity {

    MaterialCardView txt_home, txt_time, txt_music, txt_airport, txt_location, txt_profile;

    LinearLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout = findViewById(R.id.rootLayout);

        txt_home = findViewById(R.id.txt_home);
        txt_time = findViewById(R.id.txt_time);
        txt_music = findViewById(R.id.txt_music);
        txt_airport = findViewById(R.id.txt_airport);
        txt_location = findViewById(R.id.txt_location);
        txt_profile = findViewById(R.id.txt_profile);

        txt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(rootLayout, "Home", Snackbar.LENGTH_SHORT).show();
            }
        });

        txt_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Snackbar.make(rootLayout, "Time", Snackbar.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "Time", Toast.LENGTH_SHORT).show();
            }
        });

        txt_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(rootLayout, "Music", Snackbar.LENGTH_SHORT).show();
            }
        });

        txt_airport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(rootLayout, "Airport", Snackbar.LENGTH_SHORT).show();
            }
        });

        txt_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(rootLayout, "Location", Snackbar.LENGTH_SHORT).show();
            }
        });

        txt_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                customToast();
            }
        });
    }

    private void customToast() {
        StyleableToast.makeText(this, "Profile", R.style.exampleToast).show();
    }
}
