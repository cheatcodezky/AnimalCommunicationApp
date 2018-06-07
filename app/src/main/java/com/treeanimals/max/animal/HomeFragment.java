package com.treeanimals.max.animal;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by 95112 on 2018/6/6.
 */

public class HomeFragment extends Fragment {
    private TextView findButton,releaseButton;
    private View findTag,releaseTag;
    int babyGreen,black,white;
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
        return view;
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
