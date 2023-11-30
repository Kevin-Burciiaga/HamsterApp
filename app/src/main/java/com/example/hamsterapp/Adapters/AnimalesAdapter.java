package com.example.hamsterapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hamsterapp.Models.Animal;
import com.example.hamsterapp.R;

import java.util.List;

public class AnimalesAdapter extends RecyclerView.Adapter<AnimalesAdapter.ViewHolder>{
    Context context;
    List<Animal> animalList;

    public AnimalesAdapter(Context context, List<Animal> animalList) {
        this.context = context;
        this.animalList = animalList;
    }


    @NonNull
    @Override
    public AnimalesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_animales_rec,parent,false);
        AnimalesAdapter.ViewHolder viewHolder =new AnimalesAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalesAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(animalList.get(position).getImage());
        holder.txtInfo.setText(animalList.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtInfo;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imgView);
            txtInfo=itemView.findViewById(R.id.txtInfo);

        }
    }
}
