package com.example.eventsterapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.eventsterapp.Fragments.CreateEventFragment;
import com.example.eventsterapp.Fragments.CreateGroupFragment;
import com.example.eventsterapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class AboutmeActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent i;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    System.out.println("Navigation Home");
                    i = new Intent(AboutmeActivity.this, HomeActivity.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_search:
                    System.out.println("Navigation Search");
                    return true;
                case R.id.navigation_create_new:
                    System.out.println("Navigation Create New");
                    i = new Intent(AboutmeActivity.this, CreateActivity.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_aboutme:
                    System.out.println("Navigation About me");
                    i = new Intent(AboutmeActivity.this, AboutmeActivity.class);
                    startActivity(i);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutme);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    }
}