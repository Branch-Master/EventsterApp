package com.example.eventsterapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventsterapp.R;
import com.example.eventsterapp.models.ChildRow;
import com.example.eventsterapp.models.parentRow;

import java.util.ArrayList;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<parentRow> parentRowList;
    private ArrayList<parentRow> originalList;

    public MyExpandableListAdapter(Context context,ArrayList<parentRow> originalList) {
        this.context = context;
        this.parentRowList = new ArrayList<>();
        this.parentRowList.addAll(originalList);
        this.originalList = new ArrayList<>();
        this.originalList.addAll(originalList);
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
        parentRow parentRow = (parentRow) getGroup(groupPosition);

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
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildRow childRow = (ChildRow) getChild(groupPosition,childPosition);
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.child_row,null);
        }
        ImageView childIcon = (ImageView) convertView.findViewById(R.id.child_icon);
        childIcon.setImageResource(R.mipmap.calendar_77371);

        final TextView childText = (TextView) convertView.findViewById(R.id.child_text);
        childText.setText(childRow.getText().trim());

        final View finalConvertView = convertView;
        childText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(finalConvertView.getContext()
                        , childText.getText(),
                        Toast.LENGTH_SHORT).show();
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
            for(parentRow parentRow: originalList){
                ArrayList<ChildRow> childList = parentRow.getChildList();
                ArrayList<ChildRow> newList = new ArrayList<ChildRow>();

                for(ChildRow childRow : childList){
                    if(childRow.getText().toLowerCase().contains(query)){
                        newList.add(childRow);
                    }
                }
                if(newList.size() > 0){
                    parentRow nParentRow = new parentRow(parentRow.getName(),newList);
                    parentRowList.add(nParentRow);
                }
            }
        }
        notifyDataSetChanged();
    }
}
