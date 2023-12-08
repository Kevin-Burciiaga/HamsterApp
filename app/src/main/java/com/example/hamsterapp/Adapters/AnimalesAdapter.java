package com.example.hamsterapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hamsterapp.Models.Animal;
import com.example.hamsterapp.R;

import java.util.List;
public class AnimalesAdapter extends RecyclerView.Adapter<AnimalesAdapter.ViewHolder>{
   private Context context;
    private List<Animal> animalList;
    public AnimalesAdapter(Context context, List<Animal> animalList) {
        this.context = context;
        this.animalList = animalList;
    }
    @NonNull
    @Override
    public AnimalesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_animales_rec,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtInfo.setText(animalList.get(position).getNombre());
    }
    @Override
    public int getItemCount() {
        return animalList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtInfo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtInfo=itemView.findViewById(R.id.txtInfo);
        }
    }
}
