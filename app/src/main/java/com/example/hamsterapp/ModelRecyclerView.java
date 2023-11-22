package com.example.hamsterapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ModelRecyclerView extends RecyclerView.Adapter<ModelRecyclerView.ViewHolder> {

    Context context;
    ArrayList<Model>arrayList=new ArrayList<>();

    public ModelRecyclerView(Context context,ArrayList<Model> arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview,parent,false);
        ViewHolder viewHolder =new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ModelRecyclerView.ViewHolder holder, int position) {

        holder.imageView.setImageResource(arrayList.get(position).getImage());
        holder.txtInfo.setText(arrayList.get(position).getInfo());

        holder.cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Info"+position,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context,MainActivity2.class);
                intent.putExtra("image",arrayList.get(position).getImage());
                intent.putExtra("info",arrayList.get(position).getInfo());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() { return arrayList.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder{
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
