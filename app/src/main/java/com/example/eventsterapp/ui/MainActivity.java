package com.example.eventsterapp.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.example.eventsterapp.Adapters.RVAdapter;
import com.example.eventsterapp.R;
import com.example.eventsterapp.database.DatabaseHelper;
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

    private RVAdapter eventAdapter;
    private RVAdapter groupAdapter;
    private RVAdapter userAdapter;
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
        setContentView(R.layout.activity_main);

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

        RVAdapter adapter = new RVAdapter(eventList,new Event());
        eventView.setAdapter(adapter);


    }

    @Override
    public void onResume() {
       super.onResume();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_home);
    }

    private void loadData() {
        mDatebaseHelper.dropAllTables();

        MockData mockData = new MockData();
        //this.eventList.addAll(mockData.getEvents());
        //this.groupList.addAll(mockData.getGroups());
        //this.userList.addAll(mockData.getUsers());




        for(User u : mockData.getUsers()){
            mDatebaseHelper.addUser(u);
        }
        for(Event e: mockData.getEvents()){
            mDatebaseHelper.addEvent(e);
        }
        for(Group g: mockData.getGroups()){
            mDatebaseHelper.addGroup(g);
        }


        Cursor allusers = mDatebaseHelper.getAllUsers();
        while(allusers.moveToNext()){
            String name = allusers.getString(1);
            String pass = allusers.getString(2);
            String email = allusers.getString(3);
            String bday = allusers.getString(4);
            String zodiac = allusers.getString(5);
            String phone = allusers.getString(6);

            this.userList.add(new User(new Long(13),name,pass,bday,phone,zodiac,email,false ));
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

            this.eventList.add(new Event(new Long(13),eventName, eventInfo, groupID,tag,startDate,endDate,location,eventSeats,vis));
        }

        Cursor allgroups = mDatebaseHelper.getAllGroups();
        while(allgroups.moveToNext()){
            String groupname = allgroups.getString(1);
            String info = allgroups.getString(2);
            int vis = allgroups.getInt(3);

            this.groupList.add(new Group( groupname, info,new Long(13) ,vis));
        }

        eventAdapter = new RVAdapter(eventList,new Event());
        groupAdapter = new RVAdapter(groupList,new Group());
        userAdapter = new RVAdapter(userList,new User());

    }

    private void addUser(User newEntry){


        boolean insertData = mDatebaseHelper.addUser(newEntry);

        if(insertData){
            System.out.println("data sucessfully added=======");
        }
        else{
            System.out.println("data not added============");
        }
    }


    }