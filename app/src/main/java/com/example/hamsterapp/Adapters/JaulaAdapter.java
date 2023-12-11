package com.example.hamsterapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hamsterapp.Activity1Activity;
import com.example.hamsterapp.Models.Jaula;
import com.example.hamsterapp.R;

import java.util.List;
public class JaulaAdapter extends RecyclerView.Adapter<JaulaAdapter.ViewHolder>{
    private Context context;
    private List<Jaula> jaulaList;

    public JaulaAdapter(Context context, List<Jaula> jaulaList) {
        this.context = context;
        this.jaulaList = jaulaList;
    }
    @NonNull
    @Override
    public JaulaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_recycler_jaula,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtInfo.setText(jaulaList.get(position).getName());
        holder.cardView1.setOnClickListener(v -> {
            Intent i = new Intent(context, Activity1Activity.class);
            i.putExtra("id", jaulaList.get(position).getId());
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        });

    }
    @Override
    public int getItemCount() {return jaulaList.size();}
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtInfo;
        CardView cardView1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtInfo=itemView.findViewById(R.id.txtInfo);
            cardView1=itemView.findViewById(R.id.cardView1);
        }
    }
}