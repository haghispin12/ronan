//package com.example.ronan.MathProject;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.ronan.R;
//
//import java.util.ArrayList;
//
//public class MyUsersAdapter extends RecyclerView.Adapter<MyUsersAdapter.MyViewHolder> {
//
//
//    public interface OnItemClickListener1 {
//
//        public void onItemClick(User item);
//    }
//        private ArrayList<User> Users;
//        private OnItemClickListener1 listener;
//
//
//    public MyUsersAdapter(ArrayList<User> Users, MyUsersAdapter.OnItemClickListener1 listener){
//        this.Users = Users;
//        this.listener = listener;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyUsersAdapter.MyViewHolder holder, int position) {
//        holder.bind(Users.get(position),listener);
//    }
//
//    @Override
//    public int getItemCount() {
//        return Users.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView username;
//        ImageView userimage;
//        TextView ScoreUs;
//
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            username = itemView.findViewById(R.id.UserName);
//            userimage = itemView.findViewById(R.id.UserImg);
//            ScoreUs = itemView.findViewById(R.id.Score);
//        }
//
//        public void bind(final User item, final MyUsersAdapter.OnItemClickListener1 listener) {
//            username.setText(item.getName());
//            ScoreUs.setText(item.getScore()+"");
//            userimage.setImageBitmap(item.getBitmap());
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    listener.onItemClick(item);
//                }
//            });
//
//        }
//    }}
//
//
//
