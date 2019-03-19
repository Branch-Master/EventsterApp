package com.example.eventsterapp.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eventsterapp.R;
import com.example.eventsterapp.models.*;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CardViewHolder>{

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView cardName;
        TextView cardInfo;




        CardViewHolder(View itemView) {

            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.rv);
            cardName = (TextView)itemView.findViewById(R.id.event_name);
            cardInfo = (TextView)itemView.findViewById(R.id.event_info);

        }
    }

    ArrayList<Event> events;
    ArrayList<Group> groups;
    ArrayList<User> users;
    String type;

   public RVAdapter(ArrayList<Event> cards,Event e){

       this.events = cards;
       this.type="event";

    }

    public RVAdapter(ArrayList<Group> cards, Group g){

        this.groups = cards;
        this.type = "group";

    }

    public RVAdapter(ArrayList<User> cards , User u){

        this.users = cards;
        this.type = "user";

    }


    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_card, viewGroup, false);
        CardViewHolder pvh = new CardViewHolder(v);
        return pvh;
    }


    public void onBindViewHolder(CardViewHolder personViewHolder, int i) {

        switch (this.type.toLowerCase()) {
            case "event":
                personViewHolder.cardName.setText(events.get(i).getEventName());
                personViewHolder.cardInfo.setText(events.get(i).getEventInfo());
                break;

            case "group":
                personViewHolder.cardName.setText(groups.get(i).getGroupName());
                personViewHolder.cardInfo.setText(groups.get(i).getGroupInfo());
                break;
            case "user":
                personViewHolder.cardName.setText(users.get(i).getUsername());
                personViewHolder.cardInfo.setText(users.get(i).getEmail());
                break;
        }


    }

    @Override
    public int getItemCount() {
        switch (this.type.toLowerCase()) {
            case "event":
                return events.size();
            case "group":
                return groups.size();
            case "user":
                return users.size();
        }
        return 0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }




}
