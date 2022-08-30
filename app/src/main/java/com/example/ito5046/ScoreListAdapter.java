package com.example.ito5046;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScoreListAdapter extends RecyclerView.Adapter<ScoreListAdapter.MyViewHolder> {
    Context context;
    ArrayList competitionName;
    int position;

    ScoreListAdapter(Context context, ArrayList battle_name){
        this.context = context;
        this.competitionName = battle_name;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.battle_name_text.setText(String.valueOf(competitionName.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // change this to view scores
                Intent intent = new Intent(context,DisplayScoresActivity.class);
                Singleton.addBattle(String.valueOf(competitionName.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return competitionName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView battle_name_text;
        LinearLayoutCompat mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            battle_name_text = itemView.findViewById(R.id.battle_name_text);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

}
