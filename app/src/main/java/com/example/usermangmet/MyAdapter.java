package com.example.usermangmet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
rra
    Context context;
    ArrayList<User>userArrayList;

    public MyAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user= userArrayList.get(position);

        holder. firstName.setText(user.firstname);
        holder.lastName.setText(user.lastname);
        holder.Email.setText(user.Email);
        holder.Birthday.setText(user.Birthday);
        holder.PhoneNum.setText(String.valueOf(user.PhoneNum));
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }
    public static class  MyViewHolder extends RecyclerView.ViewHolder{

        TextView firstName , lastName ,Email,Birthday,PhoneNum

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            firstName=itemView.findViewById(R.id.tvfirsName);
            lastName=itemView.findViewById(R.id.tvlastName);
            Email=itemView.findViewById(R.id.tvemail);
            Birthday=itemView.findViewById(R.id.tvbirthday);
            PhoneNum=itemView.findViewById(R.id.tvphonenumber);
        }
    }
}

