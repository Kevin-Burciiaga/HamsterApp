package com.example.hamsterapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hamsterapp.Activity1Activity;
import com.example.hamsterapp.Jaulas;
import com.example.hamsterapp.Models.Jaula;
import com.example.hamsterapp.R;

import java.util.ArrayList;

public class JaulaAdapter extends RecyclerView.Adapter<JaulaAdapter.ViewHolder>{

    Context context;
    ArrayList<Jaula> arrayList=new ArrayList<>();

public JaulaAdapter(Context context,ArrayList<Jaula> arrayList){
    this.context=context;
    this.arrayList=arrayList;
}

    @NonNull
    @Override
    public JaulaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_recycler_jaula,parent,false);
        JaulaAdapter.ViewHolder viewHolder =new JaulaAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(arrayList.get(position).getImage());
        holder.txtInfo.setText(arrayList.get(position).getInfo());
        holder.cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Activity1Activity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtInfo;
        CardView cardView1;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imgView);
            txtInfo=itemView.findViewById(R.id.txtInfo);
            cardView1=itemView.findViewById(R.id.cardView1);
        }
    }
}
