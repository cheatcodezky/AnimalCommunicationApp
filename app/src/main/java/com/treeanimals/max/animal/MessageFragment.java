package com.treeanimals.max.animal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 95112 on 2018/6/6.
 */

public class MessageFragment extends Fragment {
    private View rootView;
    private List<UserMessage> messageList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        if (rootView == null){
            rootView = inflater.inflate(R.layout.message_page,container,false);
        }
        ViewGroup viewGroup = (ViewGroup)rootView.getParent();
        if (viewGroup != null){
            viewGroup.removeView(rootView);
        }
        init();
        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.receivedMessageList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        UserMessageAdapter userMessageAdapter = new UserMessageAdapter(messageList,getContext());
        recyclerView.setAdapter(userMessageAdapter);
        return rootView;
    }

    private void init(){
        UserMessage userMessage = new UserMessage(R.drawable.test_head,"三石","请问你家的杜宾还在吗","6月13号");
        messageList.add(userMessage);
    }
}
