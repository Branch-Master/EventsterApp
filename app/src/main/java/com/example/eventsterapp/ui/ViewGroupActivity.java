package com.example.eventsterapp.ui;

import android.database.Cursor;
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
import com.example.eventsterapp.models.Group;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ViewGroupActivity extends AppCompatActivity {
    private ImageView groupImage;
    private TextView groupName;
    private TextView groupInfo;
    private DatabaseHelper mydb;
    private int open;

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
            bundle.putInt("ent_type", 1);
            addToGroupFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction().replace(R.id.show_users_container_ ,addToGroupFragment).commit();

            open = 1;
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
            bundle.putInt("ent_type", 1);
            showMembersFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction().replace(R.id.show_users_container_ ,showMembersFragment).commit();

            open = 2;

            System.out.println("SHOW MEMBERS");
            Cursor groupMember = mydb.getGroupMember();
            while(groupMember.moveToNext()){
                System.out.println(Integer.parseInt(groupMember.getString(0)) + "_______________________" + Integer.parseInt(groupMember.getString(1)));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);

        open = 0;

        mydb = new DatabaseHelper(this);


        groupName = findViewById(R.id.group_name);
        groupInfo = findViewById(R.id.group_info);
        groupImage = findViewById(R.id.group_img);

        showmembers = findViewById(R.id.show_members);
        addmembers = findViewById(R.id.add_members);
        join = findViewById(R.id.group_add_me);
        showmembers.setOnClickListener(showmembersButton);
        addmembers.setOnClickListener(addmembmersButton);
        join.setOnClickListener(joinButton);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        nameFromIntent =  getIntent().getStringExtra("ent_name");

        int id = this.mydb.getIdFromGroup(nameFromIntent);
        Group viewGroup = this.mydb.getGroupById(id);
        groupName.setText( viewGroup.getGroupName() );

        groupInfo.setText( viewGroup.getGroupInfo() );
        groupImage.setImageResource(R.drawable.default_group_img);

    }

}
