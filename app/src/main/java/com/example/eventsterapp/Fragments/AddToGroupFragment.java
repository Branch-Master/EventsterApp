package com.example.eventsterapp.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.eventsterapp.Adapters.MyExpandableListAdapterAdd;
import com.example.eventsterapp.R;
import com.example.eventsterapp.database.DatabaseHelper;
import com.example.eventsterapp.models.ChildRow;
import com.example.eventsterapp.models.ParentRow;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;

public class AddToGroupFragment extends Fragment {

    private MyExpandableListAdapterAdd listAdapter;
    private ArrayList<ParentRow> parentList;
    private ArrayList<ParentRow> showThisParentList;
    private ExpandableListView myList;
    private String nameFromIntent;
    private DatabaseHelper mDatabasehelper;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_addtogroup, container, false);
        System.out.println("addmembers fragment");
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            nameFromIntent = bundle.getString("ent_name");
        }
        displayList(1,v);

        return v;
    }


    private void displayList(int number, View v){
        if(number == 1){
            loadDataUsers();
        } else {
            loadDataGroupsAndEvents();
        }

        myList =  v.findViewById(R.id.expandambleListView_viewEntity);

        // idFromIntent = getActivity().getIntent().getIntExtra("ent_id",-1);
        // typeFromIntent = getActivity().getIntent().getStringExtra("ent_type");

        listAdapter = new MyExpandableListAdapterAdd(getContext(),parentList, nameFromIntent, 0);
        myList.setAdapter(listAdapter);

    }

    private void loadDataUsers(){

        parentList = new ArrayList<ParentRow>();
        ArrayList<ChildRow> childRows = new ArrayList<ChildRow>();
        ParentRow parentRow;

        DatabaseHelper mdb = new DatabaseHelper(getContext());

        Cursor users = mdb.getAllUsers();
        while(users.moveToNext()){
            String username = users.getString(1);
            int id = users.getInt(0);
            childRows.add(new ChildRow(R.drawable.default_user_img,username,id,"usr"));
        }
        parentRow = new ParentRow("All Users",childRows);
        parentList.add(parentRow);

        while(users.moveToNext()){
            String username = users.getString(1);
            int id = users.getInt(0);
            childRows.add(new ChildRow(R.drawable.default_user_img,username,id,"usr"));
        }
        parentRow = new ParentRow("Users Attending",childRows);
        parentList.add(parentRow);

    }

    private void loadDataGroupsAndEvents(){
        ArrayList<ChildRow> childRows = new ArrayList<ChildRow>();
        ParentRow parentRow;

        Context c = getContext();
        DatabaseHelper mdb = new DatabaseHelper(c);



        Cursor events = mdb.getAllEvents();
        while(events.moveToNext()){
            String eventName = events.getString(1);
            int id = events.getInt(0);
            childRows.add(new ChildRow(R.drawable.default_event_img,eventName,id,"evt"));
        }
        parentRow = new ParentRow("Events",childRows);
        parentList.add(parentRow);

        childRows = new ArrayList<ChildRow>();

        Cursor groups = mdb.getAllGroups();
        while(groups.moveToNext()){
            String name = groups.getString(1);
            int id = groups.getInt(0);
            childRows.add(new ChildRow(R.drawable.default_group_img,name,id,"grp"));
        }
        parentRow = new ParentRow("Groups",childRows);
        parentList.add(parentRow);
    }
    }
