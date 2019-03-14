package com.example.eventsterapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eventsterapp.R;
import com.example.eventsterapp.models.Event;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.EventViewHolder>{

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView eventName;
        TextView eventInfo;




        EventViewHolder(View itemView) {

            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.rv);
            eventName = (TextView)itemView.findViewById(R.id.event_name);
            eventInfo = (TextView)itemView.findViewById(R.id.event_info);

        }
    }

    List<Event> events;

   public RVAdapter(List<Event> events){
        this.events = events;
    }


    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_card, viewGroup, false);
        EventViewHolder pvh = new EventViewHolder(v);
        return pvh;
    }


    public void onBindViewHolder(EventViewHolder personViewHolder, int i) {
        personViewHolder.eventName.setText(events.get(i).getEventName());
        personViewHolder.eventInfo.setText(events.get(i).getEventInfo());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }




}
