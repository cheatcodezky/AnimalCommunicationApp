package com.treeanimals.max.animal;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 95112 on 2018/6/25.
 */

public class DialogueActivity extends BaseActivity {
    private Button sendMessageButton;
    private RecyclerView recyclerView;
    private MsgAdapter msgAdapter;
    private EditText contentInput;
    private List<Msg>  msgList;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogue_page);
        init();
        recyclerView = (RecyclerView)findViewById(R.id.message_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(msgAdapter);
        sendMessageButton = (Button)findViewById(R.id.sendMessageButton);
        contentInput = (EditText)findViewById(R.id.contentInput);

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = contentInput.getText().toString();
                if (!"".equals(content)){
                    Msg msg = new Msg(content,Msg.TYPE_SENT,R.drawable.head_picture);
                    msgList.add(msg);
                    msgAdapter.notifyItemInserted(msgList.size()-1);
                    recyclerView.scrollToPosition(msgList.size()-1);
                    contentInput.setText("");
                }
            }
        });
    }
    private void init(){
        msgList = new ArrayList<>();
        Msg msg = new Msg("请问你家的杜宾还在吗",Msg.TYPE_RECEIVED,R.drawable.test_head);
        msgList.add(msg);
        msgAdapter = new MsgAdapter(msgList);
    }
}
