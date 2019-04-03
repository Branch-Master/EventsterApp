package com.example.eventsterapp.ui;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.eventsterapp.Adapters.MyExpandableListAdapter;
import com.example.eventsterapp.Adapters.MyExpandableListAdapterAdd;
import com.example.eventsterapp.Fragments.AddToGroupFragment;
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
import android.view.View;
import android.widget.Button;
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

    private int idFromIntent;
    private String typeFromIntent;

    private Button addmembers;
    private Button showmembers;

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


    private View.OnClickListener addmembmersButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            AddToGroupFragment addToGroupFragment = new AddToGroupFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("ent_id", idFromIntent);
            bundle.putString("ent_type",typeFromIntent);
            addToGroupFragment.setArguments(bundle);


            getSupportFragmentManager().beginTransaction().add(R.id.show_users_container_ ,addToGroupFragment).commit();
        }
    };

    private View.OnClickListener showmembersButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        System.out.println("SHOW MEMBERS");
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

        showmembers = findViewById(R.id.show_members);
        addmembers = findViewById(R.id.add_members);
        showmembers.setOnClickListener(showmembersButton);
        addmembers.setOnClickListener(addmembmersButton);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        idFromIntent =  getIntent().getIntExtra("ent_id",-1);
        typeFromIntent = getIntent().getStringExtra("ent_type");

        if( typeFromIntent.equals("evt")){
            Event viewEvent = this.mydb.getEventById(idFromIntent);
            entityName.setText( viewEvent.getEventName() );
            entityInfo.setText( viewEvent.getEventInfo());
            entityImage.setImageResource(R.drawable.default_event_img);

        }
        else if( typeFromIntent.equals("grp")){
            Group viewGroup = this.mydb.getGroupById(idFromIntent);
            entityName.setText( viewGroup.getGroupName() );
            entityInfo.setText( viewGroup.getGroupInfo() );
            entityImage.setImageResource(R.drawable.default_group_img);

        }
        else if(typeFromIntent.equals("usr")){
            User viewUser = this.mydb.getUserById(idFromIntent);
            entityName.setText(viewUser.getUsername());
            entityInfo.setText( viewUser.getEmail() );
            entityImage.setImageResource(R.drawable.default_user_img);

        }


    }





}
