package com.example.eventsterapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.eventsterapp.R;
import com.example.eventsterapp.database.DatabaseHelper;
import com.example.eventsterapp.models.Group;
import com.example.eventsterapp.ui.HomeActivity;

import androidx.fragment.app.Fragment;

public class CreateGroupFragment extends Fragment {

    private DatabaseHelper mDatabasehelper;
    private EditText new_group_name;
    private EditText new_group_info;
    private RadioGroup new_group_vis;
    private Button create_group_button;

    private View.OnClickListener mCreateButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name = new_group_name.getText().toString();
            String info = new_group_info.getText().toString();
            int vis =0;
            switch (new_group_vis.getCheckedRadioButtonId()){
                case R.id.group_public_create:
                    vis=1;
                    break;
                case R.id.group_private_create:
                    vis=0;
                    break;
            }

            Group newGroup = new Group(name,info,vis);

            mDatabasehelper.addGroup(newGroup);

            Intent i = new Intent(getActivity(), HomeActivity.class);
            startActivity(i);

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Context context = getContext();
        mDatabasehelper = new DatabaseHelper(context);

        final View v = inflater.inflate(R.layout.fragment_create_group, container, false);

        new_group_name = (EditText) v.findViewById(R.id.group_name_create);
        new_group_info = (EditText) v.findViewById(R.id.group_info_create);
        new_group_vis = (RadioGroup) v.findViewById(R.id.group_vis_create);
        create_group_button = (Button) v.findViewById(R.id.create_group_button);

        create_group_button.setOnClickListener(mCreateButton);




        return v;

    }






}
