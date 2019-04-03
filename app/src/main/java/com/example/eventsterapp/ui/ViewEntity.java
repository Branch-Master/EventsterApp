package com.example.eventsterapp.ui;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.eventsterapp.Adapters.MyExpandableListAdapter;
import com.example.eventsterapp.Adapters.MyExpandableListAdapterAdd;
import com.example.eventsterapp.R;
import com.example.eventsterapp.database.DatabaseHelper;
import com.example.eventsterapp.models.ChildRow;
import com.example.eventsterapp.models.Event;
import com.example.eventsterapp.models.Group;
import com.example.eventsterapp.models.ParentRow;
import com.example.eventsterapp.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewEntity extends AppCompatActivity {

    private ImageView entityImage;
    private SearchManager searchManager;
    private TextView entityName;
    private TextView entityInfo;
    private DatabaseHelper mydb;
    private MyExpandableListAdapterAdd listAdapter;
    private ArrayList<ParentRow> parentList;
    private ArrayList<ParentRow> showThisParentList;
    private ExpandableListView myList;
    private int idFromIntent;
    private String typeFromIntent;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_search:

                    return true;
                case R.id.navigation_create_new:

                    return true;
                case R.id.navigation_aboutme:

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entity);

        mydb = new DatabaseHelper(this);


        entityName = findViewById(R.id.entity_name);
        entityInfo = findViewById(R.id.entity_info);
        entityImage = findViewById(R.id.entity_img);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        idFromIntent =  getIntent().getIntExtra("ent_id",-1);
        typeFromIntent = getIntent().getStringExtra("ent_type");

        parentList = new ArrayList<ParentRow>();
        showThisParentList = new ArrayList<ParentRow>();

        if( typeFromIntent.equals("evt")){
            Event viewEvent = this.mydb.getEventById(idFromIntent);
            entityName.setText( viewEvent.getEventName() );
            entityInfo.setText( viewEvent.getEventInfo());
            entityImage.setImageResource(R.drawable.default_event_img);
            displayList(1);
        }
        else if( typeFromIntent.equals("grp")){
            Group viewGroup = this.mydb.getGroupById(idFromIntent);
            entityName.setText( viewGroup.getGroupName() );
            entityInfo.setText( viewGroup.getGroupInfo() );
            entityImage.setImageResource(R.drawable.default_group_img);
            displayList(1);
        }
        else if(typeFromIntent.equals("usr")){
            User viewUser = this.mydb.getUserById(idFromIntent);
            entityName.setText(viewUser.getUsername());
            entityInfo.setText( viewUser.getEmail() );
            entityImage.setImageResource(R.drawable.default_user_img);
            displayList(2);
        }


    }

    private void displayList(int number){
        if(number == 1){
            loadDataUsers();
        } else {
            loadDataGroupsAndEvents();
        }

        myList = (ExpandableListView) findViewById(R.id.expandambleListView_viewEntity);
        listAdapter = new MyExpandableListAdapterAdd(ViewEntity.this,parentList, getIntent().getIntExtra("ent_id",-1), getIntent().getStringExtra("ent_type"));
        myList.setAdapter(listAdapter);

    }

    private void loadDataUsers(){
        ArrayList<ChildRow> childRows = new ArrayList<ChildRow>();
        ParentRow parentRow;

        DatabaseHelper mdb = new DatabaseHelper(this);

        Cursor users = mdb.getAllUsers();
        while(users.moveToNext()){
            String username = users.getString(1);
            int id = users.getInt(0);
            childRows.add(new ChildRow(R.drawable.default_user_img,username,id,"usr"));
        }
        parentRow = new ParentRow("All Users",childRows);
        parentList.add(parentRow);

        while(users.moveToNext()){
            String username = users.getString(1);
            int id = users.getInt(0);
            childRows.add(new ChildRow(R.drawable.default_user_img,username,id,"usr"));
        }
        parentRow = new ParentRow("Users Attending",childRows);
        parentList.add(parentRow);

    }

    private void loadDataGroupsAndEvents(){
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
