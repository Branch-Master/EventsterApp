package com.example.eventsterapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.eventsterapp.R;
import com.example.eventsterapp.database.DatabaseHelper;
import com.example.eventsterapp.models.Event;
import com.example.eventsterapp.ui.ViewEventActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RVEventCardAdapter extends RecyclerView.Adapter<RVEventCardAdapter.EventCard> {

    public static class EventCard extends RecyclerView.ViewHolder {
        CardView rv;
        TextView cardName;
        TextView cardInfo;
        ImageView cardImg;
        TextView cardStarts;
        TextView cardEnds;
        TextView cardLoc;
        TextView cardSeats;
        TextView cardGroup;
        LinearLayout card;




        EventCard(View itemView) {

            super(itemView);
            rv = (CardView)itemView.findViewById(R.id.rv);
            cardName = (TextView)itemView.findViewById(R.id.event_name);
            cardInfo = (TextView)itemView.findViewById(R.id.event_info);
            cardImg = (ImageView)itemView.findViewById(R.id.card_img);
            cardStarts = (TextView)itemView.findViewById(R.id.event_starts);
            cardEnds = (TextView)itemView.findViewById(R.id.event_ends);
            cardLoc = (TextView)itemView.findViewById(R.id.event_location);
            cardSeats = (TextView)itemView.findViewById(R.id.event_nr_steats);
            card = (LinearLayout) itemView.findViewById(R.id.event_cv);
            cardGroup = (TextView) itemView.findViewById(R.id.event_group);



        }
    }

    ArrayList<Event> events;
    DatabaseHelper mdb;

    public RVEventCardAdapter(ArrayList<Event> cards){
        this.events = cards;
    }

    @NonNull
    @Override
    public RVEventCardAdapter.EventCard onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_card, parent,false);
        EventCard eventCard = new EventCard(v);
        mdb = new DatabaseHelper(v.getContext());

        return eventCard;
    }

    @Override
    public void onBindViewHolder(EventCard holder, int i) {
        final int count = i+1;
        holder.cardName.setText(events.get(i).getEventName());
        holder.cardInfo.setText(events.get(i).getEventInfo());
        holder.cardStarts.setText(events.get(i).getStartDate());
        holder.cardEnds.setText(events.get(i).getEndDate());
        holder.cardImg.setImageResource(R.drawable.default_event_img);
        holder.cardLoc.setText(events.get(i).getLocation());
        holder.cardSeats.setText(events.get(i).getEventSeats() +"");


        String groupName = mdb.getGroupById(events.get(i).getGroupID()).getGroupName();

        if(groupName.equals("ekkert fannst")){
            groupName = "No Group";
        }

        holder.cardGroup.setText(groupName);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewEventActivity.class);
                intent.putExtra("ent_name", events.get(count-1).getEventName());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return events.size();
    }


}
