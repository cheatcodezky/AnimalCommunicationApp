package com.treeanimals.max.animal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 95112 on 2018/6/6.
 */

public class MessageFragment extends Fragment {
    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        if (rootView == null){
            rootView = inflater.inflate(R.layout.message_page,container,false);
        }
        ViewGroup viewGroup = (ViewGroup)rootView.getParent();
        if (viewGroup != null){
            viewGroup.removeView(rootView);
        }
        return rootView;
    }
}
