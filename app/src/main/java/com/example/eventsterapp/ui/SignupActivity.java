package com.example.eventsterapp.ui;

import android.os.Bundle;

import com.example.eventsterapp.Fragments.CreateUserFragment;
import com.example.eventsterapp.R;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        CreateUserFragment createUserFragment = new CreateUserFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.create_fragment_container,createUserFragment).commit();

    }
}
