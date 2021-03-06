package com.example.eventsterapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventsterapp.R;
import com.example.eventsterapp.database.DatabaseHelper;
import com.example.eventsterapp.models.ChildRow;
import com.example.eventsterapp.models.ParentRow;
import com.example.eventsterapp.ui.ViewEventActivity;
import com.example.eventsterapp.ui.ViewGroupActivity;
import com.example.eventsterapp.ui.ViewUserActivity;

import java.util.ArrayList;

public class MyExpandableListAdapterAdd extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<ParentRow> parentRowList;
    private ArrayList<ParentRow> originalList;
    private String entity;
    private int type;
    private DatabaseHelper mDatabasehelper;



    public MyExpandableListAdapterAdd(Context context,ArrayList<ParentRow> originalList,String entity, int type) {
        this.context = context;
        this.parentRowList = new ArrayList<>();
        this.parentRowList.addAll(originalList);
        this.originalList = new ArrayList<>();
        this.originalList.addAll(originalList);
        this.entity = entity;
        this.type = type;
        mDatabasehelper = new DatabaseHelper(this.context);
    }

    @Override
    public int getGroupCount() {
        return parentRowList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return parentRowList.get(groupPosition).getChildList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentRowList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return parentRowList.get(groupPosition).getChildList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParentRow parentRow = (ParentRow) getGroup(groupPosition);

        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.parent_row,null);
        }
        TextView heading = (TextView) convertView.findViewById(R.id.parent_text);

        heading.setText(parentRow.getName().trim());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildRow childRow = (ChildRow) getChild(groupPosition,childPosition);
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.child_row_add,null);
        }
        ImageView childIcon = (ImageView) convertView.findViewById(R.id.child_icon_add);
        childIcon.setImageResource(childRow.getIcon());

        Button childButton = (Button) convertView.findViewById(R.id.child_button_add);
        if (type == 1) {
            childButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = childRow.getId();
                    System.out.println(entity + " " + childRow.getText() + " " + type);
                    int groupId = mDatabasehelper.getIdFromGroup(entity);
                    int userId = mDatabasehelper.getIdFromUser(childRow.getText());
                    mDatabasehelper.addUserToGroup(userId, groupId);
                }
            });
        } else {
            childButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = childRow.getId();
                    System.out.println(entity + " " + childRow.getText() + " " + type);
                    int eventId = mDatabasehelper.getIdFromEvent(entity);
                    int userId = mDatabasehelper.getIdFromUser(childRow.getText());
                    mDatabasehelper.addUserToEvent(userId, eventId);
                }
            });
        }

        final TextView childText = (TextView) convertView.findViewById(R.id.child_text_add);
        childText.setText(childRow.getText().trim());

        final View finalConvertView = convertView;
        childText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Toast.makeText(finalConvertView.getContext()
                        , childText.getText(),
                        Toast.LENGTH_SHORT).show();
                Intent i = null;

                if (childRow.getType().equals("ent")) {
                    i = new Intent(finalConvertView.getContext(), ViewEventActivity.class);
                } else if (childRow.getType().equals("usr")) {
                    i = new Intent(finalConvertView.getContext(), ViewUserActivity.class);
                } else if (childRow.getType().equals("grp")) {
                    i = new Intent(finalConvertView.getContext(), ViewGroupActivity.class);
                }
                i.putExtra("ent_name", childRow.getText() );
                context.startActivity(i);
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public void filterData(String query){
        query = query.toLowerCase();
        parentRowList.clear();

        if(query.isEmpty()){
            parentRowList.addAll(originalList);
        }
        else{
            for(ParentRow parentRow: originalList){
                ArrayList<ChildRow> childList = parentRow.getChildList();
                ArrayList<ChildRow> newList = new ArrayList<ChildRow>();

                for(ChildRow childRow : childList){
                    if(childRow.getText().toLowerCase().contains(query)){
                        newList.add(childRow);
                    }
                }
                if(newList.size() > 0){
                    ParentRow nParentRow = new ParentRow(parentRow.getName(),newList);
                    parentRowList.add(nParentRow);
                }
            }
        }
        notifyDataSetChanged();
    }
}
