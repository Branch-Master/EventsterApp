package com.example.eventsterapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.eventsterapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.widget.TextView;

public class ViewEntity extends AppCompatActivity {

    private TextView mTextMessage;
    private TextView entityName;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_search:
                    mTextMessage.setText(R.string.title_search);
                    return true;
                case R.id.navigation_create_new:
                    mTextMessage.setText(R.string.title_create_new);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entity);

        mTextMessage = (TextView) findViewById(R.id.message);
        entityName = findViewById(R.id.entity_name);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        String nameFromIntent =getIntent().getStringExtra("ent_name");
        entityName.setText(nameFromIntent);
    }

}
