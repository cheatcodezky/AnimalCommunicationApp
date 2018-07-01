package com.treeanimals.max.animal;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by 95112 on 2018/6/30.
 */

public class SaveUtils {
    static private SharedPreferences.Editor editor = null;
    static private SharedPreferences sharedPreferences = null;
    static private Context context = null;
    public SaveUtils(Context context){
        this.context = context;

        sharedPreferences = this.context.getSharedPreferences("data",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


    }
    public static void saveBySharedPreferences(HashMap<String,String> hashMap){
        Iterator<HashMap.Entry<String,String>> iterator = hashMap.entrySet().iterator();
        while(iterator.hasNext()){
            HashMap.Entry<String,String> entry = iterator.next();
            editor.putString(entry.getKey(),entry.getValue());
        }
        editor.apply();
    }
    public static String getDataFromSharedPreferences(String key){
        return sharedPreferences.getString(key,null);
    }
    public static String load(){
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try{
            in = context.openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while((line = reader.readLine()) != null){
                content.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
    static public void saveString(String saveData){
        String data = saveData;
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try{
            out = context.openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try{
                if (writer!=null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
