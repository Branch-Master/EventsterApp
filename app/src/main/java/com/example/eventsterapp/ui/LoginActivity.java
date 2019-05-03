package com.example.eventsterapp.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;

import com.example.eventsterapp.Fragments.CreateUserFragment;
import com.example.eventsterapp.Fragments.LoginFragment;
import com.example.eventsterapp.Network.NetworkController;
import com.example.eventsterapp.R;
import com.example.eventsterapp.database.DatabaseHelper;
import com.example.eventsterapp.models.Event;
import com.example.eventsterapp.models.Group;
import com.example.eventsterapp.models.User;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private final String mypref = "myprefrences";
    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;
    private final String userid = "sessionEmail";
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        databaseHelper = new DatabaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment loginFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.login_fragment_container,loginFragment).commit();

        this.sharedpreferences = getSharedPreferences(mypref,Context.MODE_PRIVATE);
        this.editor = sharedpreferences.edit();

        NetworkController networkController = new NetworkController();
        networkController.getSomething();


    }

    public void createSession(String email){

        editor.putString(userid, email);
        editor.commit();

        //String getPref = sharedpreferences.getString(userid, "ekkertfannst");

    }

    @Override
    public void onBackPressed() {
        databaseHelper.dropAllData();
        User birgir = new User("birgir", "birgirbirgir", "birgir@hotmail.com");
        User adam = new User("adam","adamadam","adam@gmail.com");
        User marino = new User("marino","marinomarino","marino@hi.is");
        User isak = new User("isak", "isakisak", "isak@internet.is");
        User palli = new User("palli", "pallipalli", "palli@pallaland.is");
        Group nord = new Group("Nord","Grubba fyrir nemendafelag toluvnarfraedinga og hugbunadarverkfraedinga",0);
        Group spark = new Group("Spark","Grubba fyrir Spark",0);
        Group vinirnir = new Group("Vinirnir","Grubba fyrir okkur vinina",0);
        Group strakar = new Group("Strakar","Grubba fyrir straka",1);
        Group hi = new Group("Haskoli islands","Grubba fyrir haskola islands",1);
        Event nordEvent = new Event("Arshatid Nord","Event sem palli,birgir og adam eiga ad sja", 1, "Party", "15.5", "16.5","Rugbraudsgerdin",100,0);
        Event sparkEvent = new Event("Arshatid Spark","Event sem isak a ad sja", 2, "Party", "18.6", "20.6","Salurinn",100,0);
        Event vinirnirEvent = new Event("Arshatid Vinanna","Event sem palli,birgir,adam,marino og isak eiga ad sja", 3, "Party", "21.5", "22.5","Hja solva",3,0);
        Event strakarEvent = new Event("Strakar event","Event sem bara strakar grubban a ad sja en hun er public eiga ad sja", 4, "Party", "15.5", "16.5","Hja marino",6,0);
        Event allirEvent = new Event("Tonleikar hja ed sheeran eda eh","Event sem allir eiga ad sja", 0, "tonleikar", "15.5", "16.5","egilsholl",100,1);

        databaseHelper.addUser(birgir);
        databaseHelper.addUser(adam);
        databaseHelper.addUser(marino);
        databaseHelper.addUser(isak);
        databaseHelper.addUser(palli);

        databaseHelper.addGroup(nord);
        databaseHelper.addGroup(spark);
        databaseHelper.addGroup(vinirnir);
        databaseHelper.addGroup(strakar);
        databaseHelper.addGroup(hi);

        databaseHelper.addEvent(nordEvent);
        databaseHelper.addEvent(sparkEvent);
        databaseHelper.addEvent(vinirnirEvent);
        databaseHelper.addEvent(strakarEvent);
        databaseHelper.addEvent(allirEvent);

        databaseHelper.addUserToGroup(1,1);
        databaseHelper.addUserToGroup(2,1);
        databaseHelper.addUserToGroup(5,1);

        databaseHelper.addUserToGroup(4,2);

        databaseHelper.addUserToGroup(1,3);
        databaseHelper.addUserToGroup(2,3);
        databaseHelper.addUserToGroup(3,3);
        databaseHelper.addUserToGroup(4,3);
        databaseHelper.addUserToGroup(5,3);

        databaseHelper.addUserToGroup(4,4);
        databaseHelper.addUserToGroup(5,4);
    }




}
