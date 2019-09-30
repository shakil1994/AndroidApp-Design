package com.example.shakil.androidswipebottomnav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.shakil.androidswipebottomnav.Adapter.MyAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CoordinatorLayout root_layout;
    List<String> stringList = new ArrayList<>();
    RecyclerView recyclerView;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        root_layout = findViewById(R.id.rootLayout);
        recyclerView = findViewById(R.id.item_recycler);

        bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_message:
                        Snackbar.make(root_layout, "This appear above Bottom Navigation View", Snackbar.LENGTH_SHORT).show();
                        break;

                    case R.id.action_add:
                        Snackbar.make(root_layout, "This appear above Bottom Navigation View", Snackbar.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        getData();
    }

    private void getData() {
        for (int i = 0; i < 50; i++){
            stringList.add(new StringBuilder("This is item ").append(i + 1).toString());
        }

        MyAdapter adapter = new MyAdapter(this, stringList);
        recyclerView.setAdapter(adapter);
    }
}
