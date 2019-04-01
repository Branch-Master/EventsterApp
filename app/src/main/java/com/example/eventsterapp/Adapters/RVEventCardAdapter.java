package com.example.eventsterapp.Adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eventsterapp.R;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RVEventCardAdapter extends RecyclerView.Adapter<RVEventCardAdapter.EventCard> {

    public static class EventCard extends RecyclerView.ViewHolder {
        CardView cv;
        TextView cardName;
        TextView cardInfo;
        ImageView cardImg;


        EventCard(View itemView) {

            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.rv);
            cardName = (TextView)itemView.findViewById(R.id.event_name);
            cardInfo = (TextView)itemView.findViewById(R.id.event_info);
            cardImg = (ImageView)itemView.findViewById(R.id.card_img);

        }
    }

    @NonNull
    @Override
    public RVEventCardAdapter.EventCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RVEventCardAdapter.EventCard holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
