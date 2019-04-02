package com.example.eventsterapp.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.eventsterapp.Fragments.CreateEventFragment;
import com.example.eventsterapp.Fragments.CreateGroupFragment;
import com.example.eventsterapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class CreateActivity extends FragmentActivity {


    private TabLayout.OnTabSelectedListener mOnTabSelectedListener = new TabLayout.OnTabSelectedListener() {


        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            System.out.println("tab Selected: " + tab.getPosition() + "================================");

            //Fragment createFragment =  (Fragment) findViewById(R.id.create_fragment);

            switch( tab.getPosition() ){
                case 0:
                    CreateEventFragment createEventFragment = new CreateEventFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.create_fragment_container,createEventFragment).commit();

                    break;
                case 1:


                    CreateGroupFragment createGroupFragment = new CreateGroupFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.create_fragment_container,createGroupFragment).commit();

                    break;

            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent i;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    System.out.println("Navigation Home");
                    i = new Intent(CreateActivity.this, HomeActivity.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_search:
                    System.out.println("Navigation Search");
                    i = new Intent(CreateActivity.this, SearchActivity.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_create_new:
                    System.out.println("Navigation Create New");
                    return true;
                case R.id.navigation_menu:
                    System.out.println("Navigation Menu");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        TabLayout tabLayout_event = (TabLayout) findViewById(R.id.tablayoutCreate);
        tabLayout_event.addOnTabSelectedListener(mOnTabSelectedListener);

        CreateEventFragment createEventFragment = new CreateEventFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.create_fragment_container,createEventFragment).commit();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_create_new);
    }

    @Override
    public void onResume() {
        super.onResume();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_create_new);
    }
}
