package com.example.eventsterapp.ui;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.eventsterapp.Adapters.MyExpandableListAdapter;
import com.example.eventsterapp.R;
import com.example.eventsterapp.models.ChildRow;
import com.example.eventsterapp.models.parentRow;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener,SearchView.OnCloseListener {

    private TextView mTextMessage;

    private SearchManager searchManager;
    private SearchView searchView;
    private MyExpandableListAdapter listAdapter;
    private ExpandableListView myList;
    private ArrayList<parentRow> parentList;
    private ArrayList<parentRow> showThisParentList;
    private MenuItem searchItem;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        parentList = new ArrayList<parentRow>();
        showThisParentList = new ArrayList<parentRow>();

        displayList();

        expandAll();
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

    private void loadData(){
        ArrayList<ChildRow> childRows = new ArrayList<ChildRow>();
        parentRow parentRow;


        childRows.add(new ChildRow(R.mipmap.calendar_77371,"lorem ipsum 1"));
        childRows.add(new ChildRow(R.mipmap.calendar_77371,"lorem ipsum 2"));
        childRows.add(new ChildRow(R.mipmap.calendar_77371,"lorem ipsum 3"));

        parentRow = new parentRow("group 1",childRows);
        parentList.add(parentRow);

        childRows = new ArrayList<ChildRow>();
        childRows.add(new ChildRow(R.mipmap.calendar_77371,"lorem ipsum alora"));
        childRows.add(new ChildRow(R.mipmap.calendar_77371,"lorem ipsum ypsum"));
        childRows.add(new ChildRow(R.mipmap.calendar_77371,"lorem ipsum dipsum"));
        parentRow = new parentRow("group 2",childRows);
        parentList.add(parentRow);


    }
}
