package com.treeanimals.max.animal;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 95112 on 2018/6/8.
 */

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.ViewHolder> {
    private List<PetRelease> petList;
    public PetAdapter(List<PetRelease> petList){
        this.petList = petList;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView petName,petDetail,petMoney,petReleaseTime;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.previewPicture);
            petName = (TextView)itemView.findViewById(R.id.petName);
            petDetail = (TextView)itemView.findViewById(R.id.petDetail);
            petMoney = (TextView)itemView.findViewById(R.id.petMoney);
            petReleaseTime = (TextView)itemView.findViewById(R.id.petReleaseTime);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.findlist_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PetRelease petRelease = petList.get(position);
        holder.petName.setText(petRelease.getPetName());
        holder.petDetail.setText(petRelease.getPetDetail());
        holder.petMoney.setText(petRelease.getPetMoney()+"");
        holder.petReleaseTime.setText(petRelease.getReleaseTime());
        holder.imageView.setImageResource(petRelease.getImageId());
    }



    @Override
    public int getItemCount() {
        return petList.size();
    }
}
