package com.treeanimals.max.animal.weather;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.treeanimals.max.animal.VisitActivity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 95112 on 2018/7/14.
 */

public class GetCountry {
    static GetCountry getCountry;
    private static Handler mHandler;
    public static GetCountry newInstance(Handler handler){
        if (getCountry == null)
            getCountry = new GetCountry();
        mHandler = handler;
        return getCountry;
    }
    public void get(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream inputStream;
                BufferedReader bufferedReader;
                try {
                    URL url = new URL("http://guolin.tech/api/china");
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    inputStream = connection.getInputStream();
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String tmpLine;
                    String stringLine = "";
                    while ((tmpLine = bufferedReader.readLine())!=null){
                        stringLine += tmpLine;
                    }
                    Message message = new Message();
                    message.what = VisitActivity.UPDATE_TEXT;
                    Bundle bundle = new Bundle();
                    bundle.putString("country",stringLine);
                    message.setData(bundle);
                    mHandler.sendMessage(message);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private GetCountry(){


    }

}
