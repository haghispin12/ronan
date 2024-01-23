package com.example.ronan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyFruitsAdapter extends RecyclerView.Adapter<MyFruitsAdapter.MyViewHolder> {

    public interface OnItemClickListener{

        public void onItemClick(Fruit item);
    }
    private ArrayList<Fruit> fruits;
    private OnItemClickListener listener;


    public MyFruitsAdapter(ArrayList<Fruit> fruits, OnItemClickListener listener){
        this.fruits = fruits;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(fruits.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
            TextView FruitName;
            ImageView FruitImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            FruitName = itemView.findViewById(R.id.FruitName);
           FruitImage = itemView.findViewById(R.id.FruitImage);
        }
        public void bind (final Fruit item, final OnItemClickListener listener){
            FruitName.setText(item.getName());
            FruitImage.setImageResource(item.getDrawable());
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });

        }
    }
}
