package com.example.eventsterapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.eventsterapp.R;
import com.example.eventsterapp.database.DatabaseHelper;
import com.example.eventsterapp.models.Event;
import com.example.eventsterapp.models.Group;
import com.example.eventsterapp.models.User;
import com.example.eventsterapp.ui.HomeActivity;
import com.example.eventsterapp.ui.LoginActivity;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class LoginFragment extends Fragment {

    private DatabaseHelper databaseHelper;
    private EditText username;
    private EditText password;
    private Button login_button;
    private Button signup_button;
    private TextView notValid;
    private CheckBox saveInfo;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor editor;
    private String myLogPref = "loginPreference";
    private String ninfo = "ninfo";
    private String pinfo = "pinfo";
    private String cinfo = "cinfo";



    private View.OnClickListener signupButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CreateUserFragment createUserFragment = new CreateUserFragment();

            FragmentManager fragmentManager =getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            transaction.replace(R.id.login_fragment_container,createUserFragment).commit();

        }
    };

    private View.OnClickListener loginButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            String un = username.getText().toString();
            String p = password.getText().toString();
            Boolean test1 = un.length() > 0;
            Boolean test2 = p.length() > 7;

            User validLoginInfo = databaseHelper.validateLogin(un,p);


            if(validLoginInfo != null && test1 && test2){
                if(saveInfo.isChecked()){
                    editor.putString(cinfo,"true");
                    editor.commit();

                    editor.putString(ninfo, username.getText().toString());
                    editor.commit();

                    editor.putString(pinfo,password.getText().toString());
                    editor.commit();

                }
                else{
                    editor.remove(cinfo);
                    editor.commit();
                    editor.remove(ninfo);
                    editor.commit();
                    editor.remove(pinfo);
                    editor.commit();
                }


                ((LoginActivity)getActivity()).createSession(validLoginInfo.getEmail());


                //String tag, String startDate, String endDate, String location, int eventSeats,int vis


                Intent i = new Intent(getActivity(), HomeActivity.class);
                startActivity(i);
            }
            else{
                notValid.setText("Username or password incorrect");
            }

        }
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        Context c = getContext();
        databaseHelper = new DatabaseHelper(c);

        this.loginPreferences = PreferenceManager.getDefaultSharedPreferences(c);
        this.editor = loginPreferences.edit();



        final View v = inflater.inflate(R.layout.fragment_login, container, false);

        username = (EditText) v.findViewById(R.id.login_username);
        password = (EditText) v.findViewById(R.id.login_password);
        login_button = (Button) v.findViewById(R.id.loginButton);
        signup_button = (Button) v.findViewById(R.id.signUpButton);
        notValid = (TextView) v.findViewById(R.id.login_not_valid);
        saveInfo = (CheckBox) v.findViewById(R.id.login_saveinfo);

        checkPreference();
        signup_button.setOnClickListener(signupButtonClick);
        login_button.setOnClickListener(loginButtonClick);


        return v;
    }

    private void checkPreference(){
        String checkbox = loginPreferences.getString(cinfo,"false");
        String name = loginPreferences.getString(ninfo,"");
        String pass = loginPreferences.getString(pinfo,"");

        username.setText(name);
        password.setText(pass);

        if(checkbox.equals("true")){
            saveInfo.setChecked(true);
        }
        else{
            saveInfo.setChecked(false);
        }


    }

}
