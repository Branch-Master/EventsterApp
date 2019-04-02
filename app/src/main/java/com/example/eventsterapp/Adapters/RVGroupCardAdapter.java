package com.example.eventsterapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eventsterapp.R;
import com.example.eventsterapp.models.Group;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RVGroupCardAdapter extends RecyclerView.Adapter<RVGroupCardAdapter.GroupCard> {



    public static class GroupCard extends RecyclerView.ViewHolder{

        CardView cv;
        TextView cardName;
        TextView cardInfo;
        TextView cardVis;
        ImageView cardImg;

        GroupCard(View itemView){
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.rv);
            cardName = (TextView)itemView.findViewById(R.id.group_card_name);
            cardInfo = (TextView)itemView.findViewById(R.id.group_card_info);
            cardImg = (ImageView)itemView.findViewById(R.id.group_card_img);
            cardVis = (TextView) itemView.findViewById(R.id.group_card_vis);
         }
    }

    ArrayList<Group> groups;

    public RVGroupCardAdapter(ArrayList<Group> cards){
        this.groups = cards;
    }

    @NonNull
    @Override
    public GroupCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_card, parent,false);
        GroupCard groupCard = new GroupCard(v);
        return groupCard;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupCard holder, int i) {
        holder.cardName.setText(groups.get(i).getGroupName());
        holder.cardInfo.setText(groups.get(i).getGroupInfo());
        holder.cardImg.setImageResource(R.drawable.default_group_img);
        String vis = "idk";
        switch(groups.get(i).getVisable()){
            case 0:
                vis = "private";
                break;
            case 1:
                vis = "public";
                break;
        }


        holder.cardVis.setText(vis);
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }


}
