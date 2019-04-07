package com.example.eventsterapp.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eventsterapp.Fragments.AddToGroupFragment;
import com.example.eventsterapp.R;
import com.example.eventsterapp.database.DatabaseHelper;
import com.example.eventsterapp.models.Group;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ViewGroupActivity extends AppCompatActivity {
    private ImageView entityImage;
    private TextView entityName;
    private TextView entityInfo;
    private DatabaseHelper mydb;

    private int idFromIntent;

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
        setContentView(R.layout.activity_view_group);

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


        Group viewGroup = this.mydb.getGroupById(idFromIntent);
        entityName.setText( viewGroup.getGroupName() );
        entityInfo.setText( viewGroup.getGroupInfo() );
        entityImage.setImageResource(R.drawable.default_group_img);

    }

}
