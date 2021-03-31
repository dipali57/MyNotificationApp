package com.example.mynotificationapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mynotificationapp.Data.Cdatabase;
import com.example.mynotificationapp.Data.Cmethods;
import com.example.mynotificationapp.sql.Methods;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private Context context;
    private List<Methods> notificationList;
    Cdatabase cdb;

    public Adapter(Context context, List<Methods> list, Cdatabase cdb) {
        this.context = context;
        this.notificationList = list;
        this.cdb = cdb;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        Methods notification = notificationList.get(position);
        System.out.println("Check : "+notification.getTitle());
        System.out.println("Check : "+notification.getDescription());

        holder.not_title.setText(notification.getTitle());
        holder.not_desc.setText(notification.getDescription());

        String go = String.valueOf(notificationList.get(position).getId());
        if(cdb.isExist(go)){
            holder.linearLayout.setBackgroundColor(Color.WHITE);
        }

    }


    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView not_title;
        public TextView not_desc;
        public ImageView iconButton;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            linearLayout = (LinearLayout) itemView.findViewById(R.id.change_background);
            not_title = itemView.findViewById(R.id.item_title);
            not_desc = itemView.findViewById(R.id.item_desc);
            iconButton = itemView.findViewById(R.id.image);
            iconButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();

            linearLayout.setBackgroundColor(Color.LTGRAY);
            Methods notify = notificationList.get(pos);
            if(!cdb.isExist(String.valueOf(notify.getId()))){
                Cmethods read = new Cmethods(String.valueOf(notify.getId()));
                cdb.addData(read);
            }
        }
    }
}