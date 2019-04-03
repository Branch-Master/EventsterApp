package com.example.eventsterapp.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.eventsterapp.Fragments.CreateUserFragment;
import com.example.eventsterapp.Fragments.LoginFragment;
import com.example.eventsterapp.R;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private final String mypref = "myprefrences";
    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;
    private final String userid = "sessionEmail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment loginFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.login_fragment_container,loginFragment).commit();

        this.sharedpreferences = getSharedPreferences(mypref,Context.MODE_PRIVATE);
        this.editor = sharedpreferences.edit();

    }

    public void createSession(String email){

        editor.putString(userid, email);
        editor.commit();

        //String getPref = sharedpreferences.getString(userid, "ekkertfannst");

    }



}
