package com.treeanimals.max.animal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

/**
 * Created by 95112 on 2018/6/25.
 */

public class UserMessageAdapter extends RecyclerView.Adapter<UserMessageAdapter.ViewHolder> {
    private List<UserMessage> messageList ;
    private Context context;
    public UserMessageAdapter(List<UserMessage> messageList,Context context){
        this.messageList = messageList;
        this.context = context;

    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView nameView,messageView,timeView;
        View itemView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            imageView = (ImageView)itemView.findViewById(R.id.messageUserPortrait);
            nameView = (TextView)itemView.findViewById(R.id.messageUserName);
            messageView = (TextView)itemView.findViewById(R.id.messageUserNew);
            timeView = (TextView)itemView.findViewById(R.id.messageUserTime);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.received_message,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                UserMessage userMessage = messageList.get(position);
                Intent intent = new Intent();
                intent.setAction("com.treeanimals.max.animal.dialogPage");
                context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserMessage userMessage = messageList.get(position);
        holder.imageView.setImageResource(userMessage.getImageId());
        holder.messageView.setText(userMessage.getMessage());
        holder.nameView.setText(userMessage.getUserName());
        holder.timeView.setText(userMessage.getTime());
    }

    @Override
    public int getItemCount() {
        return this.messageList.size();
    }
}
