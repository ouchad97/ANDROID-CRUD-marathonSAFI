package com.mobile.basicapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter  extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{


    private Context context;
    private Activity activity;
    private ArrayList user_id, user_name, user_email, user_tel;


    CustomAdapter(Activity activity, Context context, ArrayList user_id, ArrayList user_name, ArrayList user_email,
                  ArrayList user_tel){
        this.activity = activity;
        this.context = context;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_tel = user_tel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.idUser.setText(String.valueOf(user_id.get(position)));
        holder.nameUser.setText(String.valueOf(user_name.get(position)));
        holder.emailUser.setText(String.valueOf(user_email.get(position)));
        holder.telUser.setText(String.valueOf(user_tel.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("_id", String.valueOf(user_id.get(position)));
                intent.putExtra("nameUser", String.valueOf(user_name.get(position)));
                intent.putExtra("emailUser", String.valueOf(user_email.get(position)));
                intent.putExtra("telUser", String.valueOf(user_tel.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return user_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView idUser, nameUser, emailUser, telUser;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            idUser = itemView.findViewById(R.id.idUser);
            nameUser = itemView.findViewById(R.id.nameUser);
            emailUser = itemView.findViewById(R.id.emailUser);
            telUser = itemView.findViewById(R.id.telUser);
            mainLayout = itemView.findViewById(R.id.mainLayoutt);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);

        }
    }

}
