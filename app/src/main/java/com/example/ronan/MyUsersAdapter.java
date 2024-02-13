package com.example.ronan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyUsersAdapter extends RecyclerView.Adapter<MyUsersAdapter.MyViewHolder> {


    public interface OnItemClickListener {

        public void onItemClick(user item);
    }
        private ArrayList<user> users;
        private OnItemClickListener listener;


    public MyUsersAdapter(ArrayList<user> users, MyUsersAdapter.OnItemClickListener listener){
        this.users = users;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyUsersAdapter.MyViewHolder holder, int position) {
        holder.bind(users.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        ImageView userimage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.UserName);
            userimage = itemView.findViewById(R.id.UserImg);
        }

        public void bind(final user item, final MyUsersAdapter.OnItemClickListener listener) {
            username.setText(item.getName());
            userimage.setImageBitmap(item.getBitmap());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });

        }
    }}



