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
        ImageView cardImg;

        GroupCard(View itemView){
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.rv);
            cardName = (TextView)itemView.findViewById(R.id.event_name);
            cardInfo = (TextView)itemView.findViewById(R.id.event_info);
            cardImg = (ImageView)itemView.findViewById(R.id.card_img);
        }
    }

    ArrayList<Group> groups;

    public RVGroupCardAdapter(ArrayList<Group> cards){
        this.groups = cards;
    }

    @NonNull
    @Override
    public GroupCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_card, parent,false);
        GroupCard groupCard = new GroupCard(v);
        return groupCard;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupCard holder, int i) {
        holder.cardName.setText(groups.get(i).getGroupName());
        holder.cardInfo.setText(groups.get(i).getGroupInfo());
        holder.cardImg.setImageResource(R.drawable.default_group_img);
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }


}
