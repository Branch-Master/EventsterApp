package com.example.eventsterapp.ui;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.eventsterapp.R;
import com.example.eventsterapp.database.MockData;
import com.example.eventsterapp.models.Event;
import com.example.eventsterapp.models.Group;
import com.example.eventsterapp.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

public class ViewEntity extends AppCompatActivity {

    private ImageView entityImage;

    private TextView entityName;
    private TextView entityInfo;
    private MockData mockData;

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
                case R.id.navigation_menu:

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entity);


        entityName = findViewById(R.id.entity_name);
        entityInfo = findViewById(R.id.entity_info);
        entityImage = findViewById(R.id.entity_img);




        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        this.mockData = new MockData();
        Long idFromIntent =  getIntent().getLongExtra("ent_id",-1);
        String viewEntityType = getIntent().getStringExtra("ent_type");



        if( viewEntityType.equals("evt")){
            Event viewEvent = this.mockData.getEventById(idFromIntent);
            entityName.setText( viewEvent.getEventName() );
            entityInfo.setText( viewEvent.getEventInfo());
            entityImage.setImageResource(R.drawable.default_event_img);
        }
        else if( viewEntityType.equals("grp")){
            Group viewGroup = this.mockData.getGroupById(idFromIntent);
            entityName.setText( viewGroup.getGroupName() );
            entityInfo.setText( viewGroup.getGroupInfo() );
            entityImage.setImageResource(R.drawable.default_group_img);
        }
        else if(viewEntityType.equals("usr")){
            User viewUser = this.mockData.getUserById(idFromIntent);
            entityName.setText(viewUser.getUsername());
            entityInfo.setText( viewUser.getEmail() );
            entityImage.setImageResource(R.drawable.default_user_img);
        }


    }

}
