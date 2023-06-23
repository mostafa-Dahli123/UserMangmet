package com.example.usermangmet;

import android.content.Context;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<User>userArrayList;
    User user;

    public MyAdapter(Context context, ArrayList<User> userArrayList,User user) {
        this.context = context;
        this.userArrayList = userArrayList;
        this.user=user;
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

        holder. firstName.setText(user.getFname());
        holder.lastName.setText(user.getLname());
        holder.Email.setText(user.getEmail());
        holder.Birthday.setText(user.getBirthDay());
        holder.PhoneNum.setText(String.valueOf(user.getPhoneNum()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user.isTeacher()) {
                            sendSMS(user.getPhoneNum(), "Hello, this is your teacher "+user.getFname()+" "+user.getLname()+" if you need any help just call me"+user.getPhoneNum());
                    Toast.makeText(context, "help send successfully", Toast.LENGTH_SHORT).show();
                }else {

                    AppCompatActivity activity = (AppCompatActivity) context;
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutMain, new AfterStudent(userArrayList.get(position))).addToBackStack(null).commit();

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }
    public static class  MyViewHolder extends RecyclerView.ViewHolder{

        TextView firstName , lastName ,Email,Birthday,PhoneNum;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            firstName=itemView.findViewById(R.id.tvfirsName);
            lastName=itemView.findViewById(R.id.tvlastName);
            Email=itemView.findViewById(R.id.tvemail);
            Birthday=itemView.findViewById(R.id.tvbirthday);
            PhoneNum=itemView.findViewById(R.id.tvphonenumber);
        }
    }
    public void sendSMS(String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            // SMS sent successfully
        } catch (Exception e) {
            // Failed to send SMS
            e.printStackTrace();
        }
    }
}

