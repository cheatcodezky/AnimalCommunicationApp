package com.treeanimals.max.animal;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by 95112 on 2018/6/9.
 */

public class HomePagerAdapter extends PagerAdapter {
    private ArrayList<View> viewList;

    public HomePagerAdapter(ArrayList<View> viewList){
        super();
        this.viewList = viewList;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container,int position){
        container.addView(viewList.get(position));
        return viewList.get(position);
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView(viewList.get(position));
    }

}
