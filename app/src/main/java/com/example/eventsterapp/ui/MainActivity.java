package com.example.eventsterapp.ui;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.example.eventsterapp.Adapters.RVAdapter;
import com.example.eventsterapp.R;
import com.example.eventsterapp.database.MockData;
import com.example.eventsterapp.models.Event;
import com.example.eventsterapp.models.Group;
import com.example.eventsterapp.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RecyclerView eventView;

    private ArrayList<Event> eventList = new ArrayList<Event>();
    private ArrayList<Group> groupList = new ArrayList<Group>();
    private ArrayList<User> userList = new ArrayList<User>();

    RVAdapter adapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent i;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    System.out.println("Navigation Home");
                    return true;
                case R.id.navigation_search:
                    System.out.println("Navigation Search");
                    i = new Intent(MainActivity.this, SearchActivity.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_create_new:
                    System.out.println("Navigation Create New");
                    i = new Intent(MainActivity.this, CreateActivity.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_menu:
                    System.out.println("Navigation Menu");
                    return true;
            }
            return false;
        }
    };


    private TabLayout.OnTabSelectedListener mOnTabSelectedListener = new TabLayout.OnTabSelectedListener() {

        RVAdapter rvAdapter;

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            System.out.println("tab Selected: " + tab.getText() + "================================");

            switch( tab.getText().toString() ){
                case "Events":
                    this.rvAdapter = new RVAdapter(eventList,new Event());
                    eventView.setAdapter(this.rvAdapter);
                    break;
                case "Groups":
                    this.rvAdapter = new RVAdapter(groupList,new Group());
                    eventView.setAdapter(this.rvAdapter);
                    break;
                case "Users":
                    this.rvAdapter = new RVAdapter(userList,new User());
                    eventView.setAdapter(this.rvAdapter);
                    break;
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayoutMain);
        tabLayout.addOnTabSelectedListener(mOnTabSelectedListener);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);


        eventView = findViewById(R.id.rv);
        eventView.setNestedScrollingEnabled(false);



        eventView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        eventView.setLayoutManager(llm);

        RVAdapter adapter = new RVAdapter(eventList,new Event());
        eventView.setAdapter(adapter);


    }

    @Override
    public void onResume() {
       super.onResume();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_home);
    }

    private void loadData(){
        MockData mockData = new MockData();
        this.eventList.addAll(mockData.getEvents());
        this.groupList.addAll(mockData.getGroups());
        this.userList.addAll(mockData.getUsers());
    }

}
