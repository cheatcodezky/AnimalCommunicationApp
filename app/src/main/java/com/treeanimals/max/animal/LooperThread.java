package com.treeanimals.max.animal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by 95112 on 2018/6/29.
 */

public class LooperThread  extends Thread{
    public Handler mHandler;
    public void run(){
        Looper.prepare();
        mHandler = new Handler(){
            public void handleMessage(Message msg){
            }
        };
        Looper.loop();
    }
}
