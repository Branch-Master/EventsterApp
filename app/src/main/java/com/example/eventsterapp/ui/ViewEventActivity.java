package com.example.eventsterapp.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eventsterapp.Fragments.AddToGroupFragment;
import com.example.eventsterapp.Fragments.ShowMembersFragment;
import com.example.eventsterapp.R;
import com.example.eventsterapp.database.DatabaseHelper;
import com.example.eventsterapp.models.Event;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ViewEventActivity extends AppCompatActivity {
    private ImageView entityImage;
    private TextView EventName;
    private TextView EventInfo;
    private TextView EventStart;
    private TextView EventEnd;
    private TextView EventLocation;
    private TextView EventSeats;
    private DatabaseHelper mydb;

    private String nameFromIntent;

    private Button addmembers;
    private Button showmembers;
    private Button join;
    private ShowMembersFragment showMembersFragment;
    private AddToGroupFragment addToGroupFragment;

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

            addToGroupFragment = new AddToGroupFragment();
            Bundle bundle = new Bundle();
            bundle.putString("ent_name", nameFromIntent);
            bundle.putInt("ent_type", 0);
            addToGroupFragment.setArguments(bundle);


            getSupportFragmentManager().beginTransaction().replace(R.id.show_users_container_ ,addToGroupFragment).commit();

        }
    };

    private View.OnClickListener joinButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            System.out.println("what");
        }
    };


            private View.OnClickListener showmembersButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            showMembersFragment = new ShowMembersFragment();
            Bundle bundle = new Bundle();
            bundle.putString("ent_name", nameFromIntent);
            bundle.putInt("ent_type", 0);
            showMembersFragment.setArguments(bundle);


            getSupportFragmentManager().beginTransaction().replace(R.id.show_users_container_ ,showMembersFragment).commit();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);

        mydb = new DatabaseHelper(this);


        EventName = findViewById(R.id.view_event_name);
        EventInfo = findViewById(R.id.view_event_info);
        EventStart = findViewById(R.id.view_event_starts);
        EventEnd = findViewById(R.id.view_event_ends);
        EventLocation = findViewById(R.id.view_event_location);
        EventSeats = findViewById(R.id.view_event_nr_seats);
        entityImage = findViewById(R.id.event_img);

        showmembers = findViewById(R.id.show_members);
        addmembers = findViewById(R.id.add_members);
        join = findViewById(R.id.event_add_me);
        showmembers.setOnClickListener(showmembersButton);
        addmembers.setOnClickListener(addmembmersButton);
        join.setOnClickListener(joinButton);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        nameFromIntent =  getIntent().getStringExtra("ent_name");

        int id = this.mydb.getIdFromEvent(nameFromIntent);
        Event viewEvent = this.mydb.getEventById(id);
        EventName.setText( viewEvent.getEventName() );
        EventInfo.setText( viewEvent.getEventInfo());
        EventStart.setText( viewEvent.getStartDate());
        EventEnd.setText( viewEvent.getEndDate());
        EventLocation.setText( viewEvent.getLocation());
        EventSeats.setText( String.valueOf(viewEvent.getEventSeats()));
        entityImage.setImageResource(R.drawable.default_event_img);



    }

}
