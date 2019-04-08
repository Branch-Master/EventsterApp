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
import com.example.eventsterapp.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ViewUserActivity extends AppCompatActivity {
    private ImageView UserImage;
    private TextView UserName;
    private TextView UserEmail;
    private TextView UserZodiac;
    private TextView UserPhone;
    private TextView UserInfo;
    private DatabaseHelper mydb;

    private String nameFromIntent;

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
/*
            AddToGroupFragment addToGroupFragment = new AddToGroupFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("ent_id", idFromIntent);
            addToGroupFragment.setArguments(bundle);


            getSupportFragmentManager().beginTransaction().add(R.id.show_users_container_ ,addToGroupFragment).commit(); */
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
        setContentView(R.layout.activity_view_user);

        mydb = new DatabaseHelper(this);


        UserName = findViewById(R.id.viewuser_card_name);
        UserInfo = findViewById(R.id.viewuser_card_info);
        UserEmail = findViewById(R.id.viewuser_card_email);
        UserZodiac = findViewById(R.id.viewuser_card_zodiac);
        UserPhone = findViewById(R.id.viewuser_card_phone);
        UserImage = findViewById(R.id.viewuser_image);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        nameFromIntent =  getIntent().getStringExtra("ent_name");
        int id = this.mydb.getIdFromUser(nameFromIntent);
        User viewUser = this.mydb.getUserById(id);
        UserName.setText(viewUser.getUsername());
        UserEmail.setText( viewUser.getEmail() );
        UserPhone.setText(viewUser.getPhone());
        UserZodiac.setText(viewUser.getZodiac());
        UserInfo.setText( viewUser.getInfo() );
        UserImage.setImageResource(R.drawable.default_user_img);


    }

}