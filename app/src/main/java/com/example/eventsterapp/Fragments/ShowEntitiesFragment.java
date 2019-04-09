package com.example.eventsterapp.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.eventsterapp.Adapters.MyExpandableListAdapter;
import com.example.eventsterapp.R;
import com.example.eventsterapp.database.DatabaseHelper;
import com.example.eventsterapp.models.ChildRow;
import com.example.eventsterapp.models.ParentRow;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;

public class ShowEntitiesFragment extends Fragment {

    private MyExpandableListAdapter listAdapter;
    private ArrayList<ParentRow> parentList;
    private ExpandableListView myList;
    private String nameFromIntent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_addtogroup, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            nameFromIntent = bundle.getString("ent_name");
        }
        displayList(v);

        return v;
    }

    private void displayList(View v){

        loadDataUsers();

        myList =  v.findViewById(R.id.expandambleListView_viewEntity);

        listAdapter = new MyExpandableListAdapter(getContext(),parentList);
        myList.setAdapter(listAdapter);

    }

    private void loadDataUsers(){

        parentList = new ArrayList<ParentRow>();
        ArrayList<ChildRow> childRows = new ArrayList<ChildRow>();
        ParentRow parentRow;

        DatabaseHelper mdb = new DatabaseHelper(getContext());
        String userId = String.valueOf(mdb.getIdFromUser(nameFromIntent));
        Cursor events = mdb.getEventsByUserId(userId);
        while(events.moveToNext()){
            String eventName = events.getString(1);
            int id = events.getInt(0);
            childRows.add(new ChildRow(R.drawable.default_event_img,eventName,id,"evt"));
        }
        parentRow = new ParentRow("Events",childRows);
        parentList.add(parentRow);


        childRows = new ArrayList<ChildRow>();

        Cursor groups = mdb.getGroupsByUserId(userId);
        while(groups.moveToNext()){
            String groupname = groups.getString(1);
            int id = groups.getInt(0);
            childRows.add(new ChildRow(R.drawable.default_group_img,groupname,id,"grp"));
        }
        parentRow = new ParentRow("Groups",childRows);
        parentList.add(parentRow);
    }
}
