package com.example.shakil.androidprofilescreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;

public class MainActivity extends AppCompatActivity {

    AppBarLayout appBarLayout;
    Toolbar toolbar;

    int colorOffSet = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appBarLayout = findViewById(R.id.appbar);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("PROFILE");

        setSupportActionBar(toolbar);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                colorOffSet = -(i);
                if (colorOffSet > 255){
                    colorOffSet = 255;
                }
                //End Toolbar
                toolbar.getBackground().setAlpha(colorOffSet);
                toolbar.setAlpha(colorOffSet / 256f);
            }
        });
    }
}
