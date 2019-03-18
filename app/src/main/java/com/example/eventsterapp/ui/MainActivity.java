package com.example.eventsterapp.ui;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.eventsterapp.Adapters.RVAdapter;
import com.example.eventsterapp.R;
import com.example.eventsterapp.database.MockData;
import com.example.eventsterapp.models.Event;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RecyclerView eventView;

    private TextView mTextMessage;
    private ArrayList<Event> eventList = new ArrayList<Event>();

    ArrayAdapter adapter;

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
                    Intent i = new Intent(MainActivity.this, SearchActivity.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_create_new:
                    mTextMessage.setText(R.string.title_create_new);
                    return true;
                case R.id.navigation_menu:
                    mTextMessage.setText(R.string.title_menu);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mTextMessage.setText("Event List");

        eventView = findViewById(R.id.rv);


        loadData();

        eventView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        eventView.setLayoutManager(llm);

        RVAdapter adapter = new RVAdapter(eventList);
        eventView.setAdapter(adapter);


    }

    private void loadData(){

        MockData mockData = new MockData();

        this.eventList.addAll(mockData.getEvents());

    }

}
