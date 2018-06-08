package com.treeanimals.max.animal;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 95112 on 2018/6/6.
 */

public class HomeFragment extends Fragment {
    private TextView findButton,releaseButton;
    private View findTag,releaseTag;
    int babyGreen,black,white;
    private List<PetRelease> petList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View view = inflater.inflate(R.layout.home_page,container,false);

        findButton = (TextView)view.findViewById(R.id.findButton);
        releaseButton = (TextView)view.findViewById(R.id.releaseButton);
        findTag = view.findViewById(R.id.findButtonTag);
        releaseTag = view.findViewById(R.id.releaseButtonTag);
        findButton.setOnClickListener(new ClickListener());
        releaseButton.setOnClickListener(new ClickListener());
        black = getResources().getColor(R.color.black);
        babyGreen = getResources().getColor(R.color.babyGreen);
        white = getResources().getColor(R.color.white);

        initArrayList();
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.findList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        PetAdapter petAdapter = new PetAdapter(petList);
        recyclerView.setAdapter(petAdapter);

        return view;
    }
    private void initArrayList(){
        for (int i =0 ; i< 10;i++){
            PetRelease pet = new PetRelease("狗狗","拆家，吃得多，粘人","1个小时前",R.drawable.doog,1000);
            petList.add(pet);
        }
    }
    class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            switch (view.getId()){
                case R.id.releaseButton:
                    releaseButton.setTextColor(babyGreen);
                    releaseTag.setBackgroundColor(babyGreen);
                    findButton.setTextColor(black);
                    findTag.setBackgroundColor(white);
                    break;
                case R.id.findButton:
                    releaseButton.setTextColor(black);
                    releaseTag.setBackgroundColor(white);
                    findButton.setTextColor(babyGreen);
                    findTag.setBackgroundColor(babyGreen);
                    break;
                default:
                    break;
            }
        }
    }
}
