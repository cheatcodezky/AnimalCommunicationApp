package com.treeanimals.max.animal;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 95112 on 2018/6/9.
 */

public class PetAcceptAdapter extends RecyclerView.Adapter<PetAcceptAdapter.ViewHolder> {
    private List<PetAccept> petList;
    public PetAcceptAdapter(List<PetAccept> petList){
        this.petList = petList;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imageView;
        TextView userName,timeView,detail;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView  = (CircleImageView)itemView.findViewById(R.id.releaseItemPicture);
            userName = (TextView)itemView.findViewById(R.id.userName);
            timeView = (TextView)itemView.findViewById(R.id.releaseItemTime);
            detail  =(TextView)itemView.findViewById(R.id.releaseItemDetail);
        }
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.acceptlist_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PetAccept petAccept  =petList.get(position);
        holder.imageView.setImageResource(petAccept.getImageId());
        holder.detail.setText(petAccept.getPetAcceptDetail());
        holder.timeView.setText(petAccept.getPetAcceptTime());
        holder.userName.setText(petAccept.getUserName());
    }

    @Override
    public int getItemCount() {
        return petList.size();
    }
}
