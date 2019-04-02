package com.example.eventsterapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.eventsterapp.R;
import com.example.eventsterapp.database.DatabaseHelper;
import com.example.eventsterapp.models.Group;
import com.example.eventsterapp.models.User;

import androidx.fragment.app.Fragment;

public class CreateUserFragment extends Fragment {

    private DatabaseHelper mDatabasehelper;
    private EditText new_username;
    private EditText new_password;
    private EditText new_retypePassword;
    private EditText new_email;
    private Button signUp;

    private View.OnClickListener signUpConfirmed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String username = new_username.getText().toString();
            String password = new_password.getText().toString();
            String password1 = new_retypePassword.getText().toString();
            String email = new_email.getText().toString();

            User newUser = new User(username, password, email);


            if(!mDatabasehelper.emailUsed(email)){
                mDatabasehelper.addUser(newUser);

                Toast.makeText(getView().getContext(), "User Created", Toast.LENGTH_SHORT).show();
            }




        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context context = getContext();
        mDatabasehelper = new DatabaseHelper(context);

        final View v = inflater.inflate(R.layout.fragment_signup, container, false);

        new_username = (EditText) v.findViewById(R.id.signup_username);
        new_password = (EditText) v.findViewById(R.id.signup_password1);
        new_retypePassword = (EditText) v.findViewById(R.id.signup_password2);
        new_email = (EditText) v.findViewById(R.id.signup_email);
        signUp = (Button) v.findViewById(R.id.signup_button);

        signUp.setOnClickListener(signUpConfirmed);

        return v;
    }
}
