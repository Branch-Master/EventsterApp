package com.example.eventsterapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.eventsterapp.R;

import androidx.appcompat.widget.ButtonBarLayout;
import androidx.fragment.app.Fragment;

public class CreateEventFragment extends Fragment {

    EditText new_event_name;
    Button create_event;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_create_event, container, false);
        new_event_name = (EditText) v.findViewById(R.id.name_create);
        create_event = (Button) v.findViewById(R.id.create_event_button);

        create_event.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println(new_event_name.getText() + "=========");
            }
        });

        return v;
    }


}
