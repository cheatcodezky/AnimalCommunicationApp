package com.treeanimals.max.animal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by 95112 on 2018/6/6.
 */

public class UserFragment extends Fragment {
    public static final int INFORMATIONCODE = 1;
    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null){
            rootView = inflater.inflate(R.layout.user_page,container,false);
            View view = inflater.inflate(R.layout.user_page,container,false);
            LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.userInformation);
            linearLayout.setOnClickListener(new ClickListener());
        }
        return rootView;
    }
    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

    }
    class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.userInformation:
                    Intent intent = new Intent(getContext(),InformationActivtiy.class);
                    startActivityForResult(intent,INFORMATIONCODE);
                    break;
                default:
                    break;
            }
        }
    }
}
