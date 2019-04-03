package com.example.eventsterapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eventsterapp.Fragments.CreateEventFragment;
import com.example.eventsterapp.Fragments.CreateGroupFragment;
import com.example.eventsterapp.R;
import com.example.eventsterapp.database.DatabaseHelper;
import com.example.eventsterapp.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class AboutmeActivity extends AppCompatActivity {

    private final String mypref = "myprefrences";
    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;
    private final String userid = "sessionEmail";
    private DatabaseHelper mdb;

    private EditText aboutmeEmail;
    private EditText aboutmeName;
    private EditText aboutmeZodiac;
    private EditText aboutmephone;
    private EditText aboutmeinfo;
    private Button submitInfo;

    private int id;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent i;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    System.out.println("Navigation Home");
                    i = new Intent(AboutmeActivity.this, HomeActivity.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_search:
                    System.out.println("Navigation Search");
                    return true;
                case R.id.navigation_create_new:
                    System.out.println("Navigation Create New");
                    i = new Intent(AboutmeActivity.this, CreateActivity.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_aboutme:
                    System.out.println("Navigation About me");
                    i = new Intent(AboutmeActivity.this, AboutmeActivity.class);
                    startActivity(i);
                    return true;
            }
            return false;
        }
    };

    private View.OnClickListener changeinfo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String n = aboutmeName.getText().toString();
            String e = aboutmeEmail.getText().toString();
            String z = aboutmeZodiac.getText().toString();
            String p = aboutmephone.getText().toString();

            String sessionEmail = sharedpreferences.getString(userid,"ekkert fannst");
            System.out.println(sessionEmail + "===currEmail");
            User cu = mdb.findUserByEmail(sessionEmail);

            if(!n.equals(cu.getUsername())){
                mdb.changeUsername(id,n);
            }
            if(!z.equals(cu.getZodiac())){
                mdb.changeZodiac(id,z);
            }
            if(!p.equals(cu.getPhone())){
                mdb.changePhone(id,p);
                System.out.println("phone changed to: " + p);
            }
            if(!e.equals(cu.getEmail()) && !mdb.emailUsed(e)){
                mdb.changeEmail(id,e);
                editor.clear();
                editor.putString(userid,e);
                editor.commit();
                System.out.println("email changed to: " + e);
            }

            sessionEmail = sharedpreferences.getString(userid,"ekkert fannst");
            System.out.println(sessionEmail + "===currEmail");

            displayUserInfo();

            Toast.makeText(AboutmeActivity.this, "User information changed", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutme);

        mdb = new DatabaseHelper(this);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        aboutmeEmail = (EditText) findViewById(R.id.aboutme_email);
        aboutmeName = (EditText) findViewById(R.id.aboutme_username);
        aboutmeZodiac = (EditText) findViewById(R.id.aboutme_zodiac);
        aboutmephone = (EditText) findViewById(R.id.aboutme_phone);
        aboutmeinfo = (EditText) findViewById(R.id.aboutme_info);
        submitInfo = (Button) findViewById(R.id.change_my_info);

        submitInfo.setOnClickListener(changeinfo);

        sharedpreferences = getSharedPreferences(mypref, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        displayUserInfo();


    }

    public void displayUserInfo(){
        String sessionEmail = sharedpreferences.getString(userid,"ekkert fannst");

        User currentUser = mdb.findUserByEmail(sessionEmail);

        id = currentUser.getId();
        aboutmeName.setText(currentUser.getUsername());
        aboutmeEmail.setText(currentUser.getEmail());
        aboutmeZodiac.setText(currentUser.getZodiac());
        aboutmephone.setText(currentUser.getPhone());
    }




}