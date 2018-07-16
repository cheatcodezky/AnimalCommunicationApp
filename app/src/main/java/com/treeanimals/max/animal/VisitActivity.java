package com.treeanimals.max.animal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.treeanimals.max.animal.weather.GetCountry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 95112 on 2018/7/14.
 */

public class VisitActivity extends BaseActivity {
    public static final int UPDATE_TEXT =1 ;
    TextView textView;
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case UPDATE_TEXT:
                    Bundle bundle = msg.getData();
                    String country = bundle.getString("country");
                    textView.setText(country);
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = new JSONArray(country);
                        for(int i = 0 ; i< jsonArray.length();i++){
                            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                            String id = jsonObject.getString("id");
                            String name = jsonObject.getString("name");
                            Log.e(id,name);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit);
        textView = (TextView)findViewById(R.id.showCountry);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetCountry getCountry = GetCountry.newInstance(handler);
                getCountry.get();
            }
        });

    }
}
