package com.treeanimals.max.animal;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 95112 on 2018/6/26.
 */

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {
    private List<Msg> msgList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout leftLayout,rightLayout;
        TextView leftTextView,rightTextView;
        ImageView leftImageView ,rightImageView;
        public ViewHolder(View view) {
            super(view);
            leftLayout = (LinearLayout)view.findViewById(R.id.leftLayout);
            rightLayout = (LinearLayout)view.findViewById(R.id.rightLayout);
            leftTextView = (TextView)view.findViewById(R.id.leftMessage);
            rightTextView = (TextView)view.findViewById(R.id.rightMessage);
            leftImageView = (ImageView)view.findViewById(R.id.leftMessageImage);
            rightImageView = (ImageView)view.findViewById(R.id.rightMessageImage);

        }
    }
    public MsgAdapter(List list){
        msgList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Msg msg = msgList.get(position);
        if (msg.getType() == Msg.TYPE_RECEIVED){
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftTextView.setText(msg.getContent());
            holder.leftImageView.setImageResource(msg.getHeadPortrait());
        }else{
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.rightTextView.setText(msg.getContent());
            holder.rightImageView.setImageResource(msg.getHeadPortrait());
        }

    }



    @Override
    public int getItemCount() {
        return msgList.size();
    }
}
