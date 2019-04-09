package com.example.eventsterapp.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.eventsterapp.Adapters.MyExpandableListAdapter;
import com.example.eventsterapp.Adapters.MyExpandableListAdapterAdd;
import com.example.eventsterapp.R;
import com.example.eventsterapp.database.DatabaseHelper;
import com.example.eventsterapp.models.ChildRow;
import com.example.eventsterapp.models.ParentRow;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;

public class ShowMembersFragment extends Fragment {

    private MyExpandableListAdapter listAdapter;
    private ArrayList<ParentRow> parentList;
    private ExpandableListView myList;
    private String nameFromIntent;
    private int typeFromIntent;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_addtogroup, container, false);
        System.out.println("addmembers fragment");
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            nameFromIntent = bundle.getString("ent_name");
            typeFromIntent = bundle.getInt("ent_type");
        }
        displayList(1,v);
        expandAll();

        return v;
    }

    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for(int i =0; i < count; i++){
            myList.expandGroup(i);
        }
    }


    private void displayList(int number, View v){

        loadDataUsers();

        myList =  v.findViewById(R.id.expandambleListView_viewEntity);

        // idFromIntent = getActivity().getIntent().getIntExtra("ent_id",-1);
        // typeFromIntent = getActivity().getIntent().getStringExtra("ent_type");

        listAdapter = new MyExpandableListAdapter(getContext(),parentList);
        myList.setAdapter(listAdapter);

    }

    private void loadDataUsers(){

        parentList = new ArrayList<ParentRow>();
        ArrayList<ChildRow> childRows = new ArrayList<ChildRow>();
        ParentRow parentRow;

        DatabaseHelper mdb = new DatabaseHelper(getContext());

        if (typeFromIntent == 1) {
            int idid = mdb.getIdFromGroup(nameFromIntent);
            Cursor users = mdb.getMembersFromGroup(idid);
            while (users.moveToNext()) {
                String username = users.getString(1);
                int id = users.getInt(0);
                childRows.add(new ChildRow(R.drawable.default_user_img, username, id, "usr"));
            }
            parentRow = new ParentRow("Members", childRows);
            parentList.add(parentRow);
        } else {
            int idid = mdb.getIdFromEvent(nameFromIntent);
            Cursor users = mdb.getAttendeesFromEvent(idid);
            while (users.moveToNext()) {
                String username = users.getString(1);
                int id = users.getInt(0);
                childRows.add(new ChildRow(R.drawable.default_user_img, username, id, "usr"));
            }
            parentRow = new ParentRow("Users Attending", childRows);
            parentList.add(parentRow);
        }
    }
}
