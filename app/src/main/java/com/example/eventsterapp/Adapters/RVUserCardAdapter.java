package com.example.eventsterapp.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.eventsterapp.R;
import com.example.eventsterapp.models.User;
import com.example.eventsterapp.ui.ViewUserActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RVUserCardAdapter extends RecyclerView.Adapter<RVUserCardAdapter.UserCard> {


    public static class UserCard extends RecyclerView.ViewHolder {

        CardView cv;
        TextView cardName;
        TextView cardEmail;
        ImageView cardImg;
        LinearLayout card;

        UserCard(View itemView) {

            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.rv);
            cardName = (TextView)itemView.findViewById(R.id.user_card_name);
            cardEmail = (TextView)itemView.findViewById(R.id.user_card_email);
            cardImg = (ImageView)itemView.findViewById(R.id.imageView2);
            card = (LinearLayout)itemView.findViewById(R.id.user_cv);
        }
    }

    ArrayList<User> users;

    public RVUserCardAdapter(ArrayList<User> cards){
        this.users = cards;
    }


    @NonNull
    @Override
    public RVUserCardAdapter.UserCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_card, parent,false);
        UserCard userCard = new UserCard(v);
        return userCard;
    }

    @Override
    public void onBindViewHolder(@NonNull UserCard holder, int i) {
        final int count = i+1;
        holder.cardName.setText(users.get(i).getUsername());
        holder.cardEmail.setText(users.get(i).getEmail());
        holder.cardImg.setImageResource(R.drawable.default_user_img);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewUserActivity.class);
                intent.putExtra("ent_type","usr");
                intent.putExtra("ent_id",count);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
