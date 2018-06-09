package com.treeanimals.max.animal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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

public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener{
    private TextView findButton,releaseButton;
    private View findTag,releaseTag;
    int babyGreen,black,white;
    private List<PetRelease> petList = new ArrayList<>();
    private List<PetAccept> petAcceptList = new ArrayList<>();
    private ViewPager homeViewPager;
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

        homeViewPager = (ViewPager)view.findViewById(R.id.homePagerView);
        ArrayList<View> homeViewList = new ArrayList<>();
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View releaseView = layoutInflater.inflate(R.layout.release_page,null,false);
        View acceptView = layoutInflater.inflate(R.layout.accept_page,null,false);
        homeViewList.add(releaseView);
        homeViewList.add(acceptView);
        HomePagerAdapter homePagerAdapter = new HomePagerAdapter(homeViewList);
        homeViewPager.setAdapter(homePagerAdapter);
        homeViewPager.addOnPageChangeListener(this);

        initArrayList();
        RecyclerView recyclerView = (RecyclerView)releaseView.findViewById(R.id.findList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        PetReleaseAdapter petReleaseAdapter = new PetReleaseAdapter(petList);
        recyclerView.setAdapter(petReleaseAdapter);

        RecyclerView acceptRecyclerView = (RecyclerView)acceptView.findViewById(R.id.acceptRecyclerView);
        LinearLayoutManager acceptLayoutManager = new LinearLayoutManager(getContext());
        acceptRecyclerView.setLayoutManager(acceptLayoutManager);
        PetAcceptAdapter petAcceptAdapter = new PetAcceptAdapter(petAcceptList);
        acceptRecyclerView.setAdapter(petAcceptAdapter);
        return view;
    }
    private void initArrayList(){
        for (int i =0 ; i< 10;i++){
            PetRelease pet = new PetRelease("狗狗","拆家，吃得多，粘人","1个小时前",R.drawable.doog,1000);
            petList.add(pet);
        }
        for(int i=0;i< 10; i++){
            PetAccept pet = new PetAccept(R.drawable.head_picture,"1分钟前","苹果","想要狗狗，想要狗狗，想要狗狗，想要狗狗，想要狗狗，想要狗狗");
            petAcceptList.add(pet);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    private void homeNavButtonChange(int i){
        if (i == 0){
            releaseButton.setTextColor(black);
            releaseTag.setBackgroundColor(white);
            findButton.setTextColor(babyGreen);
            findTag.setBackgroundColor(babyGreen);
        }
        else{
            releaseButton.setTextColor(babyGreen);
            releaseTag.setBackgroundColor(babyGreen);
            findButton.setTextColor(black);
            findTag.setBackgroundColor(white);
        }
    }

    @Override
    public void onPageSelected(int position) {
        homeNavButtonChange(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class ClickListener implements View.OnClickListener{


        @Override
        public void onClick(View view){
            switch (view.getId()){
                case R.id.releaseButton:
                    homeNavButtonChange(0);
                    homeViewPager.setCurrentItem(1);
                    break;
                case R.id.findButton:
                    homeNavButtonChange(1);
                    homeViewPager.setCurrentItem(0);
                    break;
                default:
                    break;
            }
        }
    }
}
