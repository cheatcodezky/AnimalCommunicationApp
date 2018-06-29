package com.treeanimals.max.animal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by 95112 on 2018/6/29.
 */

public class WorkHandler extends Handler {
    public WorkHandler(Looper looper){
        super(looper);
    }
    public void handleMessage(Message message){
        switch(message.what){
            default:
                break;
        }
    }
    private class TestHandlerThread extends Thread{
        TestHandlerThread(){
            super("TestThread");
        }
        @Override
        public void run(){
            Looper.prepare();
            Looper looperTest = Looper.myLooper();

        }
    }
}

