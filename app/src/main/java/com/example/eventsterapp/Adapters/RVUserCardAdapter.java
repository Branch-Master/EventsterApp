package com.example.eventsterapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eventsterapp.R;
import com.example.eventsterapp.models.User;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RVUserCardAdapter extends RecyclerView.Adapter<RVUserCardAdapter.UserCard> {


    public static class UserCard extends RecyclerView.ViewHolder {
        CardView cv;
        TextView cardName;
        TextView cardInfo;
        ImageView cardImg;


        UserCard(View itemView) {

            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.rv);
            cardName = (TextView)itemView.findViewById(R.id.event_name);
            cardInfo = (TextView)itemView.findViewById(R.id.event_info);
            cardImg = (ImageView)itemView.findViewById(R.id.card_img);

        }
    }

    ArrayList<User> users;

    public RVUserCardAdapter(ArrayList<User> cards){
        this.users = cards;
    }


    @NonNull
    @Override
    public RVUserCardAdapter.UserCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_card, parent,false);
        UserCard userCard = new UserCard(v);
        return userCard;
    }

    @Override
    public void onBindViewHolder(@NonNull UserCard holder, int i) {
        holder.cardName.setText(users.get(i).getUsername());
        holder.cardInfo.setText(users.get(i).getEmail());
        holder.cardImg.setImageResource(R.drawable.default_user_img);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
