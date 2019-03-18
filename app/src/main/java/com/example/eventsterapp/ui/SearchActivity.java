package com.example.eventsterapp.ui;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.eventsterapp.Adapters.MyExpandableListAdapter;
import com.example.eventsterapp.R;
import com.example.eventsterapp.models.ChildRow;
import com.example.eventsterapp.models.ParentRow;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.view.Menu;
import android.view.MenuItem;
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
    private ArrayList<ParentRow> parentList;
    private ArrayList<ParentRow> showThisParentList;
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

        parentList = new ArrayList<ParentRow>();
        showThisParentList = new ArrayList<ParentRow>();

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
        ParentRow parentRow;


        childRows.add(new ChildRow(R.mipmap.calendar_77371,"Ammæli"));
        childRows.add(new ChildRow(R.mipmap.calendar_77371,"BBQ"));
        childRows.add(new ChildRow(R.mipmap.calendar_77371,"árshátíð"));

        parentRow = new ParentRow("Events",childRows);
        parentList.add(parentRow);

        childRows = new ArrayList<ChildRow>();
        childRows.add(new ChildRow(R.mipmap.calendar_77371,"sjomlar"));
        childRows.add(new ChildRow(R.mipmap.calendar_77371,"fjölskyldan"));
        childRows.add(new ChildRow(R.mipmap.calendar_77371,"Nörd"));
        parentRow = new ParentRow("Groups",childRows);
        parentList.add(parentRow);

        childRows = new ArrayList<ChildRow>();
        childRows.add(new ChildRow(R.mipmap.calendar_77371,"Marinó"));
        childRows.add(new ChildRow(R.mipmap.calendar_77371,"Birgir"));
        childRows.add(new ChildRow(R.mipmap.calendar_77371,"Adam"));
        childRows.add(new ChildRow(R.mipmap.calendar_77371,"Ísak"));
        parentRow = new ParentRow("Users",childRows);
        parentList.add(parentRow);


    }
}
