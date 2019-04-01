package com.example.eventsterapp.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.eventsterapp.Adapters.MyExpandableListAdapter;
import com.example.eventsterapp.R;
import com.example.eventsterapp.database.DatabaseHelper;
import com.example.eventsterapp.models.ChildRow;
import com.example.eventsterapp.models.ParentRow;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener,SearchView.OnCloseListener {


    private SearchManager searchManager;
    private SearchView searchView;
    private MyExpandableListAdapter listAdapter;
    private ExpandableListView myList;
    private ArrayList<ParentRow> parentList;
    private ArrayList<ParentRow> showThisParentList;
    private MenuItem searchItem;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent i;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    System.out.println("Navigation Home");
                    i = new Intent(SearchActivity.this, MainActivity.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_search:
                    System.out.println("Navigation Search");
                    return true;
                case R.id.navigation_create_new:
                    System.out.println("Navigation Create New");
                    i = new Intent(SearchActivity.this, CreateActivity.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_menu:
                    System.out.println("Navigation Menu");
                    i = new Intent(SearchActivity.this, MainActivity.class);
                    startActivity(i);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_search);

        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        parentList = new ArrayList<ParentRow>();
        showThisParentList = new ArrayList<ParentRow>();

        displayList();

        //expandAll();
    }

    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for(int i =0; i < count; i++){
             myList.expandGroup(i);
        }
    }
    private void displayList(){
        loadData();

        myList = (ExpandableListView) findViewById(R.id.expandambleListView_search);
        listAdapter = new MyExpandableListAdapter(SearchActivity.this,parentList);
        myList.setAdapter(listAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        searchView.requestFocus();

        return true;
    }


    @Override

    public boolean onClose() {
        listAdapter.filterData(" ");
        expandAll();

        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        this.listAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        listAdapter.filterData(newText);
        expandAll();
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onResume() {
        super.onResume();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_search);

    }

    private void loadData(){
        ArrayList<ChildRow> childRows = new ArrayList<ChildRow>();
        ParentRow parentRow;

        DatabaseHelper mdb = new DatabaseHelper(this);



        Cursor events = mdb.getAllEvents();
        while(events.moveToNext()){
            String eventName = events.getString(1);
            int id = events.getInt(0);
            childRows.add(new ChildRow(R.drawable.default_event_img,eventName,id,"evt"));
        }
        parentRow = new ParentRow("Events",childRows);
        parentList.add(parentRow);


        childRows = new ArrayList<ChildRow>();

        Cursor users = mdb.getAllUsers();
        while(users.moveToNext()){
            String username = users.getString(1);
            int id = users.getInt(0);
            childRows.add(new ChildRow(R.drawable.default_user_img,username,id,"usr"));
        }
        parentRow = new ParentRow("Users",childRows);
        parentList.add(parentRow);


        childRows = new ArrayList<ChildRow>();

        Cursor groups = mdb.getAllGroups();
        while(groups.moveToNext()){
            String name = groups.getString(1);
            int id = groups.getInt(0);
            childRows.add(new ChildRow(R.drawable.default_group_img,name,id,"grp"));
        }
        parentRow = new ParentRow("Groups",childRows);
        parentList.add(parentRow);


    }
}
