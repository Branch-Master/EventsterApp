package com.example.eventsterapp.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.eventsterapp.Adapters.RVAdapter;
import com.example.eventsterapp.Adapters.RVEventCardAdapter;
import com.example.eventsterapp.Adapters.RVGroupCardAdapter;
import com.example.eventsterapp.Adapters.RVUserCardAdapter;
import com.example.eventsterapp.DrawerActivity;
import com.example.eventsterapp.R;
import com.example.eventsterapp.database.DatabaseHelper;
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



public class HomeActivity extends AppCompatActivity {

    RecyclerView eventView;

    private ArrayList<Event> eventList = new ArrayList<Event>();
    private ArrayList<Group> groupList = new ArrayList<Group>();
    private ArrayList<User> userList = new ArrayList<User>();

    private RVEventCardAdapter eventAdapter;
    private RVGroupCardAdapter groupAdapter;
    private RVUserCardAdapter userAdapter;
    private DatabaseHelper mDatebaseHelper;


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
                    i = new Intent(HomeActivity.this, SearchActivity.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_create_new:
                    System.out.println("Navigation Create New");
                    i = new Intent(HomeActivity.this, CreateActivity.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_menu:
                    System.out.println("Navigation Menu");
                    i = new Intent(HomeActivity.this, DrawerActivity.class);
                    startActivity(i);
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

            switch( tab.getPosition() ){
                case 0:

                    eventView.setAdapter(eventAdapter);
                    break;
                case 1:
                   // groupAdapter = new RVAdapter(groupList,new Group());
                    eventView.setAdapter(groupAdapter);
                    break;
                case 2:
                    //userAdapter = new RVAdapter(userList,new User());
                    eventView.setAdapter(userAdapter);
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
        setContentView(R.layout.activity_home);

        mDatebaseHelper = new DatabaseHelper(this);
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

        RVEventCardAdapter adapter = new RVEventCardAdapter(eventList);
        eventView.setAdapter(adapter);


    }

    @Override
    public void onResume() {
       super.onResume();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_home);

        loadData();


    }

    private void loadData() {
       // mDatebaseHelper.dropAllData();


       // mDatebaseHelper.addUser(new User(new Long(1), "Marinó","1234","22/05","8459701","Tvíburi","mak78@hi.is",false ));
       // mDatebaseHelper.addEvent(new Event("Tónleikar","Tónleikar hjá Ed Sheeran",1, "tónleikar","01/05","02/05","laugardalshöll",500,1));

        this.eventList = new ArrayList<Event>();
        this.groupList = new ArrayList<Group>();
        this.userList = new ArrayList<User>();


        Cursor allusers = mDatebaseHelper.getAllUsers();
        while(allusers.moveToNext()){
            String name = allusers.getString(1);
            String pass = allusers.getString(2);
            String email = allusers.getString(3);

            this.userList.add(new User(name,pass,email));
        }

        Cursor allevents = mDatebaseHelper.getAllEvents();
        while(allevents.moveToNext()){
            String eventName = allevents.getString(1);
            String eventInfo = allevents.getString(2);
            int groupID = allevents.getInt(3);
            String tag = allevents.getString(4);
            String startDate = allevents.getString(5);
            String endDate = allevents.getString(6);
            String location = allevents.getString(7);
            int eventSeats = allevents.getInt(8);
            int vis = allevents.getInt(9);

            this.eventList.add(new Event(eventName, eventInfo, groupID,tag,startDate,endDate,location,eventSeats,vis));
        }

        Cursor allgroups = mDatebaseHelper.getAllGroups();
        while(allgroups.moveToNext()){
            String groupname = allgroups.getString(1);
            String info = allgroups.getString(2);
            int vis = allgroups.getInt(3);

            this.groupList.add(new Group( groupname, info ,vis));
        }

        eventAdapter = new RVEventCardAdapter(eventList);
        groupAdapter = new RVGroupCardAdapter(groupList);
        userAdapter = new RVUserCardAdapter(userList);

    }


    }